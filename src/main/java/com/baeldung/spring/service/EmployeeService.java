package com.baeldung.spring.service;

import com.baeldung.spring.dao.IEmployeeDAO;

public class EmployeeService {
	
	private IEmployeeDAO employeeDAO;
	
	public void setEmployeeDAO(IEmployeeDAO employeeDAO) {
		this.employeeDAO = employeeDAO;
	}
	
	public void printEmployeeMessage() {
		System.out.println(employeeDAO.getMessage());
	}
	
}
