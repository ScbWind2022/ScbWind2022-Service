package com.example.userservice.service.impl;

import com.example.userservice.dto.CheckDto;
import com.example.userservice.dto.UserDTO;
import com.example.userservice.exception.CheckNotFoundException;
import com.example.userservice.exception.NotValidRequestException;
import com.example.userservice.exception.UserNotFoundException;
import com.example.userservice.model.Check;
import com.example.userservice.model.User;
import com.example.userservice.repository.CheckRepository;
import com.example.userservice.repository.UserRepository;
import com.example.userservice.service.CheckService;
import com.example.userservice.utils.DtoUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CheckServiceImpl implements CheckService {
    private final CheckRepository checkRepository;
    private final UserRepository userRepository;
    private final DtoUtils dtoUtils;
    @Override
    public void createCheckWithUser(Long user_id) {
        final Check check = Check.builder()
                .checkToken(UUID.randomUUID().toString())
                .currencyCharCode("RUB")
                .enabled(true)
                .build();
        checkRepository.save(check);
        checkRepository.updateUserAndCheck(user_id,check.getId());
    }

    @Override
    public CheckDto openNewCheckWithUser(String userEmail) {
        final User user = userRepository.getUserByEmail(userEmail);
        if(user == null){
            throw new UserNotFoundException();
        }
        final Check check = Check.builder()
                .checkToken(UUID.randomUUID().toString()).build();
        check.setUser(user);
        checkRepository.save(check);
        return dtoUtils.checkToCheckDto(check);
    }
    private void getCheckByUserEmailValid(UserDTO userDTO){
        if(userDTO == null){
            throw new NotValidRequestException();
        }
        if(userDTO.getEmail() == null) {
            throw new NotValidRequestException();
        }
    }
    @Override
    public CheckDto[] getCheckByUserEmail(UserDTO userDTO) {
        getCheckByUserEmailValid(userDTO);
        final List<Check> checks = checkRepository.getCheksByUserEmail(userDTO.getEmail());
        if(checks != null && checks.size() > 0){
            final CheckDto[] checkDtos = new CheckDto[checks.size()];
            int index = 0;
            for(Check c : checks){
                CheckDto checkDto = dtoUtils.checkToCheckDto(c);
                checkDto.setUserId(Integer.parseInt(String.valueOf(c.getUser().getId())));
                checkDtos[index] = checkDto;
                index++;
            }
            return checkDtos;
        }
        return new CheckDto[0];
    }

    @Override
    public CheckDto changeSumByEmail(CheckDto checkDto) {
        System.out.println(checkDto);
        final Long check_id = Long.parseLong(String.valueOf(checkDto.getId()));
        final String email = checkDto.getUserEmail();
        final Integer sum = Integer.parseInt(checkDto.getSum());

        final Check check1 = checkRepository.getCheckByIdAndUserEmail(check_id,email);
        if(check1 == null){
            throw new CheckNotFoundException();
        }
        checkRepository.changeSumById(check_id,sum);
        final Check check = checkRepository.getCheckByIdAndUserEmail(check_id,email);
        if(check != null){
            final CheckDto res = dtoUtils.checkToCheckDto(check);
            return res;
        }
        return null;
    }

    @Override
    public boolean changeEnableByEmail(CheckDto checkDto) {
        final Long check_id = Long.parseLong(String.valueOf(checkDto.getId()));
        return checkRepository.updateEnableByIdAndUserEmail(check_id,checkDto.getUserEmail(),checkDto.isEnable());
    }

    @Override
    public CheckDto createCheck(CheckDto checkDto) {
        final Check check = dtoUtils.checkDtoToCheck(checkDto);
        check.setCheckToken(UUID.randomUUID().toString());
        check.setEnabled(true);
        final User user = userRepository.getUserByEmail(checkDto.getUserEmail());
        if(user == null){
            throw new UserNotFoundException();
        }
        check.setUser(user);
        checkRepository.save(check);
        return dtoUtils.checkToCheckDto(check);
    }
}
