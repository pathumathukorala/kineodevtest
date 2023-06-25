package com.e3.service;

import com.e3.test.entity.Employee;
import com.e3.test.exception.BusinessException;
import com.e3.test.repository.CompanyRepository;
import com.e3.test.repository.EmployeeRepository;
import com.e3.test.service.EmployeeService;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class EmployeeServiceTest {
    private EmployeeService employeeService;
    private CompanyRepository companyRepository;
    private EmployeeRepository employeeRepository;

    @Before
    public void initialize() {
        companyRepository = mock(CompanyRepository.class);
        employeeRepository = mock(EmployeeRepository.class);
        employeeService = new EmployeeService(employeeRepository, companyRepository);
    }

    @Test
    public void testGetEmployeeByIdSuccess() {
        when(employeeRepository.findOne(anyLong())).thenReturn(new Employee());

        employeeService.getEmployeeById(1L);

        verify(employeeRepository, times(1)).findOne(1L);
    }

    @Test
    public void testGetEmployeeByIdFailure() {
        when(employeeRepository.findOne(anyLong())).thenReturn(null);

        try {
            employeeService.getEmployeeById(1L);
        }
        catch (Exception ex) {
            verify(employeeRepository, times(1)).findOne(1L);
            assertTrue(ex.getMessage().contains("ERR_003"));
        }
    }

    @Test
    public void testDeleteEmployeeByIdSuccess() {
        when(employeeRepository.findOne(anyLong())).thenReturn(new Employee());
        doNothing().when(employeeRepository).delete(anyLong());

        employeeService.deleteEmployeeById(1L);

        verify(employeeRepository, times(1)).findOne(1L);
        verify(employeeRepository, times(1)).delete(1L);
    }

    @Test
    public void testDeleteEmployeeByIdFailure_employeeNotFound() {
        when(employeeRepository.findOne(anyLong())).thenReturn(null);

        try {
            employeeService.deleteEmployeeById(1L);
        }
        catch (Exception ex) {
            verify(employeeRepository, times(1)).findOne(1L);
            verify(employeeRepository, times(0)).delete(1L);
            assertTrue(ex.getMessage().contains("ERR_003"));
        }
    }

    @Test
    public void testDeleteEmployeeByIdFailure_jpaException() {
        when(employeeRepository.findOne(anyLong())).thenReturn(new Employee());
        doThrow(new RuntimeException()).when(employeeRepository).delete(anyLong());

        try {
            employeeService.deleteEmployeeById(1L);
        }
        catch (Exception ex) {
            verify(employeeRepository, times(1)).findOne(1L);
            verify(employeeRepository, times(1)).delete(1L);
            assertTrue(ex.getMessage().contains("ERR_004"));
        }
    }

}
