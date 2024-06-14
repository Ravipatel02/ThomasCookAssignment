package com.masai.Employees.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.masai.Employees.DTO.EmployeeDTO;
import com.masai.Employees.DTO.EmployeeRequest;
import com.masai.Employees.DTO.EmployeeResponse;
import com.masai.Employees.Entity.Department;
import com.masai.Employees.Entity.Employee;
import com.masai.Employees.Service.EmployeeService;

@RestController
@RequestMapping("/tci")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/employee-bonus")
    public String saveEmployee(@RequestBody EmployeeRequest request) {
        List<EmployeeDTO> employees = request.getEmployees();
        List<Employee> empList = new ArrayList<Employee>();
        
        for (EmployeeDTO employee : employees) {
        	Employee emp = new Employee();
        	emp.setEmpName(employee.getEmpName());
        	emp.setAmount(employee.getAmount());
        	emp.setCurrency(employee.getCurrency());
            emp.setJoiningDate(employeeService.convertStringToDate(employee.getJoiningDate()));
            emp.setExitDate(employeeService.convertStringToDate(employee.getExitDate()));
            Department dp = new Department();
            dp.setName(employee.getDepartment());
            emp.setDepartment(dp);
            empList.add(emp);
        }
        employeeService.saveEmployee(empList);
        return "Employee bonuses saved successfully!";
    }
    @GetMapping("/employee-bonus")
    public EmployeeResponse getEligibleEmployees(@RequestParam("date") String date) {
        return employeeService.getEligibleEmployees(date);
    }
}