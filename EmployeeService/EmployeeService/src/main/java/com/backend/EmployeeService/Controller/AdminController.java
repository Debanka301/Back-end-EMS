package com.backend.EmployeeService.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.backend.EmployeeService.Service.EmployeeService;

@RestController
@CrossOrigin("*")
public class AdminController {
	
	@Autowired
	private EmployeeService empService;
	
	

}
