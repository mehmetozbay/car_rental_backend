package com.prorent.car_rental.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException.InternalServerError;

import com.prorent.car_rental.domain.Message;
import com.prorent.car_rental.exception.ResourceNotFoundException;
import com.prorent.car_rental.repository.MessageRepository;

@Service
@Transactional
public class MessageService {

	@Autowired
	private MessageRepository messageRepository;

	public Message createMessage(Message message) {
		return messageRepository.save(message);
	}

	public Message getMessage(Long id) {
		Message foundMessage = messageRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Id not found" + id));
		return foundMessage;
	}

	public List<Message> getAllMessage() {
		return messageRepository.findAll();
	}

	public void deleteMessage(Long id) throws ResourceNotFoundException {
		messageRepository.deleteById(id);
	}

	public Message updateMessage(Long id, Message message) throws InternalServerError {
		Message foundMessage = this.getMessage(id);
		foundMessage.setSubject(message.getSubject());
		foundMessage.setBody(message.getBody());
		return messageRepository.save(foundMessage);
	}
	
	
}
