package com.nhnacademy.department.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
@Table(name = "department")
public class Department {
    @Id
    @Column(name = "department_id")
    private String departmentId;

    @Column(name = "department_name")
    private String departmentName;

}
