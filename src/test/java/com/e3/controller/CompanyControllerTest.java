package com.e3.controller;

import com.e3.test.contoller.CompanyController;
import com.e3.test.entity.Company;
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
    }

    @Test
    public void testGetCompanyFailure() {
        when(companyService.findCompanyById(anyLong())).thenReturn(null);

        Company expectedCompany = companyController.getCompany(999L);

        assertNull(expectedCompany);
    }
}
