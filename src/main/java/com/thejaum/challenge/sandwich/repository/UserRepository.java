package com.thejaum.challenge.sandwich.repository;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import com.thejaum.challenge.sandwich.models.User;

public interface UserRepository extends CrudRepository<User, UUID>{
	User findByUsername(String username);
}
