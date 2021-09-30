package com.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.app.service.UserService;

@SpringBootApplication
public class ExamPortalForJmeApplication {


	public static void main(String[] args) {
		SpringApplication.run(ExamPortalForJmeApplication.class, args);
	}

}
