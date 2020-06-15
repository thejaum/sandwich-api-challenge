package com.thejaum.challenge.sandwich.models;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "additionals")
@Entity
public class Additional {

	@EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Type(type="uuid-char")
	@Column(name="additional_id")
    private UUID id;
	
	@ManyToOne
	@JoinColumn(name="addon_id", nullable = false)
	private Addon addon;
	
	@ManyToOne
	@JoinColumn(name="order_item_id", nullable = false)
	private OrderItem orderItem;
	
	@Column(name = "amount")
	private int amount;
}
