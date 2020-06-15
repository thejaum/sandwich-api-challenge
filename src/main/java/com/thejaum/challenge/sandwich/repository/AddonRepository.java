package com.thejaum.challenge.sandwich.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import com.thejaum.challenge.sandwich.models.Addon;

public interface AddonRepository extends CrudRepository<Addon,UUID>{
	List<Addon> findByActiveTrue();
	List<Addon> findByIdIn(List<UUID> idList);
}
