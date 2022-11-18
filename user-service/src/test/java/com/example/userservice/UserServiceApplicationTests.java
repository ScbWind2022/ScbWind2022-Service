package com.example.userservice;

import com.example.userservice.dto.UserDTO;
import com.example.userservice.model.Role;
import com.example.userservice.model.User;
import com.example.userservice.service.UserService;
import com.example.userservice.utils.DtoUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.Set;

//@SpringBootTest
class UserServiceApplicationTests {
//	@Autowired
	private DtoUtils dtoUtils;
//	@Autowired
	private UserService userService;
//	@Test
	void contextLoads() {
	}
//	@Test
	void testUtils1(){
		final Set<Role> roleSet = Collections.singleton(Role.builder().name("ROLE_USER").build());
		final User user = User.builder().id(1L)
				.email("email").firstName("firstName").roles(roleSet).build();
		System.out.println(dtoUtils.userToUserDTO(user));
	}
//	@Test
	void testRegister1(){
		final UserDTO userDTO = UserDTO.builder()
				.email("email1").password("password").firstName("firstName").build();
		System.out.println(userDTO);
		System.out.println(userService.registerUser(userDTO));
	}
//	@Test
	void testLogin1(){
		final UserDTO userDTO = UserDTO.builder()
				.email("email").password("password").firstName("firstName").build();
		System.out.println(userService.getUserAndRoleByEmail(userDTO.getEmail()));
	}
}
