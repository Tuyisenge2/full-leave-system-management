package com.mypackage.leave_management_service.controller;

import com.mypackage.leave_management_service.dto.LeaveApplicationDTO;
import com.mypackage.leave_management_service.service.LeaveApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@RequestMapping("/api/leave-applications")
public class LeaveApplicationController {
    private final LeaveApplicationService leaveApplicationService;

    @Autowired
    public LeaveApplicationController(LeaveApplicationService leaveApplicationService) {
        this.leaveApplicationService = leaveApplicationService;
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> createLeaveApplication(@RequestBody LeaveApplicationDTO leaveApplicationDTO) {
        LeaveApplicationDTO created = leaveApplicationService.createLeaveApplication(leaveApplicationDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Map.of(
                    "status", "success",
                    "message", "Leave application created successfully",
                    "data", created
                ));
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllLeaveApplications() {
        List<LeaveApplicationDTO> applications = leaveApplicationService.getAllLeaveApplications();
        return ResponseEntity.ok(Map.of(
            "status", "success",
            "message", "Leave applications retrieved successfully",
            "data", applications
        ));
    }

    @GetMapping("/employee/{email}")
    public ResponseEntity<List<LeaveApplicationDTO>> getLeaveApplicationsByEmployee(@PathVariable String email) {
        return ResponseEntity.ok(leaveApplicationService.getLeaveApplicationsByEmployee(email));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updateLeaveApplication(
            @PathVariable Integer id,
            @RequestBody LeaveApplicationDTO leaveApplicationDTO) {
        LeaveApplicationDTO updated = leaveApplicationService.updateLeaveApplication(id, leaveApplicationDTO);
        return ResponseEntity.ok(Map.of(
            "status", "success",
            "message", "Leave application updated successfully",
            "data", updated
        ));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteLeaveApplication(@PathVariable Integer id) {
        leaveApplicationService.deleteLeaveApplication(id);
        return ResponseEntity.ok(Map.of(
            "status", "success",
            "message", "Leave application deleted successfully"
        ));
    }
} 