package com.mypackage.leave_management_service.dto;

import lombok.Data;

@Data
public class LeaveTypeDTO {
    private Integer id;
    private String name;
    private boolean isDeductible;
    private boolean requiresDocument;
} 