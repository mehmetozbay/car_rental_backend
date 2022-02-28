package com.prorent.car_rental.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prorent.car_rental.domain.Role;
import com.prorent.car_rental.domain.enumeration.UserRole;
@Repository
public interface RoleRepository extends JpaRepository<Role, Integer>{

	Optional<Role> findByName(UserRole name);
}
