package com.masai.Employees.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeResponse {
    private String errorMessage;
    private List<CurrencyGroup> data;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CurrencyGroup {
        private String currency;
        private List<Employee> employees;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Employee {
        private String empName;
        private double amount;
    }
}
