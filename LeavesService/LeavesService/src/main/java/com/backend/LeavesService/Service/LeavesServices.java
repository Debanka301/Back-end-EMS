package com.backend.LeavesService.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.backend.LeavesService.Entity.Leaves;
import com.backend.LeavesService.Repository.LeavesRepository;

@Service
public class LeavesServices {
	
	@Autowired
	private LeavesRepository LeavesRepo;
	
	@Autowired
	private RestTemplate restTemplate;
	
	public List<Leaves> getAllLeaves(){
		return LeavesRepo.findAll();
	}
	
	public List<Leaves> getLeaveByUserId(Integer userId, String token) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("Authorization", token);
		
		HttpEntity<Void> requestEntity= new HttpEntity<>(headers);
		ResponseEntity<String> res= restTemplate.exchange("http://EMPLOYEE-SERVICE:8091/validateToken", HttpMethod.GET, requestEntity, String.class);
		
		String respo= res.getBody().toString();
		System.out.println(respo);
		if(respo.equals("valid")) {
			return LeavesRepo.getLeavesByEmployeeId(userId);
		}
		else {
			System.out.println("Token invalid");
			return null;
		}
	}
	
	public Leaves saveLeaves(Leaves leaves, String token) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("Authorization", token);
		
		HttpEntity<Void> requestEntity= new HttpEntity<>(headers);
		ResponseEntity<String> res= restTemplate.exchange("http://EMPLOYEE-SERVICE/validateToken", HttpMethod.GET, requestEntity, String.class);
		
		String respo= res.getBody().toString();
		System.out.println(respo);
		
		if(respo.equals("valid")) {
			return LeavesRepo.save(leaves);
		}else{
			System.out.println("Token invalid");
			return null;

		}
	}
	
}
