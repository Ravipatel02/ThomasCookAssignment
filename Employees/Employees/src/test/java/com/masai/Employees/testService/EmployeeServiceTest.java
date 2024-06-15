package com.masai.Employees.testService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import com.masai.Employees.DTO.EmployeeResponse;
import com.masai.Employees.Entity.Department;
import com.masai.Employees.Entity.Employee;
import com.masai.Employees.Repository.DepartmentRepository;
import com.masai.Employees.Repository.EmployeeRepository;
import com.masai.Employees.Service.EmployeeService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {

    @InjectMocks
    private EmployeeService employeeService;

    @Mock
    private DepartmentRepository departmentRepository;

    @Mock
    private EmployeeRepository employeeRepository;

    @Test
    void testSaveEmployee() {
        // Given
        List<Employee> employees = new ArrayList<>();
        Employee employee1 = new Employee();
        employee1.setDepartment(new Department("IT"));
        Employee employee2 = new Employee();
        employee2.setDepartment(new Department("HR"));
        employees.add(employee1);
        employees.add(employee2);

        when(departmentRepository.findByName("IT")).thenReturn(null);
        when(departmentRepository.findByName("HR")).thenReturn(null);
        when(departmentRepository.save(new Department("IT"))).thenReturn(new Department("IT"));
        when(departmentRepository.save(new Department("HR"))).thenReturn(new Department("HR"));

        // When
        employeeService.saveEmployee(employees);

        // Then
        verify(departmentRepository, times(2)).save(any(Department.class));
        verify(employeeRepository, times(2)).save(any(Employee.class));
    }

    @Test
    void testGetEligibleEmployees() {
        // Given
        List<Employee> employees = new ArrayList<>();
        Employee employee1 = new Employee();
        employee1.setEmpName("John");
        employee1.setJoiningDate(LocalDate.of(2020, 1, 1));
        employee1.setExitDate(LocalDate.of(2025, 1, 1));
        employee1.setCurrency("USD");
        employee1.setAmount(1000.0);

        Employee employee2 = new Employee();
        employee2.setEmpName("Jane");
        employee2.setJoiningDate(LocalDate.of(2022, 1, 1));
        employee2.setExitDate(LocalDate.of(2027, 1, 1));
        employee2.setCurrency("EUR");
        employee2.setAmount(2000.0);

        employees.add(employee1);
        employees.add(employee2);

        when(employeeRepository.findAll()).thenReturn(employees);

        // When
        EmployeeResponse response = employeeService.getEligibleEmployees("Jan-01-2023");

        // Then
        assertNotNull(response);
        assertEquals("", response.getErrorMessage());

        List<EmployeeResponse.CurrencyGroup> expectedCurrencyGroups = new ArrayList<>();
        EmployeeResponse.CurrencyGroup currencyGroup1 = new EmployeeResponse.CurrencyGroup("USD", List.of(new EmployeeResponse.Employee("John", 1000.0)));
        EmployeeResponse.CurrencyGroup currencyGroup2 = new EmployeeResponse.CurrencyGroup("EUR", List.of(new EmployeeResponse.Employee("Jane", 2000.0)));
        expectedCurrencyGroups.add(currencyGroup1);
        expectedCurrencyGroups.add(currencyGroup2);

        List<EmployeeResponse.CurrencyGroup> actualCurrencyGroups = response.getData();
        assertTrue(actualCurrencyGroups.containsAll(expectedCurrencyGroups));
    }

    @Test
    void testConvertStringToDate() {
        // Given
        String date = "jan-01-2023";

        // When
        LocalDate localDate = employeeService.convertStringToDate(date);

        // Then
        assertNotNull(localDate);
        assertEquals(2023, localDate.getYear());
        assertEquals(1, localDate.getMonthValue());
        assertEquals(1, localDate.getDayOfMonth());
    }
}
