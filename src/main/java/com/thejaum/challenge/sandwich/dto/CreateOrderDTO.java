package com.thejaum.challenge.sandwich.dto;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateOrderDTO {
	@JsonProperty(value = "user_id")
	private UUID userId;

	public CreateOrderDTO() {
		super();
	}

	public CreateOrderDTO(UUID userId) {
		super();
		this.userId = userId;
	}
	
	
}
