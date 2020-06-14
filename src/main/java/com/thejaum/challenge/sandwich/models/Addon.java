package com.thejaum.challenge.sandwich.models;

import java.math.BigDecimal;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

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
    @GeneratedValue(strategy = GenerationType.AUTO)
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@Column(name="addon_id")
    private UUID id;
	
	@Column(name="active")
	private boolean active;
	
	@Column(name="name")
	private String name;
	
	@Column(name="price",precision=12, scale=2)
	private BigDecimal price;

	public Addon() {
	}

	public Addon(UUID id, boolean active, String name, BigDecimal price) {
		super();
		this.id = id;
		this.active = active;
		this.name = name;
		this.price = price;
	}	
}
