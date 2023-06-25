package com.e3.test.service;

import com.e3.test.dto.EmployeeRequestDto;
import com.e3.test.dto.EmployeeSearchDto;
import com.e3.test.entity.Company;
import com.e3.test.entity.Employee;
import com.e3.test.exception.BusinessException;
import com.e3.test.exception.ValidationException;
import com.e3.test.repository.CompanyRepository;
import com.e3.test.repository.EmployeeRepository;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

import static com.e3.test.search.SearchSpecification.*;

@Service
public class EmployeeService {

    Logger logger = LogManager.getLogger(EmployeeService.class);

    private final EmployeeRepository employeeRepository;
    private final CompanyRepository companyRepository;

    public EmployeeService(EmployeeRepository employeeRepository, CompanyRepository companyRepository) {
        this.employeeRepository = employeeRepository;
        this.companyRepository = companyRepository;
    }

    public Employee getEmployeeById(Long employeeId) {
        Employee employee = employeeRepository.findOne(employeeId);

        if (employee == null) {
            logger.error("Attempt to fetch unavailable employee. employeeId: " + employeeId);
            throw new BusinessException("ERR_003");
        }

        logger.info("Employee fetch: " + employee);
        return employee;
    }

    public void deleteEmployeeById(Long employeeId) {
        Employee employee = employeeRepository.findOne(employeeId);

        if (employee == null) {
            logger.error("Attempt to delete unavailable employee. employeeId: " + employeeId);
            throw new BusinessException("ERR_003");
        }

        try {
            employeeRepository.delete(employeeId);
        }
        catch (Exception ex) {
            logger.error("Failure in deleting employee. employeeId: " + employeeId);
            throw new BusinessException("ERR_004");
        }
    }

    public List<Employee> searchEmployees(EmployeeSearchDto searchDto) {

        Specification<Employee> searchSpec = null;

        if (searchDto.getEmployeeId() != null) {
            searchSpec = hasId(searchDto.getEmployeeId());
        }

        if (!StringUtils.isEmpty(searchDto.getFirstName())) {
            searchSpec = hasFirstName(searchDto.getFirstName());
        }

        if (!StringUtils.isEmpty(searchDto.getLastName())) {
            searchSpec = hasLastName(searchDto.getLastName());
        }

        if (searchDto.getCompanyId() != null) {
            searchSpec = hasCompanyId(searchDto.getCompanyId());
        }

        if (!StringUtils.isEmpty(searchDto.getCompanyName())) {
            searchSpec = hasCompanyName(searchDto.getCompanyName());
        }

        return employeeRepository.findAll(searchSpec,
                new Sort(Sort.Direction.ASC, "firstName"));
    }

    public Employee saveEmployee(EmployeeRequestDto requestDto) {

        if (requestDto.getId() == null &&
                employeeRepository.isEmployeeAlreadyRegistered(requestDto.getFirstName(), requestDto.getLastName()) != 0) {
            logger.error("Duplicate employee creation" + requestDto);
            throw new ValidationException("ERR_006");
        }

        Company company = companyRepository.findOne(requestDto.getCompanyId());

        if (company != null) {
            Employee employee = Employee.build(requestDto.getId(),
                    requestDto.getFirstName(), requestDto.getLastName(), company);

            logger.info("Employee created. " + employee);
            return employeeRepository.save(employee);
        }
        else {
            logger.error("Attempt to create employee for invalid company. Request: " + requestDto);
            throw new BusinessException("ERR_002");
        }
    }
}
