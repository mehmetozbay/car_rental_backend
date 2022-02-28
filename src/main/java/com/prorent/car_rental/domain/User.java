package com.prorent.car_rental.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
@Table(name = "tbl_user")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull(message = "Please provide your firstname")
	@NotBlank(message = "please provide not blank name")
	@Column(length = 200, nullable = false)
	@Size(min = 2, max = 20, message = "your name '${validatedValue}'  must be between {min} and {max} characters long")
	private String firstName;
	@NotNull(message = "Please provide your Last Name")
	@NotBlank(message = "please provide not blank Last name")
	@Column(length = 200, nullable = false)
	@Size(min = 2, max = 20, message = "your name '${validatedValue}'  must be between {min} and {max} characters long")
	private String lastName;
	@NotBlank(message = "please provide your email")
	@NotNull(message = "Please provide your email")
	@Column(length = 50, nullable = false)
	@Size(min = 6, max = 200, message = "your email '${validatedValue}'  must be between {min} and {max} characters long")
	private String email;
	@Size(min = 4, max = 60, message = "Please enter min 4 characters")
	@NotNull(message = "Please enter your password")
	@Column(nullable = false, unique = true, length = 120)
	private String password;
	@Pattern(regexp = "^((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$", message = "Please enter valid phone number")
	@Size(min = 14, max = 14, message = "Phone number should be exact 10 characters")
	@NotNull(message = "Please enter your phone number")
	@Column(nullable = false, length = 14)
	private String phoneNumber;
	@Size(max = 250)
	@NotNull(message = "Please enter your address")
	@Column(nullable = false, length = 250)
	private String address;
	@Size(max = 15)
	@NotNull(message = "Please enter your zip code")
	@Column(nullable = false, length = 15)
	private String zipCode;
	@Column(nullable = false)
	private Boolean builtIn;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name="tbl_user_role", joinColumns = @JoinColumn(name="user_id"), inverseJoinColumns =@JoinColumn(name="role_id") )
	private Set<Role> roles = new HashSet<>();
	
	

}
