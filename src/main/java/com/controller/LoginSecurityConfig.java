package com.controller;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class LoginSecurityConfig extends WebSecurityConfigurerAdapter {

	public void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.inMemoryAuthentication().withUser("hcl").password("hcl").roles("USER");
		auth.inMemoryAuthentication().withUser("hcl1").password("hcl1").roles("ADMIN");
		
	}

	public void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests().antMatchers("/Success").access("hasRole('ROLE_USER')");
		http.authorizeRequests().antMatchers("/Admin").access("hasRole('ROLE_ADMIN')")
		.and().formLogin().loginPage("/loginPage")
		.defaultSuccessUrl("/Success")
		.defaultSuccessUrl("/Admin")
		.failureUrl("/loginPage?error")
		.and().logout().logoutSuccessUrl("/loginPage?logout");
	}
}
