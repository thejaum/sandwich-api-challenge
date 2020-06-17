package com.thejaum.challenge.sandwich.models;

import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

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
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Type(type="uuid-char")
	@Column(name="offer_id")
    private UUID id;
	
	@Column(name="active",nullable=false)
	private boolean active;
	
	@Column(name="description")
	private String description;
	
	@Column(name="identifier_name",nullable=false,unique = true)
	private String identifierName;
	
	@Column(name="whole_order",nullable=false)
	private boolean wholeOrder;
	@Column(name="discount_percentage",nullable=false)
	private int discountPercentage;
	
	@Column(name="discount_amount",nullable=false)
	private int discount_amount;
	@Column(name="accumulative",nullable=false)
	private boolean accumulative;
	
	@OneToMany(mappedBy = "offer", fetch = FetchType.EAGER)
	@JsonManagedReference
	private List<OfferRule> rules;
}
