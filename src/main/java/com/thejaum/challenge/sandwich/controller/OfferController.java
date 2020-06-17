package com.thejaum.challenge.sandwich.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thejaum.challenge.sandwich.models.Offer;
import com.thejaum.challenge.sandwich.service.OfferService;

@RestController
@RequestMapping("v1")
public class OfferController {

	@Autowired
	private final OfferService service;

	public OfferController(OfferService service) {
		super();
		this.service = service;
	}
	
	@GetMapping("/protected/offers")
	public ResponseEntity<?> getAll(){
		try {
			List<Offer> offer_list = service.getOfferList();
			return new ResponseEntity<>(offer_list, HttpStatus.OK);
		}catch(Exception ex) {
			//LOG Error.
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/protected/offers/{id}")
	public ResponseEntity<?> getById(
			@PathVariable(value = "id") UUID id){
		try {
			return new ResponseEntity<>(service.getOfferById(id), HttpStatus.OK);
		}catch(Exception ex) {
			//LOG Error.
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
