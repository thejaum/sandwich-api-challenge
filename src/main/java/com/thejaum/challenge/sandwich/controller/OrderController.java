package com.thejaum.challenge.sandwich.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.thejaum.challenge.sandwich.service.OrderService;

@RestController
@RequestMapping("v1/orders")
public class OrderController {

	@Autowired
	private final OrderService service;
	
	public OrderController(OrderService service) {
		this.service = service;
	}
	
	@PostMapping
	@Transactional(rollbackFor = Exception.class)
	public ResponseEntity<?> create() {
        return new ResponseEntity<>(service.createNewOrder(),HttpStatus.CREATED);
    }
	
	@GetMapping
	public ResponseEntity<?> getAll(
			@RequestParam(value="status", required=false) String status) {
		return new ResponseEntity<>(service.getAllByQueryStringParameters(status),HttpStatus.OK);
	}
}
