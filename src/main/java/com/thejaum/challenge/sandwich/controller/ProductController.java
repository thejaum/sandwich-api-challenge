package com.thejaum.challenge.sandwich.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thejaum.challenge.sandwich.models.Product;
import com.thejaum.challenge.sandwich.repository.IProductRepository;

@RestController
@RequestMapping("v1/products")
public class ProductController {

	@Autowired
	private final IProductRepository repository;

	public ProductController(IProductRepository repository) {
		super();
		this.repository = repository;
	}
	
	@GetMapping
	public ResponseEntity<?> getAll(){
		try {
			List<Product> products_list = repository.findByActiveTrue();
			return new ResponseEntity<>(products_list, HttpStatus.OK);
		}catch(Exception ex) {
			//LOG Error.
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
}
