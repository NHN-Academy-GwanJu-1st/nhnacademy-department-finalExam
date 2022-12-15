package com.nhnacademy.department.service;

import com.nhnacademy.department.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;

    @Transactional(readOnly = true)
    @Override
    public boolean existsDepartment(String departmentId) {
        return departmentRepository.existsById(departmentId);
    }
}
