package com.example.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name="Users")
public class User {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long userId;
	
	private String firstName;
	
	private String lastName;
	
	private String userUsername;
	
	private String userPassword;
	 
	private String mobNo;
	
	private String emailId;
	
	public User() {}
	
	public User( String firstName, String lastName, String userUsername, String userPassword, String mobNo,
			String emailId) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.userUsername = userUsername;
		this.userPassword = userPassword;
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



	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
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

	@Override
	public String toString() {
		return "User [ userUsername=" + userUsername + ", userPassword=" + userPassword
				+ ", mobNo=" + mobNo + ", emailId=" + emailId + "]";
	}
	
}

