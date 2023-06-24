package com.e3.test.service;

import com.e3.test.dto.EmployeeSearchDto;
import com.e3.test.entity.Employee;
import com.e3.test.repository.EmployeeRepository;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

import static com.e3.test.search.SearchSpecification.*;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Employee getEmployeeById(Long employeeId) {
        return employeeRepository.findOne(employeeId);
    }

    public Boolean deleteEmployeeById(Long employeeId) {
        employeeRepository.delete(employeeId);

        // TODO implement businessexception
        return Boolean.TRUE;
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

        List<Employee> employees = employeeRepository.findAll(searchSpec,
                new Sort(Sort.Direction.ASC, "firstName"));

        return employees;
    }
}
