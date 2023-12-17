package com.example.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


@Entity
@Table(name = "Rating")
public class Rating {

	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Long id;
	 
	 @Column(name = "first_name")
	 private String firstName;
	 
	 @Column(name = "last_name")
	 private String lastName;
	 
	 @Column(name = "phone_number")
	 private String phoneNumber;
	
	 @Column(name = "email_id")
	 private String emailId;
	
	 @Column(name = "rating")
	 private int rating;
	 
	 @Column(name = "average_rating")
	 private double averageRating;
	 
	 @ManyToOne
	 @JoinColumn(name="userId")
	 private User user;
	    
	 public Rating() {}

	public Rating(String firstName, String lastName, String phoneNumber, String emailId, int rating,
			double averageRating, User user) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.emailId = emailId;
		this.rating = rating;
		this.averageRating = averageRating;
		this.user = user;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public double getAverageRating() {
		return averageRating;
	}

	public void setAverageRating(double averageRating) {
		this.averageRating = averageRating;
	}

	public User getUserId() {
		return user;
	}

	public void setUserId(User user) {
		this.user = user;
	}
	    
}


