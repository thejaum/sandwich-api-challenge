package com.thejaum.challenge.sandwich.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.thejaum.challenge.sandwich.dto.AddItemOrderDTO;
import com.thejaum.challenge.sandwich.service.OrderService;

@RestController
@RequestMapping("v1")
public class OrderController {

	@Autowired
	private final OrderService service;
	
	public OrderController(OrderService service) {
		this.service = service;
	}
	
	@GetMapping("/protected/orders")
	public ResponseEntity<?> getAll(
			@RequestParam(value="status", required=false) String status) {
		return new ResponseEntity<>(service.getAllByQueryStringParameters(status),HttpStatus.OK);
	}
	
	@GetMapping("/protected/orders/{order_id}")
	public ResponseEntity<?> getById(
			@PathVariable("order_id") UUID id){
		return new ResponseEntity<>(service.getOrderById(id),HttpStatus.OK);
	}
	
	@PostMapping("/protected/orders")
	@Transactional(rollbackFor = Exception.class)
	public ResponseEntity<?> create() {
        return new ResponseEntity<>(service.createNewOrder(),HttpStatus.CREATED);
    }
	
	@PostMapping("/protected/orders/{order_id}/itens/")
	@Transactional(rollbackFor = Exception.class)
	public ResponseEntity<?> addItem(
			@PathVariable("order_id") UUID order_id,
			@RequestBody(required=true) AddItemOrderDTO input) {
		return new ResponseEntity<>(service.addItem(input,order_id),HttpStatus.CREATED);
	}
	
}
