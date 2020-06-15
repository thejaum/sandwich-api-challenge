package com.thejaum.challenge.sandwich.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thejaum.challenge.sandwich.dto.AddItemOrderDTO;
import com.thejaum.challenge.sandwich.models.Addon;
import com.thejaum.challenge.sandwich.repository.AddonRepository;

@RestController
@RequestMapping("v1/addons")
public class AddonController {

	@Autowired
	private final AddonRepository repository;
	
	public AddonController(AddonRepository repository) {
		this.repository = repository;
	}
	
	@GetMapping
	public ResponseEntity<?> getAll(){
		try {
			List<Addon> addon_list = repository.findByActiveTrue();
			return new ResponseEntity<>(addon_list, HttpStatus.OK);
		}catch(Exception ex) {
			//LOG Error.
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
