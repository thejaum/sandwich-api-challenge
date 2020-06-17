package com.thejaum.challenge.sandwich.models;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "users")
@Entity
public class User {

	@EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Type(type="uuid-char")
	@Column(name="user_id")
    private UUID id;
	
	@Column(name="username",nullable=false,unique = true)
    private String username;

	@Column(name="password",nullable=false)
    private String password;

	@Column(name="name",nullable=false)
    private String name;

	@Column(name="admin",nullable=false)
    private boolean admin;
}
