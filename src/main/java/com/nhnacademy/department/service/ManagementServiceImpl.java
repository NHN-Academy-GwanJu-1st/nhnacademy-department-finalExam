package com.nhnacademy.department.service;

import com.nhnacademy.department.domain.ManagementDTO;
import com.nhnacademy.department.domain.ManagementRegisterRequest;
import com.nhnacademy.department.entity.Department;
import com.nhnacademy.department.entity.Employee;
import com.nhnacademy.department.entity.Management;
import com.nhnacademy.department.repository.DepartmentRepository;
import com.nhnacademy.department.repository.EmployeeRepository;
import com.nhnacademy.department.repository.ManagementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ManagementServiceImpl implements ManagementService {

    private final ManagementRepository managementRepository;
    private final DepartmentRepository departmentRepository;
    private final EmployeeRepository employeeRepository;

    @Transactional
    @Override
    public Management saveTextFile(ManagementRegisterRequest managementRequest) {

        Employee employee = new Employee(
                managementRequest.getEmployeeId(),
                managementRequest.getEmployeeName()
        );

        Department department = new Department(
                managementRequest.getDepartmentId(),
                managementRequest.getDepartmentName()
        );

        Management management = new Management(
                new Management.Pk(department.getDepartmentId(), employee.getEmployeeId()),
                department,
                employee
        );

        departmentRepository.save(department);
        employeeRepository.save(employee);
        managementRepository.save(management);

        return management;
    }

    @Override
    public List<ManagementDTO> getByDepartmentIdInEmployee(List<String> departmentIds) {
        List<ManagementDTO> managementList = new ArrayList<>();

        departmentIds.forEach((departmentId) ->
                managementList.addAll(managementRepository.getByDepartmentIdInEmployee(departmentId))
        );

        return managementList;
    }
}


