package com.thejaum.challenge.sandwich.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thejaum.challenge.sandwich.models.User;
import com.thejaum.challenge.sandwich.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private final UserRepository repository;

	public UserService(UserRepository repository) {
		super();
		this.repository = repository;
	}
	
	public Optional<User> getUserById(UUID id) {
		return repository.findById(id);
	}
	
	public User getUserByUsername(String username) {
		return repository.findByUsername(username);
	}
}
