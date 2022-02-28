package com.prorent.car_rental.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.prorent.car_rental.domain.User;
import com.prorent.car_rental.exception.ResourceNotFoundException;
import com.prorent.car_rental.repository.UserRepository;
@Service
public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user=userRepository.findByEmail(email).orElseThrow(()->new ResourceNotFoundException("user not found"+email));
	return UserDetailsImp.build(user);
	}

}
