package com.example.userservice;

import com.example.userservice.model.Role;
import com.example.userservice.model.User;
import com.example.userservice.repository.RoleRepository;
import com.example.userservice.repository.UserRepository;
import com.example.userservice.service.CheckService;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import java.util.Collections;

@SpringBootApplication
@EnableEurekaClient
public class UserServiceApplication implements CommandLineRunner {
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private CheckService checkService;
	public static void main(String[] args) {
		SpringApplication.run(UserServiceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		final Role role = Role.builder().name("ROLE_USER").build();
		final Role role1 = Role.builder().name("ROLE_ADMIN").build();
		roleRepository.save(role1);
		roleRepository.save(role);
		final User user = User.builder()
				.firstName("firstName")
				.email("email")
				.password("password")
				.roles(Collections.singleton(role))
				.build();
		final User user1 = User.builder()
				.firstName("adminName")
				.email("admin")
				.password("password")
				.roles(Collections.singleton(role1))
				.accepted(true)
				.build();
		userRepository.save(user);
		userRepository.save(user1);
		userRepository.acceptedUserById(user.getId());


	}
}
