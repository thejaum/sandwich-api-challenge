package com.thejaum.challenge.sandwich.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.thejaum.challenge.sandwich.models.OrderItem;

@Repository
public interface OrderItemRepository extends CrudRepository<OrderItem, UUID>{
	List<OrderItem>findAllByOrderId(UUID id);
}
