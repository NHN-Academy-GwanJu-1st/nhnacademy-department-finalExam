package com.nhnacademy.department.domain;

import com.nhnacademy.department.entity.Department;
import com.nhnacademy.department.entity.Employee;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ManagementDTO {
    private Department department;
    private Employee employee;
}
