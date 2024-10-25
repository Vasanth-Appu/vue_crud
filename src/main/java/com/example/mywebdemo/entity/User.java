package com.example.mywebdemo.entity;

import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.web.bind.annotation.RequestBody;

import jakarta.annotation.Nonnull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="employee")
public class User {
	
	@Id
//	@GeneratedValue(strategy =GenerationType.IDENTITY)


	private Long id; 

    @Pattern(regexp = "^[a-zA-Z]{4,12}$",
            message = "username must be of 4 to 12 length with no special characters")
    private String name;
	
	public Long getId() {
		return id;
	}
	 
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
		
	}
	@Column(nullable=false)	
	private String address;
//	@Column(nullable=false)	
	@Pattern(regexp = "\\d{10}", message = "Phone number must be 10 digits")
	private String contact;
	@Email
	private String  email;

	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	



}
