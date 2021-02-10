package com.example.microservices.dto;

import java.util.List;

public class CustomerDTO {

	private Long id;

	private String name;

	private List<ProductDTO> toys;

	public List<ProductDTO> getToys() {
		return toys;
	}

	public void setToys(List<ProductDTO> toys) {
		this.toys = toys;
	}

	public Long getId() {
		return id;
	}

	@Override
	public String toString() {
		return "CustomerDTO [id=" + id + ", name=" + name + ", toys=" + toys + "]";
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
