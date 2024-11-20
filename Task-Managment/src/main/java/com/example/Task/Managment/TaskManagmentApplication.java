package com.example.Task.Managment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "com.example.Task.Managment.entity")

public class TaskManagmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaskManagmentApplication.class, args);
	}

}
