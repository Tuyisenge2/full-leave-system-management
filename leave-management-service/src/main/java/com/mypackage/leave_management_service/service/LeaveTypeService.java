package com.mypackage.leave_management_service.service;

import com.mypackage.leave_management_service.dto.LeaveTypeDTO;
import com.mypackage.leave_management_service.model.LeaveType;
import com.mypackage.leave_management_service.repository.LeaveTypeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LeaveTypeService {
    private final LeaveTypeRepository leaveTypeRepository;

    public LeaveTypeService(LeaveTypeRepository leaveTypeRepository) {
        this.leaveTypeRepository = leaveTypeRepository;
    }

    public LeaveTypeDTO createLeaveType(LeaveTypeDTO leaveTypeDTO) {
        try {
            // Check if leave type with same name exists
            if (leaveTypeRepository.existsByName(leaveTypeDTO.getName())) {
                throw new ResponseStatusException(HttpStatus.CONFLICT, 
                    "Leave type with name '" + leaveTypeDTO.getName() + "' already exists");
            }

            LeaveType leaveType = new LeaveType();
            BeanUtils.copyProperties(leaveTypeDTO, leaveType);
            LeaveType savedLeaveType = leaveTypeRepository.save(leaveType);
            BeanUtils.copyProperties(savedLeaveType, leaveTypeDTO);
            return leaveTypeDTO;
        } catch (ResponseStatusException e) {
            throw e;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, 
                "Error creating leave type: " + e.getMessage());
        }
    }

    public List<LeaveTypeDTO> getAllLeaveTypes() {
        try {
            return leaveTypeRepository.findAll().stream()
                    .map(leaveType -> {
                        LeaveTypeDTO dto = new LeaveTypeDTO();
                        BeanUtils.copyProperties(leaveType, dto);
                        return dto;
                    })
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, 
                "Error retrieving leave types: " + e.getMessage());
        }
    }

    public LeaveTypeDTO updateLeaveType(Integer id, LeaveTypeDTO leaveTypeDTO) {
        try {
            LeaveType leaveType = leaveTypeRepository.findById(id)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, 
                        "Leave type with ID " + id + " not found"));

            // Check if another leave type with the same name exists (excluding current leave type)
            if (!leaveType.getName().equals(leaveTypeDTO.getName()) && 
                leaveTypeRepository.existsByName(leaveTypeDTO.getName())) {
                throw new ResponseStatusException(HttpStatus.CONFLICT, 
                    "Leave type with name '" + leaveTypeDTO.getName() + "' already exists");
            }
            
            // Update only the fields that should be updated
            leaveType.setName(leaveTypeDTO.getName());
            leaveType.setDeductible(leaveTypeDTO.isDeductible());
            leaveType.setRequiresDocument(leaveTypeDTO.isRequiresDocument());
            
            LeaveType updatedLeaveType = leaveTypeRepository.save(leaveType);
            BeanUtils.copyProperties(updatedLeaveType, leaveTypeDTO);
            return leaveTypeDTO;
        } catch (ResponseStatusException e) {
            throw e;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, 
                "Error updating leave type: " + e.getMessage());
        }
    }

    public void deleteLeaveType(Integer id) {
        try {
            if (!leaveTypeRepository.existsById(id)) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, 
                    "Leave type with ID " + id + " not found");
            }
            leaveTypeRepository.deleteById(id);
        } catch (ResponseStatusException e) {
            throw e;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, 
                "Error deleting leave type: " + e.getMessage());
        }
    }
} 