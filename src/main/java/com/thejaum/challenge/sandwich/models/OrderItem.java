package com.thejaum.challenge.sandwich.models;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "order_itens")
@Entity
public class OrderItem {

	@EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="order_item_id")
    private Integer id;
	
	@Column(name="sale_value",precision=12, scale=2)
	private BigDecimal value;	
	
	@ManyToOne
	@JoinColumn(name="product_id", nullable = false)
	private Product product;
	
	@ManyToOne
	@JoinColumn(name="order_id", nullable = false)
	private Order order;
	
	@ManyToMany
	@JoinTable(name="additional",
				joinColumns = @JoinColumn(name = "order_item_id"),
				inverseJoinColumns = @JoinColumn(name = "addon_id"))
	private List<Addon> additionals;
	
	@ManyToMany
	@JoinTable(name="promotional",
				joinColumns = @JoinColumn(name = "order_item_id"),
				inverseJoinColumns = @JoinColumn(name = "offer_id"))
	private List<Offer> promotions;
	
}
