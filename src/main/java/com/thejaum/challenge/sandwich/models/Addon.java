package com.thejaum.challenge.sandwich.models;

import java.math.BigDecimal;

import javax.persistence.*;

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
	
	
	
}
