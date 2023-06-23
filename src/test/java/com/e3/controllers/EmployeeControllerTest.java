package com.e3.controllers;

import com.e3.test.contoller.EmployeeController;
import com.e3.test.repository.EmployeeRepository;
import com.e3.test.entity.Company;
import com.e3.test.entity.Employee;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class EmployeeControllerTest {
    private EmployeeRepository employeeRepository;
    private EmployeeController employeeController;

    @Before
    public void initialize() {
        employeeRepository = mock(EmployeeRepository.class);
        employeeController = new EmployeeController(employeeRepository);
    }

    @Test
    public void testGetEmployeeSuccess() {
        Employee employee = new Employee(1L, "firstName", "lastName",
                new Company(1L, "TestCompany"));

        when(employeeRepository.findOne(anyLong())).thenReturn(employee);

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
        when(employeeRepository.findOne(anyLong())).thenReturn(null);

        Employee expectedEmployee = employeeController.getEmployee(999L);

        assertNull(expectedEmployee);
    }
}
