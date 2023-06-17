package com.backend.EmployeeService.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.backend.EmployeeService.Entity.Employee;
import com.backend.EmployeeService.Repository.EmployeeRepository;

@Component
public class UsersInfoService implements UserDetailsService{
	
	@Autowired
	private EmployeeRepository empRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Employee emp= empRepo.getEmployeeByName(username); 
		return  new UsersInfo(emp);
	}
	
	

}
