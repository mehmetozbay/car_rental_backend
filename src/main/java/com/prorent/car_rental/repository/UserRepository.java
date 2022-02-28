package com.prorent.car_rental.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prorent.car_rental.domain.User;
import com.prorent.car_rental.exception.ConflictException;
import com.prorent.car_rental.exception.ResourceNotFoundException;

public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User>findByEmail(String email) throws ResourceNotFoundException;
	
	Boolean existsByEmail(String email) throws ConflictException;
}
