package com.e3.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.e3.test.model.Company;

@Controller
@RequestMapping("/company")
public class CompanyController {

	@Autowired
	CompanyRepository companyRepository;

	@RequestMapping("/{companyId}")
	public @ResponseBody Company getCompany(@PathVariable Long companyId) {
		return companyRepository.findOne(companyId);
	}
}
