package com.masai.Employees.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.masai.Employees.DTO.EmployeeResponse;
import com.masai.Employees.Entity.Department;
import com.masai.Employees.Entity.Employee;
import com.masai.Employees.Repository.DepartmentRepository;
import com.masai.Employees.Repository.EmployeeRepository;

import jakarta.transaction.Transactional;

@Service
public class EmployeeService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Transactional
    public void saveEmployee
    (List<Employee> employees) {
        for (Employee employee : employees) {
            Department department = employee.getDepartment();
            if (department == null) {
                department = new Department();
                department.setName(employee.getDepartment().getName());
                departmentRepository.save(department);
            } else {
                department = departmentRepository.findByName(department.getName());
                if (department == null) {
                    department = new Department();
                    department.setName(employee.getDepartment().getName());
                    departmentRepository.save(department);
                }
            }
            employee.setDepartment(department);
            employeeRepository.save(employee);
        }
    }
    
    
    public EmployeeResponse getEligibleEmployees(String date) {
        LocalDate localDate = convertStringToDate(date);
        List<Employee> allEmployees = employeeRepository.findAll();

        List<Employee> eligibleEmployees = allEmployees.stream()
                .filter(employee -> !employee.getJoiningDate().isAfter(localDate) && !employee.getExitDate().isBefore(localDate))
                .sorted((e1, e2) -> e1.getEmpName().compareToIgnoreCase(e2.getEmpName()))
                .collect(Collectors.toList());

        Map<String, List<EmployeeResponse.Employee>> groupedByCurrency = eligibleEmployees.stream()
                .collect(Collectors.groupingBy(Employee::getCurrency,
                        Collectors.mapping(employee -> new EmployeeResponse.Employee(employee.getEmpName(), employee.getAmount()), Collectors.toList())));

        List<EmployeeResponse.CurrencyGroup> currencyGroups = groupedByCurrency.entrySet().stream()
                .map(entry -> new EmployeeResponse.CurrencyGroup(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());

        return new EmployeeResponse("", currencyGroups);
    }
    
    

    public LocalDate convertStringToDate(String date) {
        // Capitalize the first letter of the month
        String[] parts = date.split("-");
        parts[0] = parts[0].substring(0, 1).toUpperCase() + parts[0].substring(1).toLowerCase();
        String formattedDate = String.join("-", parts);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM-dd-yyyy", Locale.ENGLISH);
        return LocalDate.parse(formattedDate, formatter);
    }

	
}