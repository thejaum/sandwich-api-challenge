package com.thejaum.challenge.sandwich.repository.custom;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.thejaum.challenge.sandwich.models.Order;

@Repository
public interface OrderRepositoryCustom {
	List<Order> getOrdersByQueryStringParameters(String status);
}
