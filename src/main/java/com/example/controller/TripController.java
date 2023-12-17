package com.example.controller;

import java.time.LocalDate;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.example.customexception.BusNotFoundException;
import com.example.customexception.BusServiceException;
import com.example.customexception.TripNotFoundException;
import com.example.dto.TripDTO;
import com.example.entity.Trip;
import com.example.service.TripService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/trip")
public class TripController {
	
    @Autowired
	private TripService tripService;
    
    
    @PostMapping("/addtrip")
    public ResponseEntity<TripDTO> addTrip( @RequestParam long busId,@Valid @RequestBody TripDTO trip) throws TripNotFoundException, BusNotFoundException, BusServiceException  
    { 
    	TripDTO createdTrip= tripService.addingTrip(busId,trip);
		
    	return new ResponseEntity<>(createdTrip ,HttpStatus.CREATED);
    }
    
    
    @DeleteMapping("/deletetrip")
    public ResponseEntity<String> deleteTrip(@RequestParam long tripId) throws TripNotFoundException 
    {
    	tripService.deleteTrip(tripId);
    	
    	return new ResponseEntity<>("Trip Deleted SucessFully",HttpStatus.OK);
    }
    
    
    @GetMapping("/getAllTrip")
    public ResponseEntity<List<Trip>> gettingTrip() throws TripNotFoundException
    {
    	
	    return new ResponseEntity<>(tripService.getAllTrip(),HttpStatus.ACCEPTED);
    }
    

    @PutMapping("/updatetrip/{tripId}")
    public ResponseEntity<Trip> updatetrip(@PathVariable Long tripId,@Valid @RequestBody TripDTO tripdto) throws TripNotFoundException
    {
    	
    	Trip updatedTrip = tripService.updateTrip(tripId, tripdto);
       	
       	return new ResponseEntity<>(updatedTrip, HttpStatus.ACCEPTED); 
    }
    
    
    @GetMapping("/findTrips")
    public ResponseEntity<Trip> findTrips(@Valid @RequestParam String fromLocation,@Valid @RequestParam String toLocation,@Valid @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate localDate) throws TripNotFoundException 
    {
        Trip trips = tripService.findtrip(fromLocation, toLocation, localDate);

        return new ResponseEntity<>(trips, HttpStatus.ACCEPTED);
    }
}
