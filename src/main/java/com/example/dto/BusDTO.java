package com.example.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

public class BusDTO {

	
    private Long busId;

    @NotBlank(message = "Bus name cannot be blank")
    private String busName;

    @NotBlank(message = "Bus type cannot be blank")
    private String busType;

    @NotBlank(message = "Bus number cannot be blank")
    @Pattern(regexp = "[A-Za-z0-9]+", message = "Bus number must contain only alphanumeric characters")
    private String busNo;

    @Positive(message = "Seat capacity must be a positive number")
    @NotNull(message = "Seat capacity cannot be null")
    private int seatCapacity;
    
    private int totalSeatAvailable;
    
    public BusDTO() {
        // Default constructor
    }

    public BusDTO(long busId,String busName, String busType, String busNo, int seatCapacity) {
    	this.busId = busId;
        this.busName = busName;
        this.busType = busType;
        this.busNo = busNo;
        this.seatCapacity = seatCapacity;
    }

    // Getters and setters

    public Long getBusId() {
        return busId;
    }

    public void setBusId(Long busId) {
        this.busId = busId;
    }

    public String getBusName() {
        return busName;
    }

    public void setBusName(String busName) {
        this.busName = busName;
    }

    public String getBusType() {
        return busType;
    }

    public void setBusType(String busType) {
        this.busType = busType;
    }

    public String getBusNo() {
        return busNo;
    }

    public void setBusNo(String busNo) {
        this.busNo = busNo;
    }

    public int getSeatCapacity() {
        return seatCapacity;
    }

    public void setSeatCapacity(int seatCapacity) {
        this.seatCapacity = seatCapacity;
    }

	public int getTotalSeatAvailable() {
		return totalSeatAvailable;
	}

	public void setTotalSeatAvailable(int totalSeatAvailable) {
		this.totalSeatAvailable = totalSeatAvailable;
	}

	public void setSeatCapacity(Integer seatCapacity) {
		this.seatCapacity = seatCapacity;
	}
}