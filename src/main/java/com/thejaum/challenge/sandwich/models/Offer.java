package com.thejaum.challenge.sandwich.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

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
	
	@Column(name="discount",nullable=false)
	private int discount;
	
	@Column(name="accumulative",nullable=false)
	private boolean accumulative;
	
	@Column(name="whole_order",nullable=false)
	private boolean wholeOrder;
	
	@OneToMany(mappedBy = "offer", fetch = FetchType.EAGER)
	@JsonManagedReference
	private List<OfferRule> rules;
}
