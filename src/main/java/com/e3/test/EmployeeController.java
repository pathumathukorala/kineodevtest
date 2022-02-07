package com.e3.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.e3.test.model.Employee;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	EmployeeRepository employeeRepository;


	@RequestMapping("/{employeeId}")
	public @ResponseBody
	Employee getEmployee(@PathVariable Long employeeId) {
		return employeeRepository.findOne(employeeId);
	}

}
