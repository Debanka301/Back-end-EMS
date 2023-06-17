package com.backend.EmployeeService.Entity;

public class LoginDTO {
	
	private String name;
	private String password;
	
	public LoginDTO() {
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public LoginDTO(String name, String password) {
		super();
		this.name = name;
		this.password = password;
	}
	
	

}
