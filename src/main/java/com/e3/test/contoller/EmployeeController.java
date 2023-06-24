package com.e3.test.contoller;

import com.e3.test.dto.EmployeeSearchDto;
import com.e3.test.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

import com.e3.test.entity.Employee;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

	private final EmployeeService employeeService;

	public EmployeeController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	@RequestMapping(value = "/{employeeId}", method = RequestMethod.GET)
	public @ResponseBody
	Employee getEmployee(@PathVariable Long employeeId) {
		return employeeService.getEmployeeById(employeeId);
	}

	@RequestMapping(value = "/{employeeId}", method = RequestMethod.DELETE)
	public void deleteEmployee(@PathVariable Long employeeId) {
		employeeService.deleteEmployeeById(employeeId);
	}

	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public @ResponseBody
	List<Employee> searchEmployees(@RequestParam("id") Optional<Long> employeeId,
								   @RequestParam("firstName") Optional<String> firstName,
								   @RequestParam("lastName") Optional<String> lastName,
								   @RequestParam("companyId") Optional<Long> companyId,
								   @RequestParam("companyName") Optional<String> companyName) {

		EmployeeSearchDto searchDto = new EmployeeSearchDto();

		if (employeeId.isPresent()) {
			searchDto.setEmployeeId(employeeId.get());
		}

		if (firstName.isPresent()) {
			searchDto.setFirstName(firstName.get());
		}

		if (lastName.isPresent()) {
			searchDto.setLastName(lastName.get());
		}

		if (companyId.isPresent()) {
			searchDto.setCompanyId(companyId.get());
		}

		if (companyName.isPresent()) {
			searchDto.setCompanyName(companyName.get());
		}

		return employeeService.searchEmployees(searchDto);
	}

}
