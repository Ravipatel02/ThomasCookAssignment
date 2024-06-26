package com.masai.Employees.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.masai.Employees.Entity.Employee;
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
