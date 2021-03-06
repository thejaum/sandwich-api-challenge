package com.thejaum.challenge.sandwich.controller;

import static com.thejaum.challenge.sandwich.util.ApiSecurityPath.PROTECTED;

import java.util.List;

import org.apache.commons.collections4.IterableUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thejaum.challenge.sandwich.models.Product;
import com.thejaum.challenge.sandwich.repository.ProductRepository;

@RestController
@RequestMapping("v1")
public class ProductController {
	
	private final String RESOURCE = "products";
	
	@Autowired
	private final ProductRepository repository;

	public ProductController(ProductRepository repository) {
		super();
		this.repository = repository;
	}
	
	@GetMapping(PROTECTED+"/"+RESOURCE)
	public ResponseEntity<?> getAll(){
		try {
			List<Product> products_list = IterableUtils.toList(repository.findAll());
			return new ResponseEntity<>(products_list, HttpStatus.OK);
		}catch(Exception ex) {
			//LOG Error.
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
}
