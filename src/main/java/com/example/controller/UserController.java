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
import jakarta.validation.Valid;

import com.example.customexception.BookingNotFoundException;
import com.example.customexception.BusNotFoundException;
import com.example.customexception.BusServiceException;
import com.example.customexception.RatingServiceException;
import com.example.customexception.TripNotFoundException;

import com.example.dto.BookingDTO;
import com.example.dto.RatingDTO;
import com.example.dto.UserDTO;
import com.example.dto.UserLoginDTO;
import com.example.entity.Trip;
import com.example.entity.User;

import com.example.service.RatingService;
import com.example.service.TripService;
import com.example.service.UserService;
import com.example.service.BookingService;



@RestController
@RequestMapping("/user_data")
public class UserController {

	@Autowired
    private  UserService userService;
	
	@Autowired
	private BookingService bookingservice;
	
	@Autowired
	private TripService tripService;
	
	@Autowired
	private RatingService ratingService;

	
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@Valid @RequestBody UserDTO user) 
    {
         try 
         {
        	 userService.registerUser(user);
             
             return new ResponseEntity<>("User Registered Sucessfully", HttpStatus.CREATED);
         } 
         catch (IllegalArgumentException e) 
         {
             return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
         }
    }
    
    
    @GetMapping("/login")
    public ResponseEntity<String> authenticateUser(@Valid @RequestBody UserLoginDTO user) 
    {
    	userService.authenticateUser(user);
    	
    	return new ResponseEntity<>("LogIn Sucessfully", HttpStatus.CREATED);
    }
    
    
    @GetMapping("/getuser/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable long userId)
    {
    	User user =userService.getUserById(userId);
		
    	return  new ResponseEntity<>(user, HttpStatus.OK);
    	
    }
    
    
    @GetMapping("/getAllUser") 
    public ResponseEntity<List<UserDTO>> getAllUser()
    {
    	List<UserDTO> users = userService.viewAllUser();
    	
    	return new ResponseEntity<>(users,HttpStatus.ACCEPTED);
    }
    
    
    @PutMapping("/update/{userId}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable long userId ,@Valid @RequestBody UserDTO userdto)
    {
    	UserDTO updatedUser =userService.updateUser(userId, userdto);
		
    	return  new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }
    
    
    
//    User Operation
    
    @GetMapping("/getAllTrip")
    public ResponseEntity<List<Trip>> gettingTrip() throws TripNotFoundException
    {
 	   return new ResponseEntity<>(tripService.getAllTrip(),HttpStatus.ACCEPTED);
    }
    
    
    @GetMapping("/findTrips")
    public ResponseEntity<Trip> findTrips(@Valid @RequestParam String fromLocation,@Valid @RequestParam String toLocation,@Valid @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate localDate) throws TripNotFoundException 
    {
        Trip trips = tripService.findtrip(fromLocation, toLocation, localDate);

        return new ResponseEntity<>(trips, HttpStatus.ACCEPTED);
    }
    
    
    @PostMapping("/addbooking/{tripId}")
	public ResponseEntity<BookingDTO> ticketBooking(@PathVariable long userId,@PathVariable long tripId, @RequestBody BookingDTO bookingdto) throws  BusNotFoundException, TripNotFoundException, BusServiceException
	{
		BookingDTO confiremedBooking =bookingservice.addBooking(userId,tripId, bookingdto);
		
		return new ResponseEntity<>(confiremedBooking,HttpStatus.ACCEPTED);
	}
    
    
    @GetMapping("/getbooking/{bookingId}")
	public ResponseEntity<BookingDTO> getBookingbyId(@PathVariable long bookingId) throws BookingNotFoundException 
	{
		
		BookingDTO bookingdto = bookingservice.getBooking(bookingId);
		
		return new ResponseEntity<>(bookingdto,HttpStatus.ACCEPTED);
	}
    
    
    @PostMapping("/create/{userId}")
	public ResponseEntity<RatingDTO> createRating(@Valid @PathVariable long userId,@Valid @RequestBody RatingDTO ratingDTO) 
	{
	    RatingDTO createdRating = ratingService.createRating(userId,ratingDTO);
	   
	    return new ResponseEntity<>(createdRating, HttpStatus.CREATED);
	}
    
    
    @GetMapping("/getrating/{id}")
	public ResponseEntity<RatingDTO> getRatingById(@PathVariable Long id) throws RatingServiceException 
	{
	    RatingDTO rating =ratingService.getRatingById(id);
	   
	    return new ResponseEntity<>(rating,HttpStatus.ACCEPTED);
	}
    
    
    @DeleteMapping("/deleterating/{id}")
	public ResponseEntity<String> deleteRating(@PathVariable Long id) throws RatingServiceException
	{
		ratingService.deleteRating(id);
		
		return new ResponseEntity<>("Rating Deleted Sucessfully",HttpStatus.OK);
	}
    
    
    @PutMapping("/updaterating/{id}")
	public ResponseEntity<RatingDTO> updateRating(@PathVariable Long id, @Valid @RequestBody RatingDTO ratingDTO) throws RatingServiceException 
	{
		RatingDTO updatedRating = ratingService.updateRating(id, ratingDTO);
		
		return new ResponseEntity<>(updatedRating,HttpStatus.ACCEPTED);
	    
	}
}


