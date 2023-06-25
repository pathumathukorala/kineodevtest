package com.e3.service;

import com.e3.test.entity.Company;
import com.e3.test.repository.CompanyRepository;
import com.e3.test.repository.EmployeeRepository;
import com.e3.test.service.CompanyService;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class CompanyServiceTest {
    private CompanyService companyService;
    private CompanyRepository companyRepository;
    private EmployeeRepository employeeRepository;

    @Before
    public void initialize() {
        companyRepository = mock(CompanyRepository.class);
        employeeRepository = mock(EmployeeRepository.class);
        companyService = new CompanyService(companyRepository, employeeRepository);
    }

    @Test
    public void testDeleteCompanyAfterVerificationSuccess() {
        when(employeeRepository.countByCompanyId(anyLong())).thenReturn(0L);
        doNothing().when(companyRepository).delete(anyLong());

        companyService.deleteCompanyAfterVerification(1L);

        verify(employeeRepository, times(1)).countByCompanyId(1L);
        verify(companyRepository, times(1)).delete(1L);
    }

    @Test
    public void testDeleteCompanyAfterVerificationFailure() {
        when(employeeRepository.countByCompanyId(anyLong())).thenReturn(0L);

        try {
            companyService.deleteCompanyAfterVerification(1L);
        }
        catch (Exception ex) {
            verify(employeeRepository, times(1)).countByCompanyId(1L);
            verify(companyRepository, times(0)).delete(1L);
            assertTrue(ex.getMessage().contains("ERR_001"));
        }
    }

    @Test
    public void testFindCompanyByIdSuccess() {
        when(companyRepository.findOne(anyLong())).thenReturn(new Company());

        companyService.findCompanyById(1L);

        verify(companyRepository, times(1)).findOne(1L);
    }

    @Test
    public void testFindCompanyByIdFailure() {
        when(companyRepository.findOne(anyLong())).thenReturn(null);

        try {
            companyService.findCompanyById(1L);
        }
        catch (Exception ex) {
            verify(companyRepository, times(1)).findOne(1L);
            assertTrue(ex.getMessage().contains("ERR_002"));
        }
    }

}
