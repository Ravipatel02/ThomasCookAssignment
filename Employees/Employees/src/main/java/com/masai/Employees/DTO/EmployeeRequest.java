package com.masai.Employees.DTO;

import java.util.List;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class EmployeeRequest {
    private List<EmployeeDTO> employees;
}