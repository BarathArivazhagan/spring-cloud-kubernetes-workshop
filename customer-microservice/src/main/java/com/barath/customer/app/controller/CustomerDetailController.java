package com.barath.customer.app.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.barath.customer.app.dto.Bank;
import com.barath.customer.app.entity.Customer;

import com.barath.customer.app.service.CustomerService;

@RestController
public class CustomerDetailController {
	

	
	private final CustomerService customerService;
	private final BankService bankService;


	public CustomerDetailController(CustomerService customerService, BankService bankService) {
		this.bankService=bankService;
		this.customerService=customerService;
	}

	@GetMapping(value="/customer/details")
	public Map<String,Object> getCustomerBankDetails(@RequestParam Long customerId){
		
		Map<String,Object> response = new HashMap<>();
		Customer customer = customerService.getCustomer(customerId).get();
		response.put("customer", customer);
		Bank bank=bankService.getBankDetails(customer.getBankId());
		response.put("bank", bank);
		System.out.println("Result "+bank);
		return response; 
	}
	
	
	@Service
	protected static class BankService{
		
		private final RestTemplate restTemplate;
		
		@Value("${bank.service.name}")
		private String bankServiceName;

		public BankService(RestTemplate restTemplate) {
			this.restTemplate = restTemplate;
		}

		public Bank getBankDetails(Long bankId) {
			
			String url = String.format(bankServiceName.concat("?id=%d"), bankId);
			System.out.println("URL ==> "+url);
			return this.restTemplate.getForObject(url, Bank.class);
		}
		
	}
	

}
