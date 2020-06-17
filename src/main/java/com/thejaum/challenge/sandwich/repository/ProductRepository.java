package com.thejaum.challenge.sandwich.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import com.thejaum.challenge.sandwich.models.Product;

public interface ProductRepository extends CrudRepository<Product, UUID>{
	List<Product> findByActiveTrue();
}
