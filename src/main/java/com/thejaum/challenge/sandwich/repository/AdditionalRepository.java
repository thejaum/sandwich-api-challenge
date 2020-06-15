package com.thejaum.challenge.sandwich.repository;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.thejaum.challenge.sandwich.models.Additional;

@Repository
public interface AdditionalRepository extends CrudRepository<Additional,UUID>{

}
