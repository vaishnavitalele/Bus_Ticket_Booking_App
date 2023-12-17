package com.example.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.customexception.BookingNotFoundException;
import com.example.customexception.BusNotFoundException;
import com.example.customexception.BusServiceException;
import com.example.customexception.TripNotFoundException;
import com.example.dto.BookingDTO;
import com.example.service.BookingService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(path="/booking")
public class BookingController {
	
	@Autowired
	private BookingService bookingservice;
	
	
	@PostMapping("/addbooking/{tripId}/{userId}")
	public ResponseEntity<BookingDTO> ticketBooking(@PathVariable long userId,@PathVariable long tripId, @RequestBody BookingDTO bookingdto) throws  BusNotFoundException, TripNotFoundException, BusServiceException
	{
		
		BookingDTO confiremedBooking =bookingservice.addBooking(userId,tripId,bookingdto);
		
		return new ResponseEntity<>(confiremedBooking,HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/deletebooking/{bookingId}")
	public ResponseEntity<String> deleteBooking(@PathVariable long bookingId) throws BookingNotFoundException 
	{
		
		bookingservice.deleteBooking(bookingId);
		
		return new ResponseEntity<>("Bus Deleted Sucessfully with Booking Id :"+bookingId,HttpStatus.NOT_FOUND);
	}
	
	
	@GetMapping("/getbooking/{bookingId}")
	public ResponseEntity<BookingDTO> getBookingbyId(@PathVariable long bookingId) throws BookingNotFoundException 
	{
		
		BookingDTO bookingdto = bookingservice.getBooking(bookingId);
		
		return new ResponseEntity<>(bookingdto,HttpStatus.ACCEPTED);
	}
	
	
	@GetMapping("/getAllBooking")
	public ResponseEntity<List<BookingDTO>> getAllBooking() throws BookingNotFoundException 
	{
		
		List<BookingDTO> bookingdto = bookingservice.getAllBooking();
		
		return new ResponseEntity<>(bookingdto,HttpStatus.ACCEPTED);
	}
	
	
	@GetMapping("/getbookingbyuser/{userId}")
	public ResponseEntity<BookingDTO> getBookingbyUser(@PathVariable long userId) throws BookingNotFoundException 
	{
		
		BookingDTO bookingdto = bookingservice.getBookingByUser(userId);
		
		return new ResponseEntity<>(bookingdto,HttpStatus.ACCEPTED);
	}
}
