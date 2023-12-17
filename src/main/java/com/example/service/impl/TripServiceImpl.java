package com.example.service.impl;

import java.time.LocalDate;

import java.util.List;


import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.BusTicketBookingApplication.BusTicketBookingApplication;
import com.example.customexception.BusNotFoundException;
import com.example.customexception.BusServiceException;
import com.example.customexception.TripNotFoundException;
import com.example.dto.TripDTO;
import com.example.entity.Bus;
import com.example.entity.Trip;
import com.example.mapper.TripMapper;
import com.example.repository.BusRepository;
import com.example.repository.TripRepository;
import com.example.service.TripService;

import jakarta.validation.Valid;


@Service
public class TripServiceImpl implements TripService {
	
	public static Logger logger = LoggerFactory.getLogger(BusTicketBookingApplication.class);
	
	@Autowired
	TripMapper tripmap;
	
	@Autowired
	private TripRepository tripRepo;
	
	@Autowired
	private BusRepository busRepo;
	
	
	@Override
	public TripDTO addingTrip(@Valid Long busId,TripDTO trip) throws TripNotFoundException, BusNotFoundException, BusServiceException  
	{
		logger.info("Adding Trip");
		
		Bus bus = busRepo.findById(busId)
				.orElseThrow(() -> new BusNotFoundException("Bus Not Found"));
		
			Trip trip1 = new Trip();
			trip1.setFromLocation(trip.getFromLocation());
			trip1.setToLocation(trip.getToLocation());
			trip1.setDepartureDate(trip.getDepartureDate());
			trip1.setDepartureTime(trip.getDepartureTime());
			trip1.setArrivalTime(trip.getArrivalTime());
			trip1.setDistance(trip.getDistance());
			trip1.setRate(trip.getRate());
			trip1.setBus(bus);
			tripRepo.save(trip1);
			
			logger.info("Trip Added Sucessfully");
			
			return tripmap.convertToDTO(trip1);
	}
	
	
	@Override
	public void deleteTrip(long tripid) throws TripNotFoundException 
	{
		logger.info("You are enter in trip delete  process");
		
		if(tripRepo.existsById(tripid))
		{
			tripRepo.deleteById(tripid);
		}
		else
		{
		    throw new TripNotFoundException("Trip Not Found With Id : "+tripid);
		}
		
		logger.info("Trip Deleted Sucessfully");
	}

	
	@Override
	public List<Trip> getAllTrip() throws TripNotFoundException 
	{
		logger.info("You are getting trip deatils");
		
		List<Trip> trip = tripRepo.findAll();
		
		if(trip.isEmpty())
		{
			throw new TripNotFoundException("Trip Not Available");
		}
		
		return trip;
	}
	
	
	@Override
	public Trip updateTrip(long tripId, TripDTO updateTrip) throws TripNotFoundException 
	{
		logger.info("You are enter in trip update  process");
		
        Optional<Trip> optionalTrip = tripRepo.findById(tripId);

        if (optionalTrip.isPresent()) 
        {
            Trip existingTrip = optionalTrip.get();
            
            existingTrip.setFromLocation(updateTrip.getFromLocation());
            existingTrip.setToLocation(updateTrip.getToLocation());
            existingTrip.setDepartureDate(updateTrip.getDepartureDate());
            existingTrip.setDepartureTime(updateTrip.getDepartureTime());
            existingTrip.setArrivalTime(updateTrip.getArrivalTime());
            existingTrip.setDistance(updateTrip.getDistance());
            existingTrip.setRate(updateTrip.getRate());

            logger.info("Trip Updated Sucessfully");
            
            return tripRepo.save(existingTrip);
        } 
        else 
        {
        	logger.warn("Your trip Id is not found");
        	
            throw new TripNotFoundException("Trip Not Found With Id : "+tripId);
        }
	}

	
	@Override
	public Trip findtrip(String fromLocation, String toLocation, LocalDate localDate) throws TripNotFoundException 
	{
		
		Trip trips = tripRepo.findTripsByFromLocationAndToLocationAndDepartureDate(fromLocation, toLocation, localDate);
		
		if(trips == null)
		{
			logger.warn("Your trip location is not Available");
			
			throw new TripNotFoundException("Trip Not Found");
		}
		
		return trips;
	}
}
