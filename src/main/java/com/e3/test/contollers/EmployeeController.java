package com.e3.test.contollers;

import com.e3.test.data.EmployeeRepository;
import org.springframework.web.bind.annotation.*;

import com.e3.test.models.Employee;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

	private final EmployeeRepository employeeRepository;

	public EmployeeController(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}

	@RequestMapping(value = "/{employeeId}", method = RequestMethod.GET)
	public @ResponseBody
	Employee getEmployee(@PathVariable Long employeeId) {
		return employeeRepository.findOne(employeeId);
	}

}
