package com.nhnacademy.department.controller;

import com.nhnacademy.department.domain.ManagementDTO;
import com.nhnacademy.department.exception.NotAllowedAcceptHeader;
import com.nhnacademy.department.exception.NotFoundDepartment;
import com.nhnacademy.department.service.DepartmentService;
import com.nhnacademy.department.service.ManagementService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@RestController
@RequestMapping("/department-members")
public class ManagementController {

    private final DepartmentService departmentService;
    private final ManagementService managementService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ManagementDTO> getDepartmentInMember(@RequestHeader(value = "Accept") String accept,
                                                     @RequestParam(name = "departmentIds") List<String> departmentIds) {

        if (!Objects.equals(accept, MediaType.APPLICATION_JSON_VALUE)) {
            throw new NotAllowedAcceptHeader(accept);
        }

        for (String departmentId : departmentIds) {
            checkExistDepartment(departmentId);
        }

        return managementService.getByDepartmentIdInEmployee(departmentIds);
    }

    private void checkExistDepartment(String departmentId) {
        if (!departmentService.existsDepartment(departmentId)) {
            throw new NotFoundDepartment(departmentId);
        }
    }


}
