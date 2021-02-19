package com.e3.test;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.e3.test.model.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
}
