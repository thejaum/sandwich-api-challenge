package com.thejaum.challenge.sandwich.controller;

import static com.thejaum.challenge.sandwich.util.ApiSecurityPath.PROTECTED;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.thejaum.challenge.sandwich.dto.AddItemOrderDTO;
import com.thejaum.challenge.sandwich.dto.CreateOrderDTO;
import com.thejaum.challenge.sandwich.service.OrderService;

@RestController
@RequestMapping("v1")
public class OrderController {

	private final String RESOURCE = "orders";
	private final String SUB_RESOURCE_ITENS = "itens";
	
	@Autowired
	private final OrderService service;
	
	public OrderController(OrderService service) {
		this.service = service;
	}
	
	@GetMapping(PROTECTED+"/"+RESOURCE)
	public ResponseEntity<?> getAll(
			@RequestParam(value="status", required=false) String status) {
		return new ResponseEntity<>(service.getAllByQueryStringParameters(status),HttpStatus.OK);
	}
	
	@GetMapping(PROTECTED+"/"+RESOURCE+"/{order_id}")
	public ResponseEntity<?> getById(
			@PathVariable("order_id") UUID order_id){
		return new ResponseEntity<>(service.getOrderById(order_id),HttpStatus.OK);
	}
	
	@PutMapping(PROTECTED+"/"+RESOURCE+"/{order_id}/perform")
	public ResponseEntity<?> perform(
			@PathVariable("order_id") UUID order_id){
		return new ResponseEntity<>(service.performOrder(order_id),HttpStatus.OK);
	}
	
	@PostMapping(PROTECTED+"/"+RESOURCE)
	@Transactional(rollbackFor = Exception.class)
	public ResponseEntity<?> create() {
        return new ResponseEntity<>(service.createNewOrder(),HttpStatus.CREATED);
    }
	
	@GetMapping(PROTECTED+"/"+RESOURCE+"/{order_id}/"+SUB_RESOURCE_ITENS)
	public ResponseEntity<?> getAllItens(
			@PathVariable("order_id") UUID order_id
			) throws Exception {
		return new ResponseEntity<>(service.getAllOrderItens(order_id),HttpStatus.OK);
	}
	
	@PostMapping(PROTECTED+"/"+RESOURCE+"/{order_id}/"+SUB_RESOURCE_ITENS)
	@Transactional(rollbackFor = Exception.class)
	public ResponseEntity<?> addItem(
			@PathVariable("order_id") UUID order_id,
			@RequestBody(required=true) AddItemOrderDTO input) throws Exception {
		return new ResponseEntity<>(service.addItem(input,order_id),HttpStatus.CREATED);
	}
	
	
}
