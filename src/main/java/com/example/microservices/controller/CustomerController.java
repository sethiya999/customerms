package com.example.microservices.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
//import org.springframework.cloud.client.ServiceInstance;
//import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.microservices.dto.CustomerDTO;
import com.example.microservices.dto.ProductDTO;
import com.example.microservices.entity.Customer;
import com.example.microservices.service.CustomerService;
//import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@EnableAutoConfiguration
@RestController
@RequestMapping("/customers")
public class CustomerController {

//	@Autowired
//	DiscoveryClient client;
	
	@Autowired
	CustomerService service;

	RestTemplate template = new RestTemplate();

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
	ResponseEntity<Customer> getCustomerById(@PathVariable Long id) {
		return service.getCustomerById(id);
	}

	
	public CustomerDTO getDefaultProducts(Long id) {
		return new CustomerDTO();
	}
	
	@RequestMapping(value = "/{id}/toys", method = RequestMethod.GET, produces = "application/json")
//	@HystrixCommand(fallbackMethod = "getDefaultProducts")
	CustomerDTO getCustomerProducts(@PathVariable Long id) {
		if(id==999) {
			throw new RuntimeException("Invalid customer id");
		}
		ResponseEntity<Customer> entity = service.getCustomerById(id);
		Customer cust = entity.getBody();

		CustomerDTO dto = new CustomerDTO();
		dto.setId(cust.getId());
		dto.setName(cust.getName());
		List<ProductDTO> toys = new ArrayList<>();
		
		//way1
		ProductDTO product = template.getForObject("http://localhost:8090/products/" + id,
				ProductDTO.class);
		
		//way2- get instances from eureka
//		List<ServiceInstance> productMSInstance = client.getInstances("productms");
//		URI uri = productMSInstance.get(0).getUri();
//		
//		System.out.println("*****************************" + uri.toString());
//		ProductDTO product = template.getForObject(uri + "/products/" + id, ProductDTO.class);
//		
		toys.add(product);
		dto.setToys(toys);
		
		return dto;
	}

}
