package com.backend.TaxService.TaxService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.backend.TaxService.Entity.Tax;
import com.backend.TaxService.Entity.TaxInput;
import com.backend.TaxService.Repository.TaxRepository;

@Service
public class TaxService {
	
	@Autowired
	private TaxRepository taxRepo;
	
	@Autowired
	private RestTemplate restTemplate;
	
	public List<Tax> getAllTaxes(){
		return taxRepo.findAll();
	}
	
	public Tax saveTax(TaxInput taxInput, String token) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("Authorization", token);
		
		HttpEntity<Void> requestEntity= new HttpEntity<>(headers);
		ResponseEntity<String> res= restTemplate.exchange("http://EMPLOYEE-SERVICE/validateToken", HttpMethod.GET, requestEntity, String.class);
		
		String respo= res.getBody().toString();
		System.out.println(respo);
		
		if(respo.equals("valid")) {
			Tax tax= new Tax();
			tax.setEmpId(taxInput.getEmpId());
			tax.setSalary(taxInput.getSalary());
			tax.setTaxPerc(calculateTaxPerc(tax.getSalary()));
			tax.setTaxAmount(calculateTaxAmount(tax.getSalary(), tax.getTaxPerc()));
			tax.setInHand(calculateInHand(tax.getSalary(), tax.getTaxAmount()));
			
			return taxRepo.save(tax);
		}else {
			System.out.println("Token invalid");
			return null;
		}
		
	}
	
	public int calculateTaxPerc(Integer salary) {
		if(salary>=1500000) {
			return 30;
		}
		else if(salary>=1000000 && salary<1500000) {
			return 20;
		}
		else if(salary>=700000 && salary<1000000) {
			return 10;
		}
		else {
			return 5;
		}
	}
	
	public int calculateTaxAmount(Integer salary, Integer taxPerc) {
		return (salary*taxPerc)/100;
	}
	
	public int calculateInHand(Integer salary, Integer taxAmount) {
		return salary-taxAmount;
	}
	
	public Tax getTaxByEmpId(Integer empId, String token) {
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("Authorization", token);
		
		HttpEntity<Void> requestEntity= new HttpEntity<>(headers);
		ResponseEntity<String> res= restTemplate.exchange("http://EMPLOYEE-SERVICE/validateToken", HttpMethod.GET, requestEntity, String.class);
		
		String respo= res.getBody().toString();
		System.out.println(respo);
		
		if(respo.equals("valid")) {
			return taxRepo.getTaxByEmpId(empId);
		}
		else {
			System.out.println("Token invalid");
			return null;
		}
	}

}
