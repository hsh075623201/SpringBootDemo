package com.ebay.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ebay.Model.Employee;
import com.ebay.Service.EmployeeService;

@RestController
public class EmployeeController {
	@Autowired
	@Qualifier("employeeService")
	private EmployeeService employeeService;
	@RequestMapping("/getEmployee")
	public List<Employee> getEmployee(){
		System.out.println("EmployeeController.......getEmployee()");
		return employeeService.getEmployee();
	}
}
