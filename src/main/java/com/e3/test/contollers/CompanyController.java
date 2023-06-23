package com.e3.test.contollers;

import com.e3.test.data.CompanyRepository;
import org.springframework.web.bind.annotation.*;

import com.e3.test.models.Company;

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
