package com.thejaum.challenge.sandwich.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "offers")
@Entity
public class Offer {
	
	@EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="offer_id")
    private Integer id;
	
	@Column(name="active",nullable=false)
	private boolean active;
	
	@Column(name="description")
	private String description;
	
	@Column(name="identifier_name",nullable=false)
	private String identifierName;
}
