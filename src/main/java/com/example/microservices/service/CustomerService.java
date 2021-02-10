package com.example.microservices.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.microservices.entity.Customer;
import com.example.microservices.repository.CustomerRepository;

@Service
public class CustomerService {

	@Autowired
	CustomerRepository repo;

	public ResponseEntity<Customer> getCustomerById(Long id) {
		Optional<Customer> optional = repo.findById(id);
		if(!optional.isPresent()) {
			return ResponseEntity.badRequest().build();
		}
		ResponseEntity<Customer> entity = ResponseEntity.ok(optional.get());
		return entity;
	}

}
