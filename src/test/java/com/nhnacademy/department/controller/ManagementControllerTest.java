package com.nhnacademy.department.controller;

import com.nhnacademy.department.domain.ManagementDTO;
import com.nhnacademy.department.entity.Department;
import com.nhnacademy.department.entity.Employee;
import com.nhnacademy.department.service.DepartmentService;
import com.nhnacademy.department.service.ManagementService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
class ManagementControllerTest {

    @MockBean
    private ManagementService managementService;

    @MockBean
    private DepartmentService departmentService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getDepartmentInMember_notAllowedAcceptHeader_thenResponseStatusBadRequest() throws Exception {
        String departmentId = "L1001";
        MediaType mediaType = MediaType.APPLICATION_XML;

        mockMvc.perform(get("/department-members")
                        .param("departmentIds", departmentId)
                        .accept(mediaType))
                .andExpect(status().isBadRequest());
    }

    @Test
    void getDepartmentInMember_nonParameter_thenResponseStatusBadRequest() throws Exception {

        mockMvc.perform(get("/department-members")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void getDepartmentInMember_notExistDepartment_thenResponseStatusBadRequest() throws Exception {
        String departmentId = "notExistDepartment";

        given(departmentService.existsDepartment(departmentId)).willReturn(false);

        mockMvc.perform(get("/department-members")
                        .param("departmentIds", departmentId)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void getDepartmentInMember_singleParam_success() throws Exception {

        List<String> departmentIds = List.of("L1001");

        ManagementDTO managementDTO = new ManagementDTO(
                new Department("L1001", "백엔드1팀"),
                new Employee("20202202", "박이름")
        );

        given(departmentService.existsDepartment(departmentIds.get(0))).willReturn(true);
        given(managementService.getByDepartmentIdInEmployee(departmentIds)).willReturn(List.of(managementDTO));

        mockMvc.perform(get("/department-members")
                        .param("departmentIds", departmentIds.get(0))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].department.departmentId", equalTo("L1001")))
                .andExpect(jsonPath("$[0].department.departmentName", equalTo("백엔드1팀")))
                .andExpect(jsonPath("$[0].employee.employeeId", equalTo("20202202")))
                .andExpect(jsonPath("$[0].employee.employeeName", equalTo("박이름")));
    }

    @Test
    void getDepartmentInMember_multiParam_success() throws Exception {

        List<String> departmentIds = List.of("L1001", "L1002");

        ManagementDTO firstValue = new ManagementDTO(
                new Department("L1001", "백엔드1팀"),
                new Employee("20202202", "박이름")
        );

        ManagementDTO secValue = new ManagementDTO(
                new Department("L1002", "백엔드2팀"),
                new Employee("20202202", "박이름")
        );


        given(departmentService.existsDepartment(departmentIds.get(0))).willReturn(true);
        given(departmentService.existsDepartment(departmentIds.get(1))).willReturn(true);
        given(managementService.getByDepartmentIdInEmployee(departmentIds)).willReturn(List.of(firstValue, secValue));

        mockMvc.perform(get("/department-members")
                        .param("departmentIds", departmentIds.get(0), departmentIds.get(1))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].department.departmentId", equalTo("L1001")))
                .andExpect(jsonPath("$[0].department.departmentName", equalTo("백엔드1팀")))
                .andExpect(jsonPath("$[0].employee.employeeId", equalTo("20202202")))
                .andExpect(jsonPath("$[0].employee.employeeName", equalTo("박이름")))
                .andExpect(jsonPath("$[1].department.departmentId", equalTo("L1002")))
                .andExpect(jsonPath("$[1].department.departmentName", equalTo("백엔드2팀")))
                .andExpect(jsonPath("$[1].employee.employeeId", equalTo("20202202")))
                .andExpect(jsonPath("$[1].employee.employeeName", equalTo("박이름")));
    }


}