package com.thejaum.challenge.sandwich.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.thejaum.challenge.sandwich.models.Offer;

@Repository
public interface OfferRepository extends CrudRepository<Offer,Integer>{
	List<Offer> getOfferByActiveTrue(); 
}
