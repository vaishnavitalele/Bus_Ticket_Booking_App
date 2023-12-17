package com.example.dto;

import java.time.LocalDate;
import java.time.LocalTime;


import org.springframework.format.annotation.DateTimeFormat;

import com.example.entity.Bus;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;


public class TripDTO {
	
	@NotBlank(message = "FromLocation Should not be Blank")
	@Pattern(regexp = "^[a-zA-Z]+$", message = "Incorect Pattern")
	private String fromLocation;
	
	@NotBlank(message = "FromLocation Should not be Blank")
	@Pattern(regexp = "^[a-zA-Z]+$", message = "Incorect Pattern")
	private String toLocation;
	
	@Size(min =1 , message = "Invalid Distance")
	private int distance;
	
	@Size(min =1,message = "Invalid rate")
	private double rate;
	
	@NotNull(message = "Buses Can not be Null")
    private Bus bus;
//	
	@NotNull(message = "Date Can not be Null")
	@Future(message = "Date must be in the Future")
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private LocalDate departureDate;
	
	@DateTimeFormat(pattern = "hh:mm:ss a")
	private LocalTime departureTime;
	
	@DateTimeFormat(pattern = "hh:mm:ss a")
	private LocalTime arrivalTime;
    
	public TripDTO() {}
	public TripDTO( String fromLocation, String toLocation,LocalDate departureDate, LocalTime departureTime, 
			LocalTime arrivalTime,int distance, double rate)
	{
		super();
		this.fromLocation = fromLocation;
		this.toLocation = toLocation;
		this.distance = distance;
		this.rate = rate;
		this.departureDate = departureDate;
		this.departureTime = departureTime;
		this.arrivalTime = arrivalTime;
	}
	public String getFromLocation() {
		return fromLocation;
	}
	public void setFromLocation(String fromLocation) {
		this.fromLocation = fromLocation;
	}
	public String getToLocation() {
		return toLocation;
	}
	public void setToLocation(String toLocation) {
		this.toLocation = toLocation;
	}
	public int getDistance() {
		return distance;
	}
	public void setDistance(int distance) {
		this.distance = distance;
	}
	public double getRate() {
		return rate;
	}
	public void setRate(double rate) {
		this.rate = rate;
	}
	public LocalDate getDepartureDate() {
		return departureDate;
	}
	public void setDepartureDate(LocalDate departureDate) {
		this.departureDate = departureDate;
	}
	public LocalTime getDepartureTime() {
		return departureTime;
	}
	public void setDepartureTime(LocalTime departureTime) {
		this.departureTime = departureTime;
	}
	public LocalTime getArrivalTime() {
		return arrivalTime;
	}
	public void setArrivalTime(LocalTime arrivalTime) {
		this.arrivalTime = arrivalTime;
	}
	public Bus getBus() {
		return bus;
	}
	public void setBus(Bus bus) {
		this.bus = bus;
	}
	

	
}
