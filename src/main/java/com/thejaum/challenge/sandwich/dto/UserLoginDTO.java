package com.thejaum.challenge.sandwich.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserLoginDTO {
	@JsonProperty(value = "username",required = true)
	private String username;
	@JsonProperty(value = "password",required = true)
	private String password;
}
