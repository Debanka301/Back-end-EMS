package com.backend.EmployeeService.DTO;

import com.backend.EmployeeService.Entity.Employee;


public class AuthResponseDTO {
	
	private String token;
	private Employee employee;
	
	public AuthResponseDTO() {
		
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public AuthResponseDTO(String token, Employee employee) {
		super();
		this.token = token;
		this.employee = employee;
	}
	
	

}
