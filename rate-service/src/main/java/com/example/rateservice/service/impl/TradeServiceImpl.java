package com.example.rateservice.service.impl;

import com.example.rateservice.dto.AccountResponseDto;
import com.example.rateservice.dto.CurrencyRateRequest;
import com.example.rateservice.dto.CurrencyRateResponse;
import com.example.rateservice.dto.OperationListRequest;
import com.example.rateservice.dto.TradeOperationRequest;
import com.example.rateservice.dto.TradeOperationResponse;
import com.example.rateservice.dto.TradeSessionRequest;
import com.example.rateservice.dto.maindto.UserDTO;
import com.example.rateservice.exception.TradeOperationException;
import com.example.rateservice.grpcclient.TradeGrpcClient;
import com.example.rateservice.service.AccountService;
import com.example.rateservice.service.CurrencyRateService;
import com.example.rateservice.service.TradeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
@RequiredArgsConstructor
public class TradeServiceImpl implements TradeService {

    private final AccountService accountService;
    private final CurrencyRateService currencyRateService;
    private final TradeGrpcClient tradeGrpcClient;

    private final Map<String, Map<Integer, BigDecimal>> userAccountBalances = new ConcurrentHashMap<>();

    @Override
    public void operateTradeSession(TradeSessionRequest request, String email) {

        if (request.isEnable()) {
            openSession(email);
            // Заблокировать счета на user-service (пропускаем)

            // Получить информацию по счетам и поместить в мапу
            AccountResponseDto[] accounts = accountService.getAccountsUserByEmail(email);
            Map<Integer, BigDecimal> sums = new HashMap<>();
            for (AccountResponseDto account : accounts) {
                System.out.println(account);
                System.out.println(account.getSum());
                sums.put(account.getId(), new BigDecimal(account.getSum()));
            }
            userAccountBalances.put(email, sums);
        } else {
            Map<Integer, BigDecimal> sums = userAccountBalances.get(email);
            for (Map.Entry<Integer, BigDecimal> entry : sums.entrySet()) {
                accountService.changeSumInAccountByUserEmailAndId(email, entry.getKey(), entry.getValue());
            }
            userAccountBalances.remove(email);
            closeSession(email);
            // Разблокировать счета на user-service (пропускаем)
        }
    }

    @Override
    public TradeOperationResponse operateTrade(TradeOperationRequest request, String email) {
        System.out.println(userAccountBalances);
        String currencyId = request.getCurrencyId();
        System.out.println(request);
        CurrencyRateResponse currentCurrencyRate = currencyRateService.getCurrentCurrencyRate(
                CurrencyRateRequest.builder().id(currencyId).build());
        BigDecimal rate = currentCurrencyRate.getValue().multiply(BigDecimal.valueOf(currentCurrencyRate.getNominal()));

        BigDecimal amount = request.getAmount();
        BigDecimal sum = amount.multiply(rate).setScale(2,  RoundingMode.HALF_EVEN);

        Map<Integer, BigDecimal> balances = userAccountBalances.get(email);
        if (balances == null) {
            throw new TradeOperationException();
        }
        BigDecimal sumFrom = balances.get(request.getAccountIdFrom());
        BigDecimal sumTo = balances.get(request.getAccountIdTo());
        if (sumFrom == null || sumTo == null) {
            throw new TradeOperationException();
        }

        if ("buy".equalsIgnoreCase(request.getOperation())) {
            balances.put(request.getAccountIdFrom(), sumFrom.subtract(sum));
            balances.put(request.getAccountIdTo(), sumFrom.add(sum));
        } else if ("sell".equalsIgnoreCase(request.getOperation())) {
            balances.put(request.getAccountIdFrom(), sumFrom.add(sum));
            balances.put(request.getAccountIdTo(), sumFrom.subtract(sum));
        }

        return TradeOperationResponse.builder()
                .accountIdFrom(request.getAccountIdFrom())
                .accountIdTo(request.getAccountIdTo())
                .currencyId(currencyId)
                .operation(request.getOperation())
                .currencyRate(rate)
                .amount(amount)
                .sum(sum)
                .build();
    }

    @Override
    public List<TradeOperationResponse> tradeOperationList(OperationListRequest request, String email) {
        // TODO
        return List.of();
    }

    @Override
    public String openSession(String email) {
        final UserDTO userDTO = UserDTO.builder()
                .email(email)
                .build();
        return tradeGrpcClient.openSession(userDTO);
    }

    @Override
    public String closeSession(String email) {
        final UserDTO userDTO = UserDTO.builder()
                .email(email)
                .build();
        return tradeGrpcClient.closeSession(userDTO);
    }
}
