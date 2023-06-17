package com.backend.EmployeeService.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.backend.EmployeeService.Entity.Employee;
import com.backend.EmployeeService.Entity.Leaves;
import com.backend.EmployeeService.Entity.LoginDTO;
import com.backend.EmployeeService.Entity.Tax;
import com.backend.EmployeeService.Exceptions.EmployeeNotFoundException;
import com.backend.EmployeeService.Exceptions.InputErrorException;
import com.backend.EmployeeService.Repository.EmployeeRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class EmployeeService {
	
	@Autowired
	private EmployeeRepository empRepo;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private PasswordEncoder encoder;
	
	public List<Employee> getAllEmployee(){
		return empRepo.findAll();
	}
	
	public Optional<Employee> getEmployeeById(Integer id) {
		Optional<Employee> temp= empRepo.findById(id);
		if(temp.isEmpty()) {
			throw new EmployeeNotFoundException("Employee not found");
		}
		return temp;
	}
	
	public Optional<Employee> getEmployeeByName(String name){
		Optional<Employee> temp = Optional.of(empRepo.getEmployeeByName(name));
		if(temp.isEmpty()) {
			throw new EmployeeNotFoundException("Employee not found");
		}
		return temp;
	}
	
	public Employee saveEmployee(Employee employee) {
		if(employee.getName()==null) {
			throw new InputErrorException("Name field cannot be null");
		}
//		else if(employee.getAddress()==null) {
//			throw new InputErrorException("Address field cannot be null");
//		}
//		else if(employee.getPassword()==null) {
//			throw new InputErrorException("Password field cannot be null");
//		}
//		else if(employee.getAge()==0) {
//			throw new InputErrorException("Age should be greater than 0");
//		}
//		else if(employee.getRole()==null) {
//			throw new InputErrorException("Role field cannot be null");
//		}
		employee.setPassword(encoder.encode(employee.getPassword()));
		return empRepo.save(employee);
	}
	
	public Employee updateEmployee(Employee employee, Integer id) {
		Optional<Employee> e= empRepo.findById(id);
		if(e.isEmpty()) {
			throw new EmployeeNotFoundException("Employee not found");
		}
		Employee emp= e.get();
		emp.setAddress(employee.getAddress());
		emp.setAge(employee.getAge());
		emp.setName(employee.getName());
		return empRepo.save(emp);
	}
	
	public String deleteEmployee(Integer id) {
		empRepo.deleteById(id);
		return "Emplyee of id "+id+" is deleted";
	}
	
	public Employee getAllLeavesByUserId(Integer id, String token){
		
		Optional<Employee> e= empRepo.findById(id);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("Authorization", token);
		HttpEntity<Void> requestEntity= new HttpEntity<>(headers);
		
		if(e.isEmpty()) {
			throw new EmployeeNotFoundException("Employee not found");
		}
		Employee emp= e.get();
		
		//List<Leaves> leaves= restTemplate.getForObject("http://LEAVES-SERVICE:8092/leaves/"+emp.getId(), List.class);
		ResponseEntity<List> leaves= restTemplate.exchange("http://LEAVES-SERVICE:8092/leaves/"+emp.getId(),HttpMethod.GET, requestEntity,List.class);
		List<Leaves> leavesByUser= leaves.getBody();
		System.out.println(leavesByUser);
		emp.setAllLeaves(leavesByUser);
		return emp;
	}
	
	public Tax getTaxByEmpId(Integer id, String token) throws JsonMappingException, JsonProcessingException {
		
		ObjectMapper mapper= new ObjectMapper();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("Authorization", token);
		
		HttpEntity<Void> requestEntity= new HttpEntity<>(headers);
		
		Optional<Employee> e = empRepo.findById(id);
		if(e.isEmpty()) {
			throw new EmployeeNotFoundException("Employee not found");
		}
		Employee emp= e.get();
		
		//String tax= restTemplate.getForObject("http://TAX-SERVICE:8093/tax/find/"+emp.getId(), String.class);
		ResponseEntity<String> tax= restTemplate.exchange("http://TAX-SERVICE:8093/tax/find/"+emp.getId(), HttpMethod.GET, requestEntity, String.class);
		System.out.println(tax);
		String respo= tax.getBody().toString();
		
		Tax t= mapper.readValue(respo, Tax.class);
		return t;
		
	}
	
	public Employee logInService(LoginDTO logInDto) {
		Optional<Employee> e= Optional.of(empRepo.getEmployeeByName(logInDto.getName()));
		Employee emp= e.get();
		if(e.isEmpty()) {
			throw new EmployeeNotFoundException("Employee not found");
		}
		else {
			if(emp.getPassword()==logInDto.getPassword())
				return emp;
		}
		return emp;
	}
	
	
}
