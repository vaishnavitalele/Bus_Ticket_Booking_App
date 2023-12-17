package com.example.repository;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.Trip;


public interface TripRepository extends JpaRepository<Trip, Long>{
	
	

	
	 Trip findTripsByFromLocationAndToLocationAndDepartureDate(
	            String fromLocation, String toLocation, LocalDate departureDate);
}
