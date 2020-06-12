package com.thejaum.challenge.sandwich.models;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.*;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "products")
@Entity
public class Product {
	
	@EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="product_id")
    private Integer id;
	
	@Column(name="active",nullable=false)
	private boolean active;
	
	@Column(name="name",nullable=false)
	private String name;
	
	@Column(name="description")
	private String description;
	
	@Column(name="base_price",precision=12, scale=2)
	private BigDecimal basePrice;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name="composition",
				joinColumns = @JoinColumn(name = "product_id"),
				inverseJoinColumns = @JoinColumn(name = "addon_id"))
	private List<Addon> composition; 
}
