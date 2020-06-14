package com.thejaum.challenge.sandwich.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thejaum.challenge.sandwich.models.Order;
import com.thejaum.challenge.sandwich.repository.OrderRepository;
import com.thejaum.challenge.sandwich.repository.custom.OrderRepositoryCustom;

@Service
public class OrderService {
	
	@Autowired
	private final OrderRepository repository;
	@Autowired
	private final OrderRepositoryCustom customRepository;
	
	public OrderService(
			OrderRepository repository,
			OrderRepositoryCustom customRepository) {
		this.repository = repository;
		this.customRepository = customRepository;
	}
	
	public Order createNewOrder() {
		Order order = new Order();
		order.setCreated_at(LocalDateTime.now());
		order.setStatus(OrderStatusPattern.EM_ANDAMENTO.toString());
		return repository.save(order);
	}
	
	public List<Order> getAllByQueryStringParameters(String status){
		return customRepository.getOrdersByQueryStringParameters(status);
	}
	
}
