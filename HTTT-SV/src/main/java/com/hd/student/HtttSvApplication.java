package com.hd.student;

import com.hd.student.entity.Role;
import com.hd.student.entity.User;
import com.hd.student.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.HashSet;

@SpringBootApplication
public class HtttSvApplication {

	public static void main(String[] args) {
		SpringApplication.run(HtttSvApplication.class, args);
	}

//	@Bean
//	CommandLineRunner run(UserService userService) {
//		return args -> {
//			userService.saveUser(new User("Hoang Duong", "1957012049-HoangDuong","sadsa",Role.ADMIN,"12323"));
//		};
//	}
}