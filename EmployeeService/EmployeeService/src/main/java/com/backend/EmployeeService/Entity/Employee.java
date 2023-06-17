package com.backend.EmployeeService.Entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="Employee")
public class Employee {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int id;
	
	@Column
//	@NotNull(message = "Name cannot be blank")
//	@Size(min = 3, max = 20)
	private String name;
	
	@Column
	//@NotNull(message = "Password cannot be blank")
	private String password;
	
	@Column
	//@NotNull(message = "Age cannot be blank")
	private int age;
	
	@Column
	//@NotNull(message = "Address cannot be blank")
	//@Size(min= 3, max= 20)
	private String address;
	
	@Column
	//@NotNull(message = "Role cannot be blank")
	private String role;
	
	@OneToMany(mappedBy = "leaveId")
	private List<Leaves> allLeaves;
	

	public Employee() {
		
	}

//	public Employee(int id, String name, int age, String address, List<Leaves> allLeaves) {
//		super();
//		this.id = id;
//		this.name = name;
//		this.age = age;
//		this.address = address;
//		this.allLeaves = allLeaves;
//	}	
	


	public Employee(int id, String name, String password, int age, String address, String role,
			List<Leaves> allLeaves) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.age = age;
		this.address = address;
		this.role = role;
		this.allLeaves = allLeaves;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public List<Leaves> getAllLeaves() {
		return allLeaves;
	}

	public void setAllLeaves(List<Leaves> allLeaves) {
		this.allLeaves = allLeaves;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	
	
	
	

}
