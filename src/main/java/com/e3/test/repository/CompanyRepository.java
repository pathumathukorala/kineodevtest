package com.e3.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.e3.test.entity.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
}
