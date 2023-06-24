package com.e3.test.service;

import com.e3.test.entity.Company;
import com.e3.test.repository.CompanyRepository;
import com.e3.test.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

@Service
public class CompanyService {

    private final CompanyRepository companyRepository;
    private final EmployeeRepository employeeRepository;

    public CompanyService(CompanyRepository companyRepository, EmployeeRepository employeeRepository) {
        this.companyRepository = companyRepository;
        this.employeeRepository = employeeRepository;
    }

    public void deleteCompanyAfterVerification(Long companyId) {
        Long count = employeeRepository.countByCompanyId(companyId);

        if (count == 0) {
            companyRepository.delete(companyId);
        }
        else {
            //TODO throw businessexception
        }
    }

    public Company findCompanyById(Long companyId) {
        return companyRepository.findOne(companyId);
    }

}
