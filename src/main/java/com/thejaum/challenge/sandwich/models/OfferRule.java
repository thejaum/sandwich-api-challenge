package com.thejaum.challenge.sandwich.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "offer_rules")
@Entity
public class OfferRule {

	@EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="offer_rule_id")
    private Integer id;
	
	@ManyToOne
	@JoinColumn(name="addon_id",referencedColumnName="addon_id", nullable = false)
	private Addon addon;
	
	@Column(name="amount")
	private int amount;
	
	@Column(name="presence")
	private boolean presence;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn (name="offer_id",referencedColumnName="offer_id",nullable=false,unique=false)
	@JsonBackReference
	private Offer offer;
}

