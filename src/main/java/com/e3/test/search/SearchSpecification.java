package com.e3.test.search;

import com.e3.test.entity.Company;
import com.e3.test.entity.Employee;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Join;

public class SearchSpecification {

    public static Specification<Employee> hasId(Long id) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.<Long>get("id"), id);
    }

    public static Specification hasFirstName(String firstName) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.<String>get("firstName"), firstName);
    }

    public static Specification hasLastName(String lastName) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.<String>get("lastName"), lastName);
    }

    public static Specification<Employee> hasCompanyId(Long companyId) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.<Long>get("company"), companyId);
    }

    public static Specification<Employee> hasCompanyName(String companyName) {
        return (root, query, criteriaBuilder) -> {
            Join<Company, Employee> companyEmployee = root.join("company");
            return criteriaBuilder.equal(companyEmployee.get("name"), companyName);
        };
    }
}
