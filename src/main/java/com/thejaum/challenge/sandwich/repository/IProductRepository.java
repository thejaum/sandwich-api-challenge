package com.thejaum.challenge.sandwich.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.thejaum.challenge.sandwich.models.Product;

public interface IProductRepository extends CrudRepository<Product, Integer>{
	List<Product> findByActiveTrue();
}
