package com.thejaum.challenge.sandwich.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AppToken {

	@JsonProperty(value = "acess_token",required = true)
	private String acessToken;
	@JsonProperty(value = "expirationTime",required = true)
	private long expirationTime;
}
