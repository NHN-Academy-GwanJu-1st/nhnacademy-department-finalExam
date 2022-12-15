package com.nhnacademy.department.repository;

import com.nhnacademy.department.domain.ManagementDTO;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@NoRepositoryBean
public interface ManagementRepositoryCustom {
    @Transactional(readOnly = true)
    List<ManagementDTO> getByDepartmentIdInEmployee(String departmentId);
}
