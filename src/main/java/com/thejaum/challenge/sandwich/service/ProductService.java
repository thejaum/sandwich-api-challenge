package com.thejaum.challenge.sandwich.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thejaum.challenge.sandwich.models.Product;
import com.thejaum.challenge.sandwich.repository.ProductRepository;

@Service
public class ProductService {
	@Autowired
	private final ProductRepository respository;

	public ProductService(ProductRepository respository) {
		super();
		this.respository = respository;
	}
	
	public Optional<Product> getProductById(UUID id) {
		return respository.findById(id);
	}
}
