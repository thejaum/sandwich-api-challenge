package com.thejaum.challenge.sandwich.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;

import com.thejaum.challenge.sandwich.service.CustomUserDetailService;;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig  extends WebSecurityConfigurerAdapter{

	@Autowired
	private CustomUserDetailService customUserDetailService;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
	    http.cors().configurationSource(request -> new CorsConfiguration().applyPermitDefaultValues())
	            .and().csrf().disable()
	            .authorizeRequests()
	            .antMatchers("/swagger-ui.html/**").permitAll()
	            .antMatchers("/*/protected/**").hasRole("USER")
	            .antMatchers("/*/admin/**").hasRole("ADMIN")
	            .and()
	            .addFilter(new JWTAuthenticationFilter(authenticationManager()))
	            .addFilter(new JWTAuthorizationFilter(authenticationManager(), customUserDetailService));
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	    auth.userDetailsService(customUserDetailService).passwordEncoder(new BCryptPasswordEncoder());
	}

	
}
