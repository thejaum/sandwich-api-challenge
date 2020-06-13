package com.thejaum.challenge.sandwich.models;

import java.math.BigDecimal;

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
@Table(name = "addons")
@Entity
public class Addon {
	
	@EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="addon_id")
    private Integer id;
	
	@Column(name="active")
	private boolean active;
	
	@Column(name="name")
	private String name;
	
	@Column(name="price",precision=12, scale=2)
	private BigDecimal price;

	public Addon() {
	}
	public Addon(Integer id, boolean active, String name, BigDecimal price) {
		super();
		this.id = id;
		this.active = active;
		this.name = name;
		this.price = price;
	}	
	
		
}
