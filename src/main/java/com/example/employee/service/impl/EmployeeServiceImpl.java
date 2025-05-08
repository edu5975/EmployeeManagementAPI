package com.example.employee.service.impl;

import com.example.employee.model.Employee;
import com.example.employee.repository.EmployeeRepository;
import com.example.employee.service.EmployeeService;
import com.example.employee.service.EventLogService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private EventLogService eventLogService;

    @Override
    public List<Employee> saveEmployees(List<Employee> employees) {
        List<Employee> listEmployees = new ArrayList<Employee>();
        for (Employee employee : employees) {
            listEmployees.add(saveEmployee(employee));
        }
        return listEmployees;
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        logger.info("Saving employee: {}", employee.getFirstName());
        try {
            eventLogService.log("CREATE", "employee", employee.getId(), employee.toString());
            return employeeRepository.save(employee);
        } catch (Exception e) {
            logger.error("Error saving employee: {}", employee.getFirstName(), e);
            throw new RuntimeException("Error saving employee", e);
        }
    }

    @Override
    public List<Employee> getAllEmployees() {
        logger.info("Fetching all employees");
        try {
            List<Employee> employees = employeeRepository.findAll();
            eventLogService.log("READ", "employee", null, "Size: " + employees.size());
            return employees;
        } catch (Exception e) {
            logger.error("Error fetching employees", e);
            throw new RuntimeException("Error fetching employees", e);
        }
    }

    @Override
    public Employee getEmployee(String id) {
        logger.info("Fetching employe");
        try {
            // Assuming the employee exists, otherwise throw an exception
            Employee employee = employeeRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Employee not found"));
            eventLogService.log("READ", "employee", id, employee.toString());
            return employee;
        } catch (Exception e) {
            logger.error("Error fetching employee", e);
            throw new RuntimeException("Error fetching employee", e);
        }
    }

    @Override
    public boolean deleteEmployee(String id) {
        logger.info("Deleting employee with ID: {}", id);
        try {
            employeeRepository.deleteById(id);
            eventLogService.log("DELETE", "employee", id, null);
            return true;
        } catch (Exception e) {
            logger.error("Error deleting employee with ID: {}", id, e);
            throw new RuntimeException("Error deleting employee", e);
        }
    }

    @Override
    public Employee updateEmployee(String id, Employee employee) {
        logger.info("Updating employee with ID: {}", id);
        try {
            // Assuming the employee exists, otherwise throw an exception
            Employee existingEmployee = employeeRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Employee not found"));
            existingEmployee.setFirstName(employee.getFirstName());
            existingEmployee.setMiddleName(employee.getMiddleName());
            existingEmployee.setLastName(employee.getLastName());
            existingEmployee.setAge(employee.getAge());
            existingEmployee.setGender(employee.getGender());
            existingEmployee.setBirthDate(employee.getBirthDate());
            existingEmployee.setJobTitle(employee.getJobTitle());

            eventLogService.log("UPDATE", "employee", id, employee.toString());

            return employeeRepository.save(existingEmployee);
        } catch (Exception e) {
            logger.error("Error updating employee with ID: {}", id, e);
            throw new RuntimeException("Error updating employee", e);
        }
    }
}
