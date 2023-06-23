package com.e3.test.contoller;

import com.e3.test.repository.EmployeeRepository;
import org.springframework.web.bind.annotation.*;

import com.e3.test.entity.Employee;

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

	@DeleteMapping(value = "/{employeeId}")
	public void deleteEmployee(@PathVariable Long employeeId) {
		employeeRepository.delete(employeeId);
	}

}
