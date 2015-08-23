package com.ebay.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import com.ebay.Mapper.EmployeeMapper;
import com.ebay.Model.Employee;


@Service
public class EmployeeService {
	@Autowired
	private EmployeeMapper employeeMapper;
	
	public List<Employee> getEmployee(){
		return employeeMapper.getEmployee();
	}
}
