package com.mypackage.leave_management_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class LeaveManagementServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(LeaveManagementServiceApplication.class, args);
	}

}
