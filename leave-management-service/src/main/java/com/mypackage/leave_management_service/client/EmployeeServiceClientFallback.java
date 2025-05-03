package com.mypackage.leave_management_service.client;

import com.mypackage.leave_management_service.dto.EmployeeDTO;
import org.springframework.stereotype.Component;

@Component
public class EmployeeServiceClientFallback implements EmployeeServiceClient {
    
    @Override
    public EmployeeDTO getEmployeeById(Long id) {
        return null; // Return null or a default employee in case of failure
    }
    
    @Override
    public EmployeeDTO getEmployeeByEmail(String email) {
        return null; // Return null or a default employee in case of failure
    }
} 