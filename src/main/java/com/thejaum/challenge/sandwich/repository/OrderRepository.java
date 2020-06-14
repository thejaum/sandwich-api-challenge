package com.thejaum.challenge.sandwich.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.thejaum.challenge.sandwich.models.Order;
import com.thejaum.challenge.sandwich.repository.custom.OrderRepositoryCustom;

@Repository
public interface OrderRepository extends CrudRepository<Order,Integer>{
		
}
