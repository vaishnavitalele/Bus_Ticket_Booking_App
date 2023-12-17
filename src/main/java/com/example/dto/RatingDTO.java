package com.example.dto;

import com.example.entity.User;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;


public class RatingDTO {
    

	private User user;
	
	@NotEmpty(message = "First Name should not be Blank")
	private String firstName;
    
	@NotEmpty(message = "First Name should not be Blank")
    private String lastName;
    
	@NotEmpty(message = "First Name should not be Blank")
    private String phoneNumber;
	
    @Email
    private String emailId;
    
   
    @Min(value = 1, message = "Rating must be greater than or equal to 1")
    @Max(value = 5, message = "Rating must be less than or equal to 5")
    private int rating;
    
    public RatingDTO() {}
	    
	    public RatingDTO(String firstname,String lastName,String phoneNumber,String emailId,int rating)
	    {
	    	this.firstName=firstname;
	    	this.lastName=lastName;
	    	this.phoneNumber=phoneNumber;
	    	this.emailId=emailId;
	    	this.rating=rating;
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

		public String getPhoneNumber() {
			return phoneNumber;
		}

		public void setPhoneNumber(String phoneNumber) {
			this.phoneNumber = phoneNumber;
		}

		public String getEmailId() {
			return emailId;
		}

		public void setEmailId(String emailId) {
			this.emailId = emailId;
		}

		public int getRating() {
			return rating;
		}

		public void setRating(int rating) {
			this.rating = rating;
		}

		public User getUser() {
			return user;
		}

		public void setUser(User user) {
			this.user = user;
		}		
}
