package com.backend.LeavesService.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.backend.LeavesService.Entity.Leaves;
import com.backend.LeavesService.Service.LeavesServices;

@CrossOrigin("*")
@RestController
public class LeavesController {
	
	@Autowired
	private LeavesServices leavesServices;
	
	@GetMapping("/leaves/all")
	public List<Leaves> getAllLeaves(){
		return leavesServices.getAllLeaves();
	}
	
	@GetMapping("/leaves/{id}")
	public List<Leaves> getLeavesByUserId(@PathVariable Integer id, @RequestHeader(name=HttpHeaders.AUTHORIZATION) String token) {
		return leavesServices.getLeaveByUserId(id,token);
	}
	
	@PostMapping("/leaves/save")
	public Leaves saveLeaves(@RequestBody Leaves leaves, @RequestHeader(name=HttpHeaders.AUTHORIZATION) String token) {
		return leavesServices.saveLeaves(leaves,token);
	}

}
