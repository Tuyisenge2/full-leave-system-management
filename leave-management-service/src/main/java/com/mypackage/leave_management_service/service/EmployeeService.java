package com.mypackage.leave_management_service.service;

import com.mypackage.leave_management_service.client.EmployeeServiceClient;
import com.mypackage.leave_management_service.dto.EmployeeDTO;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class EmployeeService {

    private final EmployeeServiceClient employeeServiceClient;

    @Autowired
    public EmployeeService(EmployeeServiceClient employeeServiceClient) {
        this.employeeServiceClient = employeeServiceClient;
    }

    public EmployeeDTO getEmployeeById(Long id) {
        try {
            return employeeServiceClient.getEmployeeById(id);
        } catch (FeignException.NotFound e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found with ID: " + id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error fetching employee: " + e.getMessage());
        }
    }

    public EmployeeDTO getEmployeeByEmail(String email) {
        try {
            return employeeServiceClient.getEmployeeByEmail(email);
        } catch (FeignException.NotFound e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found with email: " + email);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error fetching employee: " + e.getMessage());
        }
    }
} 