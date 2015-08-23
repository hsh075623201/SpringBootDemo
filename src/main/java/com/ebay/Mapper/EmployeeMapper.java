package com.ebay.Mapper;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ebay.Model.Employee;

public interface EmployeeMapper {
	List<Employee> getEmployee();
}
