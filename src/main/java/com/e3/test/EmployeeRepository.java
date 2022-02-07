package com.e3.test;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.e3.test.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
