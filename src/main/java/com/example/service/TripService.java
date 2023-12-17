package com.example.service;

import java.time.LocalDate;
import java.util.List;


import org.springframework.stereotype.Service;

import com.example.customexception.BusNotFoundException;
import com.example.customexception.BusServiceException;
import com.example.customexception.TripNotFoundException;
import com.example.dto.TripDTO;
import com.example.entity.Trip;

import jakarta.validation.Valid;



@Service
public interface TripService {

	TripDTO addingTrip(@Valid Long busId, TripDTO trip) throws TripNotFoundException, BusNotFoundException, BusServiceException;
	
	void deleteTrip(long tripid) throws TripNotFoundException;

	Trip updateTrip(long tripId, TripDTO updateTrip) throws TripNotFoundException;
	
	List<Trip> getAllTrip() throws TripNotFoundException;

	Trip findtrip(String fromLocation, String toLocation, LocalDate localDate) throws TripNotFoundException;

}
