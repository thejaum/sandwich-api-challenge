package com.thejaum.challenge.sandwich.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.thejaum.challenge.sandwich.models.Addon;

public interface AddonRepository extends CrudRepository<Addon,Integer>{
	List<Addon> findByActiveTrue();
}
