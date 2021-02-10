package com.example.microservices.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.microservices.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long>{

	
}
