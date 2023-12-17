package com.example.entity;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
	public class Trip {
		@Id
		@GeneratedValue(strategy=GenerationType.IDENTITY)
		private Long tripId;
		private String fromLocation;
		private String toLocation;
		private LocalDate departureDate;
		private LocalTime departureTime;
		private LocalTime arrivalTime;
		private int distance;
		private double rate;
		
		@OneToOne
		private Bus bus;
		
		public Trip() {}
		
		
		public Trip(long tripId, String fromLocation, String toLocation, LocalDate departureDate,
				LocalTime departureTime, LocalTime arrivalTime, int distance, double rate) {
			super();
			this.tripId = tripId;
			this.fromLocation = fromLocation;
			this.toLocation = toLocation;
			this.departureDate = departureDate;
			this.departureTime = departureTime;
			this.arrivalTime = arrivalTime;
			this.distance = distance;
			this.rate = rate;
		}


		public long getTripId() {
			return tripId;
		}
		public void setTripId(long tripId) {
			this.tripId = tripId;
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
		
		


		public Bus getBus() {
			return bus;
		}


		public void setBus(Bus bus) {
			this.bus = bus;
		}


		public void setTripId(Long tripId) {
			this.tripId = tripId;
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
}
