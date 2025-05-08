package com.example.employee.controller;

import com.example.employee.model.Employee;
import com.example.employee.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EmployeeControllerTest {

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private EmployeeController employeeController;

    private Employee employee;

    @BeforeEach
    public void setup() {
        employee = new Employee();
        employee.setId("123");
        employee.setFirstName("John");
        employee.setMiddleName("M.");
        employee.setLastName("Doe");
        employee.setJobTitle("Developer");
        employee.setBirthDate(LocalDate.of(1990, 1, 1));
    }

    @Test
    public void testGetAllEmployees() {
        when(employeeService.getAllEmployees()).thenReturn(List.of(employee));
        ResponseEntity<List<Employee>> response = employeeController.getAllEmployees();
        assertEquals(1, response.getBody().size());
    }

    @Test
    public void testSaveEmployees() {
        List<Employee> employees = Collections.singletonList(employee);
        when(employeeService.saveEmployees(employees)).thenReturn(employees);
        ResponseEntity<List<Employee>> response = employeeController.saveEmployees(employees);
        assertEquals(employees, response.getBody());
    }

    @Test
    public void testUpdateEmployee() {
        when(employeeService.updateEmployee("123", employee)).thenReturn(employee);
        ResponseEntity<Employee> response = employeeController.updateEmployee("123", employee);
        assertEquals(employee, response.getBody());
    }

    @Test
    void testDeleteEmployee() {
        // Simulamos que el método deleteEmployee devolverá true
        when(employeeService.deleteEmployee("123")).thenReturn(true);

        ResponseEntity<String> response = employeeController.deleteEmployee("123");
        System.out.println(response);
        // Verificamos que la respuesta tenga el estado OK (200)
        assertEquals(HttpStatus.OK, response.getStatusCode());

        // Verificamos que el método deleteEmployee fue llamado con el ID correcto
        verify(employeeService, times(1)).deleteEmployee("123");
    }

}
