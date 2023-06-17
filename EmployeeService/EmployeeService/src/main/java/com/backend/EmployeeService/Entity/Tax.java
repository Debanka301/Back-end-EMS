package com.backend.EmployeeService.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Tax")
public class Tax {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int taxId;
	
	@Column
	private int empId;
	
	@Column
	private int salary;
	
	@Column
	private int taxPerc;
	
	@Column
	private int taxAmount;
	
	@Column
	private int inHand;
	
	public Tax(int taxId, int empId, int salary, int taxPerc, int taxAmount, int inHand) {
		super();
		this.taxId = taxId;
		this.empId = empId;
		this.salary = salary;
		this.taxPerc = taxPerc;
		this.taxAmount = taxAmount;
		this.inHand = inHand;
	}
	
	public Tax() {
		
	}

	public int getTaxId() {
		return taxId;
	}

	public void setTaxId(int taxId) {
		this.taxId = taxId;
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

	public int getTaxPerc() {
		return taxPerc;
	}

	public void setTaxPerc(int taxPerc) {
		this.taxPerc = taxPerc;
	}

	public int getTaxAmount() {
		return taxAmount;
	}

	public void setTaxAmount(int taxAmount) {
		this.taxAmount = taxAmount;
	}

	public int getInHand() {
		return inHand;
	}

	public void setInHand(int inHand) {
		this.inHand = inHand;
	}

	@Override
	public String toString() {
		return "Tax [taxId=" + taxId + ", empId=" + empId + ", salary=" + salary + ", taxPerc=" + taxPerc
				+ ", taxAmount=" + taxAmount + ", inHand=" + inHand + "]";
	}
	
	
	
	
	
	
	

}
