package com.masai.Employees.DTO;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter

@Data
public class EmployeeDTO {
    private String empName;
    private String department;
    private double amount;
    private String currency;
    private String joiningDate;
    private String exitDate;
}