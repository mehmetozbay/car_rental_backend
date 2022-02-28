package com.prorent.car_rental.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_message")

public class Message implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull(message = "Please provide your name")
	@NotBlank(message = "please provide not blank name")
	@Column(length = 200, nullable = false)
	@Size(min = 2, max = 20, message = "your name '{validatedValue}'  must be between {min} and {max} characters long")
	private String name;

	@NotNull(message = "Please provide your subject")
	@NotBlank(message = "please provide not blank subject")
	@Column(length = 200, nullable = false)
	@Size(min = 5, max = 20, message = "your name '{validatedValue}'  must be between {min} and {max} characters long")
	private String subject;

	@NotBlank(message = "please provide not blank body")
	@NotNull(message = "Please provide your message body")
	@Column(length = 50, nullable = false)
	@Size(min = 5, max = 200, message = "your message '{validatedValue}'  must be between {min} and {max} characters long")
	private String body;

	@NotBlank(message = "please provide your email")
	@NotNull(message = "Please provide your email")
	@Column(length = 50, nullable = false)
	@Size(min = 6, max = 200, message = "your email '{validatedValue}'  must be between {min} and {max} characters long")
	private String email;

	@Pattern(regexp = "^((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$", message = "Please enter valid phone number")
	@Size(min = 14, max = 14, message = "Phone number should be exact 10 characters")
	@NotNull(message = "Please enter your phone number")
	@Column(nullable = false, length = 14)
	private String phoneNumber;

}
