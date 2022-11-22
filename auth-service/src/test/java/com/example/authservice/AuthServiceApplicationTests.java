package com.example.authservice;

import com.example.authservice.dto.RangeCurrencyRateRequest;
import com.example.authservice.dto.domestic.UserDto;
import com.example.authservice.grpcclient.RateGrpcClient;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

//@SpringBootTest
class AuthServiceApplicationTests {
	private final Gson gson = new Gson();
    @Autowired
    private RateGrpcClient rateGrpcClient;
//	@Test
	void contextLoads() {
	}
//	@Test
	void datetest1(){
		LocalDateTime now = LocalDateTime.now();
		String dateStr = now.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
		UserDto userDTO = UserDto.builder().dateCreate(dateStr).build();
		System.out.println(gson.toJson(userDTO));

	}
//   @Test
    void grpcTest1(){
//        CurrencyResponse[] res = rateGrpcClient.getCurrencyList();
//        for(CurrencyResponse c : res){
//            System.out.println(c);
//        }

//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//        LocalDate date = LocalDate.parse(s1, formatter);

//        String s1 = "2020-12-12";
//        String s2 = "2021-12-22";
//        RangeCurrencyRateRequest request = RangeCurrencyRateRequest.builder()
//                .dateFrom(s1)
//                .dateTo(s2)
//                .id("R01235")
//                .build();
//        System.out.println(rateGrpcClient.getRangeCurrencyRate(request));

        /*CurrentCurrencyRateRequest request = CurrentCurrencyRateRequest.builder()
                .id("R01235")
                .build();
        System.out.println(rateGrpcClient.getCurrentCurrencyRate(request));*/
	   RangeCurrencyRateRequest request = RangeCurrencyRateRequest.builder()
			   .dateTo("2022-10-10")
			   .dateFrom("2020-12-12")
			   .id("R01235")
			   .build();
	   System.out.println(rateGrpcClient.getRangeCurrencyRate(request));;
    }
}
