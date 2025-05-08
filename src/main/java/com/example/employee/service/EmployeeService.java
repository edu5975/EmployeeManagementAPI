package com.example.employee.service;

import com.example.employee.model.Employee;
import java.util.List;

public interface EmployeeService {
    List<Employee> getAllEmployees();

    Employee getEmployee(String id);

    Employee saveEmployee(Employee employee);

    List<Employee> saveEmployees(List<Employee> employees);

    Employee updateEmployee(String id, Employee updatedEmployee);

    boolean deleteEmployee(String id);
}
