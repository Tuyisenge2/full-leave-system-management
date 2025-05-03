package com.mypackage.leave_management_service.client;

import com.mypackage.leave_management_service.dto.EmployeeDTO;
import com.mypackage.leave_management_service.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
    name = "employee-service",
    url = "http://localhost:8000",
    configuration = FeignConfig.class,
    fallback = EmployeeServiceClientFallback.class
)
public interface EmployeeServiceClient {
    
    @GetMapping("/api/employees/{id}")
    EmployeeDTO getEmployeeById(@PathVariable Long id);
    
    @GetMapping("/api/employees/{email}")
    EmployeeDTO getEmployeeByEmail(@PathVariable String email);
}