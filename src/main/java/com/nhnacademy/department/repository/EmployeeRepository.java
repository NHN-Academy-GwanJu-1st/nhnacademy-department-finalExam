package com.nhnacademy.department.repository;

import com.nhnacademy.department.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, String> {
}
