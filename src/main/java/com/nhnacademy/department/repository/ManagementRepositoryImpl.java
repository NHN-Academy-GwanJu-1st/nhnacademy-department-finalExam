package com.nhnacademy.department.repository;

import com.nhnacademy.department.domain.ManagementDTO;
import com.nhnacademy.department.entity.Management;
import com.nhnacademy.department.entity.QDepartment;
import com.nhnacademy.department.entity.QEmployee;
import com.nhnacademy.department.entity.QManagement;
import com.querydsl.core.types.Projections;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class ManagementRepositoryImpl extends QuerydslRepositorySupport implements ManagementRepositoryCustom {

    public ManagementRepositoryImpl() {
        super(Management.class);
    }

    @Override
    public List<ManagementDTO> getByDepartmentIdInEmployee(String departmentId) {
        QManagement management = QManagement.management;
        QDepartment department = QDepartment.department;
        QEmployee employee = QEmployee.employee;

        return from(management)
                .select(Projections.constructor(ManagementDTO.class,
                        department,
                        employee))
                .innerJoin(management.department, department)
                .innerJoin(management.employee, employee)
                .where(management.pk.departmentId.eq(departmentId))
                .orderBy(employee.employeeId.asc())
                .fetch();
    }
}
