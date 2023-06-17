package com.backend.TaxService.Entity;

public class TaxInput {
	
	private int empId;
	private int salary;
	
	public TaxInput(int empId, int salary) {
		super();
		this.empId = empId;
		this.salary = salary;
	}

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}
	
	
	
	

}
