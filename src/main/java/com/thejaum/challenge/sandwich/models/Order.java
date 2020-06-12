package com.thejaum.challenge.sandwich.models;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="order_id")
    private Integer id;
	
	@Column(name="status",nullable=false)
	private String status;
	@Column(name="completed_at")
	private LocalDateTime completed_at;
	@Column(name="created_at",nullable=false)
	private LocalDateTime created_at;
}
