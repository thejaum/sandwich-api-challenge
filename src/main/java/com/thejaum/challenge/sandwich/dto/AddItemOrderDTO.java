package com.thejaum.challenge.sandwich.dto;

import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddItemOrderDTO {
	@JsonProperty(value = "product_id")
	private UUID productId;
	@JsonProperty(value = "addon_list")
	private List<UUID> additionals;
	
	public AddItemOrderDTO(UUID productId, List<UUID> additionals) {
		super();
		this.productId = productId;
		this.additionals = additionals;
	}
	public AddItemOrderDTO() {
		super();
	}
	
	
}
