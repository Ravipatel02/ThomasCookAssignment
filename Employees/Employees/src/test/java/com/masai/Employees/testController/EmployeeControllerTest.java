package com.masai.Employees.testController;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.masai.Employees.Controller.EmployeeController;
import com.masai.Employees.DTO.EmployeeDTO;
import com.masai.Employees.DTO.EmployeeRequest;
import com.masai.Employees.DTO.EmployeeResponse;
import com.masai.Employees.Service.EmployeeService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class EmployeeControllerTest {

    @InjectMocks
    private EmployeeController employeeController;

    @Mock
    private EmployeeService employeeService;

    @Test
    void testSaveEmployee() {
        // Given
        EmployeeRequest request = new EmployeeRequest();
        List<EmployeeDTO> employees = new ArrayList<>();
        EmployeeDTO employee = new EmployeeDTO();
        employee.setEmpName("John");
        employee.setAmount(1000.0);
        employee.setCurrency("USD");
        employee.setJoiningDate("2020-01-01");
        employee.setExitDate("2025-01-01");
        employee.setDepartment("IT");
        employees.add(employee);
        request.setEmployees(employees);

        // When
        String response = employeeController.saveEmployee(request);

        // Then
        assertEquals("Employee bonuses saved successfully!", response);
        verify(employeeService).saveEmployee(anyList());
    }



}
