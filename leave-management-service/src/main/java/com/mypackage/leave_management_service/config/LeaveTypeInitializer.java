package com.mypackage.leave_management_service.config;

import com.mypackage.leave_management_service.model.LeaveType;
import com.mypackage.leave_management_service.repository.LeaveTypeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class LeaveTypeInitializer implements CommandLineRunner {

    private final LeaveTypeRepository leaveTypeRepository;

    public LeaveTypeInitializer(LeaveTypeRepository leaveTypeRepository) {
        this.leaveTypeRepository = leaveTypeRepository;
    }

    @Override
    public void run(String... args) {
        createLeaveTypeIfNotExists("Personal Time Off (PTO)", true, false);
        createLeaveTypeIfNotExists("Sick Leave", true, true);
        createLeaveTypeIfNotExists("Compassionate Leave", false, true);
        createLeaveTypeIfNotExists("Maternity Leave", false, true);
    }

    private void createLeaveTypeIfNotExists(String name, boolean isDeductible, boolean requiresDocument) {
        if (!leaveTypeRepository.existsByName(name)) {
            LeaveType leaveType = new LeaveType();
            leaveType.setName(name);
            leaveType.setDeductible(isDeductible);
            leaveType.setRequiresDocument(requiresDocument);
            leaveTypeRepository.save(leaveType);
        }
    }
} 