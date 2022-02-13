package com.prorent.car_rental.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prorent.car_rental.domain.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

}
