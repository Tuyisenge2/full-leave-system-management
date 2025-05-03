package com.mypackage.leave_management_service.repository;

import com.mypackage.leave_management_service.model.LeaveType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LeaveTypeRepository extends JpaRepository<LeaveType, Integer> {
    boolean existsByName(String name);
    Optional<LeaveType> findByName(String name);
}