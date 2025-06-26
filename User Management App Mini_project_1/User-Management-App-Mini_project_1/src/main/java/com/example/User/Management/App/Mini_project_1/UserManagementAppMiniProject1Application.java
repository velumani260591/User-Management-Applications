package com.example.User.Management.App.Mini_project_1;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.apache.catalina.mapper.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "User Management application",
				description = "Store the user information",
				version = "1.0",
				contact = @Contact(
						name = "thrivendra",
						email = "sample@gmail.com"
				)
		)
)
public class UserManagementAppMiniProject1Application {


	public static void main(String[] args) {
		SpringApplication.run(UserManagementAppMiniProject1Application.class, args);
	}

}
