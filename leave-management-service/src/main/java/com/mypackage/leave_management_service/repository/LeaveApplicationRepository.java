package com.mypackage.leave_management_service.repository;

import com.mypackage.leave_management_service.model.LeaveApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LeaveApplicationRepository extends JpaRepository<LeaveApplication, Integer> {
    List<LeaveApplication> findByEmployeeEmail(String email);
} 