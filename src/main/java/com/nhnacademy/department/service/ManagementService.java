package com.nhnacademy.department.service;

import com.nhnacademy.department.domain.ManagementDTO;
import com.nhnacademy.department.domain.ManagementRegisterRequest;
import com.nhnacademy.department.entity.Management;

import java.util.List;

public interface ManagementService {

    Management saveTextFile(ManagementRegisterRequest managementRequest);

    List<ManagementDTO> getByDepartmentIdInEmployee(List<String> departmentId);
}
