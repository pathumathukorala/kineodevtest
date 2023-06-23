package com.e3.controllers;

import com.e3.test.contoller.CompanyController;
import com.e3.test.repository.CompanyRepository;
import com.e3.test.entity.Company;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class CompanyControllerTest {

    private CompanyRepository companyRepository;
    private CompanyController companyController;

    @Before
    public void initialize() {
        companyRepository = mock(CompanyRepository.class);
        companyController = new CompanyController(companyRepository);
    }

    @Test
    public void testGetCompanySuccess() {
        Company company = new Company(1L, "TestCompany");
        when(companyRepository.findOne(anyLong())).thenReturn(company);

        Company expectedCompany = companyController.getCompany(1L);

        assertNotNull(company);
        assertEquals(expectedCompany.getName(), "TestCompany");
        assertEquals(expectedCompany.getId().longValue(), 1L);
    }

    @Test
    public void testGetCompanyFailure() {
        when(companyRepository.findOne(anyLong())).thenReturn(null);

        Company expectedCompany = companyController.getCompany(999L);

        assertNull(expectedCompany);
    }
}
