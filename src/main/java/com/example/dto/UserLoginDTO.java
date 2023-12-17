package com.example.dto;

import jakarta.validation.constraints.NotBlank;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class UserLoginDTO {
	
	@NotBlank(message = "Username is required")
    @Size(min = 7, message = "Username should be at least 7 characters")
    @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "Invalid username format")
	private String userUsername;
	
	 @NotBlank(message = "Password is required")
	 @Size(min = 8, message = "Password should be at least 8 characters")
	 @Pattern(regexp = "^(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?])(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).*$",
	 message = "Invalid password format")
	 private String userPassword;

	public UserLoginDTO( String userUsername, String userPassword) {
		super();
		this.userUsername = userUsername;
		this.userPassword = userPassword;
	}

	public String getUserUsername() {
		return userUsername;
	}

	public void setUserUsername(String userUsername) {
		this.userUsername = userUsername;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}	 

}
