package com.e3.test.contoller;

import com.e3.test.service.CompanyService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.*;

import com.e3.test.entity.Company;

@RestController
@RequestMapping("/company")
public class CompanyController {

	Logger logger = LogManager.getLogger(CompanyController.class);

	private CompanyService companyService;

	public CompanyController(CompanyService companyService) {
		this.companyService = companyService;
	}

	@RequestMapping(value = "/{companyId}", method = RequestMethod.GET)
	public @ResponseBody Company getCompany(@PathVariable Long companyId) {
		logger.debug("Fetching company from ID: " + companyId);
		return companyService.findCompanyById(companyId);
	}

	@RequestMapping(value = {"/{companyId}"}, method = RequestMethod.DELETE)
	public void deleteCompany(@PathVariable Long companyId) {
		logger.debug("Deleting company with the ID: " + companyId);
		companyService.deleteCompanyAfterVerification(companyId);
	}
}
