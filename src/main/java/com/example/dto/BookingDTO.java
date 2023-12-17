package com.example.dto;

import java.time.LocalDateTime;


import com.example.entity.Trip;
import com.example.entity.User;

import jakarta.validation.constraints.*;


public class BookingDTO {
	 
	@NotBlank(message = "Name cannot be blank")
	private String name;

	@Positive(message = "Age must be a positive number")
	private int age;

	@Email(message = "Invalid email format")
	private String emailId;

	@NotNull(message = "LocalDateTime cannot be null")
	private LocalDateTime localdatetime;

	@Positive(message = "Amount must be a positive number")
	private double amount;

	@Min(value = 1, message = "Booking seat must be at least 1")
	private int bookingSeat;

	@NotNull(message = "Trip cannot be null")
	private Trip trip;

	@NotNull(message = "User cannot be null")
	private UserBookingDTO user;

	public BookingDTO() {}

	public BookingDTO(String name, int age, String emailId, int bookingSeat) {
		super();
		this.name = name;
		this.age = age;
		this.emailId = emailId;
		this.bookingSeat = bookingSeat;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public LocalDateTime getLocaldatetime() {
		return localdatetime;
	}

	public void setLocaldatetime(LocalDateTime localdatetime) {
		this.localdatetime = localdatetime;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public int getBookingSeat() {
		return bookingSeat;
	}

	public void setBookingSeat(int bookingSeat) {
		this.bookingSeat = bookingSeat;
	}

	public Trip getTrip() {
		return trip;
	}

	public void setTrip(Trip trip) {
		this.trip = trip;
	}

	public UserBookingDTO getUser() {
		return user;
	}

	public void setUser(UserBookingDTO user) {
		this.user = user;
	}
}
