package com.e3.test.contoller;

import com.e3.test.repository.CompanyRepository;
import com.e3.test.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.e3.test.entity.Company;

@RestController
@RequestMapping("/company")
public class CompanyController {

	private CompanyService companyService;

	public CompanyController(CompanyService companyService) {
		this.companyService = companyService;
	}

	@RequestMapping(value = "/{companyId}", method = RequestMethod.GET)
	public @ResponseBody Company getCompany(@PathVariable Long companyId) {
		return companyService.findCompanyById(companyId);
	}

	@RequestMapping(value = {"/{companyId}"}, method = RequestMethod.DELETE)
	public void deleteCompany(@PathVariable Long companyId) {
		companyService.deleteCompanyAfterVerification(companyId);
	}
}