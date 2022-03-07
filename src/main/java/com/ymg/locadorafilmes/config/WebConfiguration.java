package com.ymg.locadorafilmes.config;

import com.ymg.locadorafilmes.service.LoggedPersonDetailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import com.ymg.locadorafilmes.security.jwt.JwtService;


import com.ymg.locadorafilmes.security.jwt.JwtAuthFilter;

import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.security.config.http.SessionCreationPolicy;

@EnableWebSecurity
public class WebConfiguration extends WebSecurityConfigurerAdapter {

	// @Autowired
	// public void configureGlobal(AuthenticationManagerBuilder auth) throws
	// Exception {
	// auth.userDetailsService(service).passwordEncoder(new
	// BCryptPasswordEncoder());
	// }

	// @Override
	// protected void configure(AuthenticationManagerBuilder auth) throws Exception
	// {
	// super.configure(auth);
	// }

	@Autowired
	private LoggedPersonDetailService service;

	@Autowired
	private JwtService jwtService;

	@Bean
	public OncePerRequestFilter jwtFilter() {
		return new JwtAuthFilter(jwtService, service);
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
				.passwordEncoder(new BCryptPasswordEncoder())
				.withUser("fulano")
				.password(new BCryptPasswordEncoder().encode("123"))
				.roles("ADMIN");
	}

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.csrf().disable();
		httpSecurity.authorizeRequests()
				.antMatchers("/auth").permitAll()
				.antMatchers("/users/create").permitAll()
				.anyRequest().authenticated()
				.and()
				.cors()
				.and()
				.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and()
				.addFilterBefore(jwtFilter(), UsernamePasswordAuthenticationFilter.class);

	}

}