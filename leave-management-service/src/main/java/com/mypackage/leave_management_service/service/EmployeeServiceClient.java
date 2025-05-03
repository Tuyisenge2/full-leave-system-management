// package com.mypackage.leave_management_service.service;

// import org.springframework.cloud.openfeign.FeignClient;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;

// @FeignClient(name = "employee-service", url = "${employee.service.url}")
// public interface EmployeeServiceClient {
//     @GetMapping("/employees/{id}")
//     Employee getEmployeeById(@PathVariable String id);
// }