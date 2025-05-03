package com.mypackage.leave_management_service.dto;

import lombok.Data;

@Data
public class EmployeeDTO {
    private Long id;
    private String email;
    private String name;
    private String picture;
    private String googleId;
    private RoleDTO role;
}

@Data
class RoleDTO {
    private Long id;
    private String name;
} 