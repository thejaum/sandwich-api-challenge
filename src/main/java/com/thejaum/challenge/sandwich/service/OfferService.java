package com.thejaum.challenge.sandwich.service;

import java.util.List;
import java.util.Optional;

import org.apache.commons.collections4.IterableUtils;
import org.springframework.stereotype.Service;

import com.thejaum.challenge.sandwich.models.Offer;
import com.thejaum.challenge.sandwich.repository.OfferRepository;

@Service
public class OfferService {
	private final OfferRepository repository;

	public OfferService(OfferRepository respository) {
		super();
		this.repository = respository;
	}
	
	public List<Offer> getActiveOfferList() {
		return repository.getOfferByActiveTrue();
	}
	
	public List<Offer> getOfferList() {
		return IterableUtils.toList(repository.findAll());
	}
	
	public Optional<Offer> getOfferById(int id) {
		return repository.findById(id);
	}
}
