package com.backend.TaxService.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.backend.TaxService.Entity.Tax;
import com.backend.TaxService.Entity.TaxInput;
import com.backend.TaxService.TaxService.TaxService;

@CrossOrigin("*")
@RestController
public class TaxController {
	
	@Autowired
	private TaxService taxService;
	
	@GetMapping("/tax/all")
	public List<Tax> getAllTaxes(){
		return taxService.getAllTaxes();
	}
	
	@PostMapping("/tax/save")
	public Tax saveTax(@RequestBody TaxInput taxInput, @RequestHeader(name=HttpHeaders.AUTHORIZATION) String token) {
		return taxService.saveTax(taxInput, token);
	}
	
	@GetMapping("/tax/find/{empId}")
	public Tax getTaxByEmpId(@PathVariable Integer empId, @RequestHeader(name=HttpHeaders.AUTHORIZATION) String token) {
		return taxService.getTaxByEmpId(empId, token);
	}
	

}
