package com.example.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class UserBookingDTO {
	
	@Pattern(regexp = "^[a-zA-Z]*$", message = "Invalid FisrtName format")
	@NotBlank(message = "FirstName is required")
    private String firstName;
	
	@Pattern(regexp = "^[a-zA-Z]*$", message = "Invalid LastName format")
	@NotBlank(message = "LastName is required")
	private String lastName;
	
	@NotBlank(message = "Username is required")
    @Size(min = 7, message = "Username should be at least 7 characters")
    @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "Invalid username format")
	private String userUsername;
	 
	 
	@NotBlank(message="Mobile number is required")
	@Size(min=10, message ="Mobile number must be 10 numbers")
	@Pattern(regexp= "^[0-9]*$")
	private String mobNo;
	
	@Email(message= "Please enter valid Email Id")
	private String emailId;
	
	public UserBookingDTO() {}

	public UserBookingDTO(String firstName, String lastName,String userUsername,String mobNo,String emailId) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.userUsername = userUsername;
		this.mobNo = mobNo;
		this.emailId = emailId;
	}
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUserUsername() {
		return userUsername;
	}

	public void setUserUsername(String userUsername) {
		this.userUsername = userUsername;
	}

	public String getMobNo() {
		return mobNo;
	}

	public void setMobNo(String mobNo) {
		this.mobNo = mobNo;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}	


}
