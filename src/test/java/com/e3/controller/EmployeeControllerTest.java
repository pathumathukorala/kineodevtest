package com.e3.controller;

import com.e3.test.contoller.EmployeeController;
import com.e3.test.dto.EmployeeRequestDto;
import com.e3.test.dto.EmployeeSearchDto;
import com.e3.test.entity.Company;
import com.e3.test.entity.Employee;
import com.e3.test.exception.BusinessException;
import com.e3.test.service.EmployeeService;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Optional;

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
        Employee employee = Employee.build(1L, "firstName", "lastName",
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
        when(employeeService.getEmployeeById(anyLong())).thenThrow(new BusinessException("String"));

        try {
            employeeController.getEmployee(999L);
        } catch (Exception ex) {
            assertTrue(ex.getMessage().contains("String"));
            verify(employeeService, times(1)).getEmployeeById(999L);
        }
    }

    @Test
    public void testDeleteEmployeeByIdSuccess() {
        doNothing().when(employeeService).deleteEmployeeById(anyLong());

        employeeController.deleteEmployee(1L);

        verify(employeeService, times(1)).deleteEmployeeById(1L);
    }

    @Test
    public void testDeleteEmployeeByIdFailure() {
        doThrow(new BusinessException("ERR_003")).when(employeeService).deleteEmployeeById(anyLong());

        try {
            employeeController.deleteEmployee(1L);
        }
        catch (Exception ex) {
            verify(employeeService, times(1)).deleteEmployeeById(1L);
            assertTrue(ex.getMessage().contains("ERR_003"));
        }
    }

    @Test
    public void testSearchEmployeeSuccess_employeeId() {
        when(employeeService.searchEmployees(anyObject())).thenReturn(new ArrayList<>());

        employeeController.searchEmployees(Optional.of(1L),
                Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty());

        EmployeeSearchDto searchDto = new EmployeeSearchDto();
        searchDto.setEmployeeId(1L);
        verify(employeeService, times(1)).searchEmployees(searchDto);

    }

    @Test
    public void testSearchEmployeeSuccess_firstName() {
        when(employeeService.searchEmployees(anyObject())).thenReturn(new ArrayList<>());

        employeeController.searchEmployees(Optional.empty(),
                Optional.of("firstName"), Optional.empty(), Optional.empty(), Optional.empty());

        EmployeeSearchDto searchDto = new EmployeeSearchDto();
        searchDto.setFirstName("firstName");
        verify(employeeService, times(1)).searchEmployees(searchDto);

    }

    @Test
    public void testSearchEmployeeSuccess_lastName() {
        when(employeeService.searchEmployees(anyObject())).thenReturn(new ArrayList<>());

        employeeController.searchEmployees(Optional.empty(),
                Optional.empty(), Optional.of("lastName"), Optional.empty(), Optional.empty());

        EmployeeSearchDto searchDto = new EmployeeSearchDto();
        searchDto.setLastName("lastName");
        verify(employeeService, times(1)).searchEmployees(searchDto);

    }

    @Test
    public void testSearchEmployeeSuccess_companyId() {
        when(employeeService.searchEmployees(anyObject())).thenReturn(new ArrayList<>());

        employeeController.searchEmployees(Optional.empty(),
                Optional.empty(), Optional.empty(), Optional.of(1L), Optional.empty());

        EmployeeSearchDto searchDto = new EmployeeSearchDto();
        searchDto.setCompanyId(1L);
        verify(employeeService, times(1)).searchEmployees(searchDto);

    }

    @Test
    public void testSearchEmployeeSuccess_companyName() {
        when(employeeService.searchEmployees(anyObject())).thenReturn(new ArrayList<>());

        employeeController.searchEmployees(Optional.empty(),
                Optional.empty(), Optional.empty(), Optional.empty(), Optional.of("companyName"));

        EmployeeSearchDto searchDto = new EmployeeSearchDto();
        searchDto.setCompanyName("companyName");
        verify(employeeService, times(1)).searchEmployees(searchDto);

    }

    @Test
    public void testCreateEmployeeSuccess() {
        when(employeeService.saveEmployee(anyObject())).thenReturn(new Employee());

        EmployeeRequestDto requestDto = new EmployeeRequestDto();
        requestDto.setFirstName("FirstName");
        requestDto.setLastName("LastName");
        requestDto.setCompanyId(1L);

        employeeController.createEmployee(requestDto);

        verify(employeeService, times(1)).saveEmployee(requestDto);
    }
}
