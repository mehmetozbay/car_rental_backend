package com.prorent.car_rental.controller;


import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.prorent.car_rental.domain.User;
import com.prorent.car_rental.security.JwtUtils;
import com.prorent.car_rental.service.UserService;
import com.prorent.car_rental.vm.LoginVM;

@RestController
@RequestMapping
public class UserJWTController {

	@Autowired
	private UserService userService;

	@Autowired
	private JwtUtils jwtUtils;
	@Autowired
	AuthenticationManager authenticationManager;

	@PostMapping("/register")
	public ResponseEntity<User> register(@Valid @RequestBody User user) {
		User savedUser = userService.register(user);

		return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
	}

	@PostMapping("/login")
	public ResponseEntity<JWTToken> login(@Valid @RequestBody LoginVM loginVm) {
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(loginVm.getEmail(), loginVm.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt=jwtUtils.generateToken(authentication);
		HttpHeaders httpHeaders=new HttpHeaders();
		httpHeaders.add("Authorization", "Bearer "+jwt);
		return new ResponseEntity<>(new JWTToken(jwt), httpHeaders, HttpStatus.OK);
	}

	static class JWTToken {
		private String token;

		public JWTToken(String token) {
			this.token = token;
		}

		@JsonProperty("jwt_token")
		public String getToken() {
			return token;
		}

		public void setToken(String token) {
			this.token = token;
		}

	}
}
