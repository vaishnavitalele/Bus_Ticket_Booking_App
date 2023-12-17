package com.example.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Booking {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long bookingId;
	
	private String name;
	
	private int age;
	
	private String emailId;
	
	private LocalDateTime localdatetime;
	
	private double amount;
	
	private int bookingSeat;
	
	@ManyToOne
    @JoinColumn(name = "trip_id")
    private Trip trip;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	public Booking() {}
	
	public Booking(long bookingId, String name, int age, String emailId, LocalDateTime localdatetime, double amount,
			int bookingSeat, Trip trip,User user) {
		super();
		this.bookingId = bookingId;
		this.name = name;
		this.age = age;
		this.emailId = emailId;
		this.localdatetime = localdatetime;
		this.amount = amount;
		this.bookingSeat = bookingSeat;
		this.trip = trip;
		this.user = user;
	}


	public long getBookingId() {
		return bookingId;
	}

	public void setBookingId(long bookingId) {
		this.bookingId = bookingId;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Booking [bookingId=" + bookingId + ", name=" + name + ", age=" + age + ", emailId=" + emailId
				+ ", localdatetime=" + localdatetime + ", amount=" + amount + ", bookingSeat=" + bookingSeat + ", trip="
				+ trip + ", user=" + user + "]";
	}
	
}
