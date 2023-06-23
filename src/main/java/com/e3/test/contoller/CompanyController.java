package com.e3.test.contoller;

import com.e3.test.repository.CompanyRepository;
import org.springframework.web.bind.annotation.*;

import com.e3.test.entity.Company;

@RestController
@RequestMapping("/company")
public class CompanyController {


	private final CompanyRepository companyRepository;

	public CompanyController(CompanyRepository companyRepository) {
		this.companyRepository = companyRepository;
	}

	@RequestMapping(value = "/{companyId}", method = RequestMethod.GET)
	public @ResponseBody Company getCompany(@PathVariable Long companyId) {
		return companyRepository.findOne(companyId);
	}
}
