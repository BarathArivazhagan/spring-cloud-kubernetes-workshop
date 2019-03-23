package com.barath.customer.app.service;

import java.net.URI;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.barath.customer.app.dto.Bank;


@Service
public class BankDetailService{
	
	private final RestTemplate restTemplate;
	private final LoadBalancerClient loadBalancerClient;
	
	@Value("${bank.service.name:bank-microservice}")
	private String bankServiceName;

	public BankDetailService(RestTemplate restTemplate, LoadBalancerClient loadBalancerClient) {
		this.restTemplate = restTemplate;
		this.loadBalancerClient= loadBalancerClient;
	}

	public Bank getBankDetails(Long bankId) {
		
		ServiceInstance instance= loadBalancerClient.choose(bankServiceName);
		URI uri = URI.create(String.format("http://%s:%s".concat("/bank?id=%d"), instance.getHost(), instance.getPort(),bankId));
		System.out.println("URL ==> "+Objects.toString(uri));
		return this.restTemplate.getForObject(Objects.toString(uri), Bank.class);
	}
	
}