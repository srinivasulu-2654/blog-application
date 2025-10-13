package com.sreenu.blog_app.blog_application_project;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BlogApplicationProjectApplication {

	public static void main(String[] args) {

		SpringApplication.run(BlogApplicationProjectApplication.class, args);
	}

	@Bean   // (here @Configuration is not providing even though it will work why because there is @SpringBootApplication)
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

}
