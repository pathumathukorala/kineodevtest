package com.e3.controllers;

import com.e3.test.contoller.EmployeeController;
import com.e3.test.entity.Company;
import com.e3.test.entity.Employee;
import com.e3.test.service.EmployeeService;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class EmployeeControllerTest {
    private EmployeeService employeeService;
    private EmployeeController employeeController;

    @Before
    public void initialize() {
        employeeService = mock(EmployeeService.class);
        employeeController = new EmployeeController(employeeService);
    }

    @Test
    public void testGetEmployeeSuccess() {
        Employee employee = new Employee(1L, "firstName", "lastName",
                new Company(1L, "TestCompany"));

        when(employeeService.getEmployeeById(anyLong())).thenReturn(employee);

        Employee expectedEmployee = employeeController.getEmployee(1L);

        assertNotNull(expectedEmployee);
        assertNotNull(expectedEmployee.getCompany());
        assertEquals(expectedEmployee.getId().longValue(), 1L);
        assertEquals(expectedEmployee.getFirstName(), "firstName");
        assertEquals(expectedEmployee.getLastName(), "lastName");
        assertEquals(expectedEmployee.getCompany().getId().longValue(), 1L);
        assertEquals(expectedEmployee.getCompany().getName(), "TestCompany");
    }

    @Test
    public void testGetEmployeeFailure() {
        when(employeeService.getEmployeeById(anyLong())).thenReturn(null);

        Employee expectedEmployee = employeeController.getEmployee(999L);

        assertNull(expectedEmployee);
    }
}
