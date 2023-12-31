package com.e3.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.e3.test.entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>, JpaSpecificationExecutor<Employee> {

    Long countByCompanyId(Long companyId);

    @Query("SELECT count(e.id) FROM Employee e WHERE e.firstName = ?1 AND e.lastName = ?2")
    Long isEmployeeAlreadyRegistered(String firstName, String lastName);

}
