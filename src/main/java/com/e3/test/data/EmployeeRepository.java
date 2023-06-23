package com.e3.test.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.e3.test.models.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
