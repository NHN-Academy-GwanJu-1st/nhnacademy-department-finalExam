package com.nhnacademy.department.repository;

import com.nhnacademy.department.entity.Management;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ManagementRepository extends JpaRepository<Management, Management.Pk>, ManagementRepositoryCustom {
}
