package com.thejaum.challenge.sandwich.models;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "orders")
@Entity
public class Order {
	
	@EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Type(type="uuid-char")
	@Column(name="order_id")
    private UUID id;
	@Column(name="status",nullable=false)
	private String status;
	@Column(name="completed_at")
	private LocalDateTime completed_at;
	@Column(name="created_at",nullable=false)
	private LocalDateTime created_at;
	@OneToMany(mappedBy = "order",targetEntity = OrderItem.class, fetch = FetchType.LAZY)
	@JsonManagedReference
	private List<OrderItem> itens;
	@ManyToOne
	@JoinColumn(name="user_id", nullable = false)
	@JsonBackReference
	private User user;
	
}
