package com.example.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity
public class Bus {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long busId;

    private String busName;
    private String busType;
    private String busNo;
    private int seatCapacity;
    private int totalSeatAvailable;

    // Constructors, getters, and setters
    
    public Bus() {}

    public Bus(String busName, String busType, String busNo, int seatCapacity,int totalSeatAvailable) {
        this.busName = busName;
        this.busType = busType;
        this.busNo = busNo;
        this.seatCapacity = seatCapacity;
        this.totalSeatAvailable =totalSeatAvailable;
    }


    public long getBusId() {
        return busId;
    }

    public void setBusId(long busId) {
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

	@Override
	public String toString() {
		return "Bus [busId=" + busId + ", busName=" + busName + ", busType=" + busType + ", busNo=" + busNo
				+ ", seatCapacity=" + seatCapacity + ", totalSeatAvailable=" + totalSeatAvailable + "]";
	}
	
	
    
    
}
