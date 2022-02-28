package com.prorent.car_rental.service;

import java.util.HashSet;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException.BadRequest;

import com.prorent.car_rental.domain.Role;
import com.prorent.car_rental.domain.User;
import com.prorent.car_rental.domain.enumeration.UserRole;
import com.prorent.car_rental.exception.BadRequestException;
import com.prorent.car_rental.exception.ConflictException;
import com.prorent.car_rental.exception.ResourceNotFoundException;
import com.prorent.car_rental.repository.RoleRepository;
import com.prorent.car_rental.repository.UserRepository;

@Transactional
@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public User register(User user) throws BadRequestException {
		if (userRepository.existsByEmail(user.getEmail())) {
			throw new ConflictException("Error: Email is already in use");
		} else {
			String encodedPassword = passwordEncoder.encode(user.getPassword());
			user.setPassword(encodedPassword);
			user.setBuiltIn(false);
			Set<Role> roles = new HashSet<>();
			Role role = roleRepository.findByName(UserRole.ROLE_CUSTOMER)
					.orElseThrow(() -> new ResourceNotFoundException("Role not found"));
			roles.add(role);
			user.setRoles(roles);
			return userRepository.save(user);
		}
	}
}
