package com.nhnacademy.department.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
@Table(name = "management")
public class Management {

    @EmbeddedId
    private Pk pk;

    @MapsId("departmentId")
    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    @MapsId("employeeId")
    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    @EqualsAndHashCode
    @Getter
    @Embeddable
    public static class Pk implements Serializable {
        @Column(name = "department_id")
        private String departmentId;

        @Column(name = "employee_id")
        private String employeeId;
    }


}
