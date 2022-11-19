package com.example.authservice;

import com.example.authservice.dto.maindto.UserDTO;
import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@SpringBootTest
class AuthServiceApplicationTests {
	/*private final Gson gson = new Gson();
	@Test
	void contextLoads() {
	}
	@Test
	void datetest1(){
		LocalDateTime now = LocalDateTime.now();
		String dateStr = now.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
		UserDTO userDTO = UserDTO.builder().dateCreate(dateStr).build();
		System.out.println(gson.toJson(userDTO));

	}*/
}
