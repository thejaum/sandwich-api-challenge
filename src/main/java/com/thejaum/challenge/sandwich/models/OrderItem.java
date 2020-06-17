package com.thejaum.challenge.sandwich.models;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.Type;
import org.springframework.data.repository.cdi.Eager;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

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
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Type(type="uuid-char")
	@Column(name="order_item_id")
    private UUID id;
	
	@Column(name="sale_value",precision=12, scale=2)
	private BigDecimal value;	
	
	@ManyToOne
	@JoinColumn(name="product_id", nullable = false)
	private Product product;
	
	@ManyToOne
	@JoinColumn(name="order_id", nullable = false)
	@JsonBackReference
	private Order order;
	
	@ManyToMany
	@JoinTable(name="promotional",
				joinColumns = @JoinColumn(name = "order_item_id"),
				inverseJoinColumns = @JoinColumn(name = "offer_id"))
	private List<Offer> promotions;
	
}
