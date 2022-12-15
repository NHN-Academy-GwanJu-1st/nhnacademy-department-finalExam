package com.nhnacademy.department.domain;

import lombok.*;

@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ManagementRegisterRequest {
    private String employeeId;
    private String employeeName;
    private String departmentName;
    private String departmentId;
}
