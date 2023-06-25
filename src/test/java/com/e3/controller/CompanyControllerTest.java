package com.e3.controller;

import com.e3.test.contoller.CompanyController;
import com.e3.test.entity.Company;
import com.e3.test.exception.BusinessException;
import com.e3.test.service.CompanyService;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class CompanyControllerTest {

    private CompanyService companyService;
    private CompanyController companyController;

    @Before
    public void initialize() {
        companyService = mock(CompanyService.class);
        companyController = new CompanyController(companyService);
    }

    @Test
    public void testGetCompanySuccess() {
        Company company = new Company(1L, "TestCompany");
        when(companyService.findCompanyById(anyLong())).thenReturn(company);

        Company expectedCompany = companyController.getCompany(1L);

        assertNotNull(company);
        assertEquals(expectedCompany.getName(), "TestCompany");
        assertEquals(expectedCompany.getId().longValue(), 1L);
        verify(companyService, times(1)).findCompanyById(1L);
    }

    @Test
    public void testGetCompanyFailure() {
        when(companyService.findCompanyById(anyLong())).thenThrow(new BusinessException("String"));

        try {
            companyController.getCompany(999L);
        }
        catch (Exception ex) {
            verify(companyService, times(1)).findCompanyById(999L);
            assertTrue(ex.getMessage().contains("String"));
        }
    }

    @Test
    public void testDeleteCompanySuccess() {
        doNothing().when(companyService).deleteCompanyAfterVerification(anyLong());

        companyController.deleteCompany(1L);

        verify(companyService, times(1)).deleteCompanyAfterVerification(1L);
    }

    @Test
    public void testDeleteCompanyWhenActiveEmployeesPresent() {
        doThrow(new BusinessException("ERR_003")).when(companyService).deleteCompanyAfterVerification(anyLong());

        try{
            companyController.deleteCompany(1L);
        }
        catch (Exception ex) {
            verify(companyService, times(1)).deleteCompanyAfterVerification(1L);
            assertTrue(ex.getMessage().contains("ERR_003"));
        }
    }
}
