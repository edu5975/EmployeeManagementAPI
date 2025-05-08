package com.example.employee.controller;

import com.example.employee.exception.EmployeeNotFoundException;
import com.example.employee.exception.InvalidEmployeeDataException;
import com.example.employee.model.Employee;
import com.example.employee.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    // Obtener todos los empleados
    @GetMapping("{id}")
    public ResponseEntity<Employee> getEmployee(@PathVariable String id) {
        try {
            Employee employee = employeeService.getEmployee(id);
            if (employee == null) {
                throw new EmployeeNotFoundException("No employee found");
            }
            return ResponseEntity.ok(employee);
        } catch (EmployeeNotFoundException ex) {
            throw new EmployeeNotFoundException("No employee found");
        }
    }

    // Obtener todos los empleados
    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees() {
        try {
            List<Employee> employees = employeeService.getAllEmployees();
            if (employees.isEmpty()) {
                throw new EmployeeNotFoundException("No employees found");
            }
            return ResponseEntity.ok(employees);
        } catch (EmployeeNotFoundException ex) {
            throw new EmployeeNotFoundException("No employees found");
        }
    }

    // Crear un nuevo empleado
    @PostMapping("/save")
    public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee) {
        try {
            if (employee.getFirstName() == null || employee.getLastName() == null) {
                throw new InvalidEmployeeDataException("Employee first name or last name cannot be null");
            }
            Employee savedEmployee = employeeService.saveEmployee(employee);
            return ResponseEntity.ok(savedEmployee);
        } catch (InvalidEmployeeDataException ex) {
            throw new InvalidEmployeeDataException("Invalid employee data: " + ex.getMessage());
        }
    }

    // Guardar múltiples empleados
    @PostMapping("/saveAll")
    public ResponseEntity<List<Employee>> saveEmployees(@RequestBody List<Employee> employees) {
        try {
            if (employees.isEmpty()) {
                throw new InvalidEmployeeDataException("Employee list cannot be empty");
            }
            List<Employee> savedEmployees = employeeService.saveEmployees(employees);
            return ResponseEntity.ok(savedEmployees);
        } catch (InvalidEmployeeDataException ex) {
            throw new InvalidEmployeeDataException("Invalid employee data: " + ex.getMessage());
        }
    }

    // Actualizar un empleado existente
    @PutMapping("/update/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable String id, @RequestBody Employee employee) {
        try {
            Employee updatedEmployee = employeeService.updateEmployee(id, employee);
            if (updatedEmployee == null) {
                throw new EmployeeNotFoundException("Employee with ID " + id + " not found");
            }
            return ResponseEntity.ok(updatedEmployee);
        } catch (EmployeeNotFoundException ex) {
            throw new EmployeeNotFoundException("Employee with ID " + id + " not found");
        }
    }

    // Eliminar un empleado
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable String id) {
        try {
            boolean isDeleted = employeeService.deleteEmployee(id);
            if (!isDeleted) {
                throw new EmployeeNotFoundException("Employee with ID " + id + " not found");
            }
            return ResponseEntity.ok("Employee with ID " + id + " deleted successfully");
        } catch (EmployeeNotFoundException ex) {
            throw new EmployeeNotFoundException("Employee with ID " + id + " not found");
        }
    }
}
