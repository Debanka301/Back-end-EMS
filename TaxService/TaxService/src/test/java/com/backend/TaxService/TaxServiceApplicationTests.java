package com.backend.TaxService;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.backend.TaxService.Repository.TaxRepository;
import com.backend.TaxService.TaxService.TaxService;

@SpringBootTest
class TaxServiceApplicationTests {
	
	@Mock
	TaxRepository taxRepo;
	
	@InjectMocks
	TaxService taxService;

	@Test
	void contextLoads() {
	}
	
	@Test
	void testCalculateTaxPerc() {
		int expected=20;
		assertEquals(expected, taxService.calculateTaxPerc(1200000));
	}
	
	@Test
	void testCalculateTaxAmount() {
		int expected=240000;
		assertEquals(expected, taxService.calculateTaxAmount(1200000, 20));
	}
	
	@Test
	void testCalculateInHand() {
		int expected=960000;
		assertEquals(expected, taxService.calculateInHand(1200000, 240000));
	}

}
