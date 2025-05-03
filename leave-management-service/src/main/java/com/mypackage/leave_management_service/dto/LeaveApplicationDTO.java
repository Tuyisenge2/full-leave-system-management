package com.mypackage.leave_management_service.dto;

import com.mypackage.leave_management_service.model.LeaveStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDate;

@Data
public class LeaveApplicationDTO {
    private Integer id;
    
    @JsonProperty("email")
    private String employeeEmail;
    
    @JsonProperty("leaveTypeId")
    private String leaveTypeId;
    
    @JsonProperty("start_date")
    private LocalDate startDate;
    
    @JsonProperty("end_date")
    private LocalDate endDate;
    
    private String reason;
    
    @JsonProperty("document_url")
    private String documentUrl;
    
    private LeaveStatus status;
} 