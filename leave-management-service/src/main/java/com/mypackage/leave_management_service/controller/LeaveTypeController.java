package com.mypackage.leave_management_service.controller;

import com.mypackage.leave_management_service.dto.LeaveTypeDTO;
import com.mypackage.leave_management_service.service.LeaveTypeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:3000") 
@RequestMapping("/api/leave-types")
public class LeaveTypeController {

    
    private final LeaveTypeService leaveTypeService;

    public LeaveTypeController(LeaveTypeService leaveTypeService) {
        this.leaveTypeService = leaveTypeService;
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> createLeaveType(@RequestBody LeaveTypeDTO leaveTypeDTO) {
        LeaveTypeDTO created = leaveTypeService.createLeaveType(leaveTypeDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Map.of(
                    "status", "success",
                    "message", "Leave type created successfully",
                    "data", created
                ));
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllLeaveTypes() {
        List<LeaveTypeDTO> leaveTypes = leaveTypeService.getAllLeaveTypes();
        return ResponseEntity.ok(Map.of(
            "status", "success",
            "message", "Leave types retrieved successfully",
            "data", leaveTypes
        ));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updateLeaveType(
            @PathVariable Integer id,
            @RequestBody LeaveTypeDTO leaveTypeDTO) {
        LeaveTypeDTO updated = leaveTypeService.updateLeaveType(id, leaveTypeDTO);
        return ResponseEntity.ok(Map.of(
            "status", "success",
            "message", "Leave type updated successfully",
            "data", updated
        ));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteLeaveType(@PathVariable Integer id) {
        leaveTypeService.deleteLeaveType(id);
        return ResponseEntity.ok(Map.of(
            "status", "success",
            "message", "Leave type deleted successfully"
        ));
    }
} 