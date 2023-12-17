package com.example.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;

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
import com.example.dto.AdminDTO;
import com.example.dto.AdminLoginDTO;
import com.example.dto.BookingDTO;
import com.example.dto.BusDTO;
import com.example.dto.RatingDTO;
import com.example.dto.TripDTO;
import com.example.dto.UserDTO;
import com.example.entity.Trip;
import com.example.entity.User;


import com.example.service.Adminservice;
import com.example.service.BookingService;
import com.example.service.BusService;
import com.example.service.RatingService;
import com.example.service.TripService;
import com.example.service.UserService;




@RestController
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private  Adminservice adminservice;
	
	@Autowired
	private UserService userService;
	
	@Autowired
    private BusService busService;
	
	@Autowired
	private TripService tripService;
	
	@Autowired
	private BookingService bookingservice;
	
	@Autowired
	private RatingService ratingService;
	
	
	@PostMapping("/register")
	public ResponseEntity<String> addAdmin(@Valid @RequestBody AdminDTO admindto)
	{
		adminservice.registerAdmin(admindto);
		
		return new ResponseEntity<>("Admin register Sucessfully",HttpStatus.CREATED);
	}
	
	
	@GetMapping("/login")
	public ResponseEntity<String> loginAdmin(@Valid @RequestBody AdminLoginDTO adminlogindto)
	{
		adminservice.login(adminlogindto);
		
		return new ResponseEntity<>("Admin LogIn Sucessfully",HttpStatus.OK);
		
	}
	
	
	@DeleteMapping("/delete")
	public ResponseEntity<String> deleteAdminById(@RequestParam long adminId)
	{
		adminservice.deleteAdmin(adminId);
		
		return new ResponseEntity<>("Admin Profile Deleted successfully", HttpStatus.ACCEPTED);
	}
	
	
	@PutMapping("/update/{adminId}")
	public ResponseEntity<String> updateAdmin(@PathVariable long adminId, @Valid @RequestBody AdminDTO updatedAdmin) throws IllegalArgumentException  
	{
		adminservice.updateAdmin(adminId,updatedAdmin );
		
		return new ResponseEntity<>("Admin Profile Updated successfully", HttpStatus.ACCEPTED);
	}
	
	
//	Admin Operation
	
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
	
	
	@PostMapping(path="/addbus")
    public ResponseEntity<BusDTO> createBus(@Valid @RequestBody BusDTO busDto) throws BusNotFoundException
    {		
        BusDTO createdBus =  busService.createBus(busDto);
		
        return ResponseEntity.status(HttpStatus.CREATED).body(createdBus);
    }
	
	
	@PutMapping("/updatebus")
	public ResponseEntity<BusDTO> updateBus(@RequestParam long busId ,@Valid @RequestBody BusDTO busDto) throws  BusNotFoundException
    {
        
		BusDTO updatedBus = busService.updateBus(busId,busDto);
        
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(updatedBus);
    }
	
	
	@DeleteMapping("/deletebus")
	public ResponseEntity<String> deleteBus(@RequestParam long busId) throws BusNotFoundException
    {
       
		String deletedBus = busService.deleteBusbyId(busId);
        
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(deletedBus + busId);
    }
	
	
	@GetMapping("/getbus")
	public ResponseEntity<BusDTO> getBus(@Valid @RequestParam long busId) throws BusNotFoundException
    {
		
		BusDTO getbus = busService.getBusbyId(busId);
        
		return  ResponseEntity.status(HttpStatus.ACCEPTED).body(getbus);
    }
	
	@GetMapping("/getAllbus")
	public ResponseEntity<List<BusDTO>> getAllBus() throws BusNotFoundException
    {
		
		List<BusDTO> buses = busService.getAllBuses();
        
		return ResponseEntity.ok(buses);
    }
	
	
	@PutMapping("/updateseat")
	public ResponseEntity<BusDTO> updateSeats(@RequestParam long busId, @Valid @RequestParam int seatCapacity) throws BusNotFoundException
	{
		
		BusDTO updatedBus = busService.updateSeat(busId,seatCapacity);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(updatedBus);
		
	}
	
    
    @PostMapping("/addtrip")
    public ResponseEntity<TripDTO> addTrip(@RequestParam long busId,@Valid @RequestBody TripDTO trip) throws TripNotFoundException, BusNotFoundException, BusServiceException  
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
    
    
    @GetMapping("/getbooking/{bookingId}")
	public ResponseEntity<BookingDTO> getBookingbyId(@PathVariable long bookingId) throws BookingNotFoundException 
	{
		
		BookingDTO bookingdto = bookingservice.getBooking(bookingId);
		
		return new ResponseEntity<>(bookingdto,HttpStatus.ACCEPTED);
	}
    
    
    @DeleteMapping("/deletebooking/{bookingId}")
	public ResponseEntity<String> deleteBooking(@PathVariable long bookingId) throws BookingNotFoundException 
	{
		
		bookingservice.deleteBooking(bookingId);
		
		return new ResponseEntity<>("Bus Deleted Sucessfully with Booking Id :"+bookingId,HttpStatus.NOT_FOUND);
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
    
    
    @GetMapping("/getAllrating")
	public ResponseEntity<List<RatingDTO>> getAllRatings() throws RatingServiceException 
	{
	    List<RatingDTO> allRating = ratingService.getAllRatings();
	   
	    return new ResponseEntity<>(allRating,HttpStatus.ACCEPTED);
	}
    
    
    @GetMapping("/getrating/{userId}")
	public ResponseEntity<RatingDTO> getRatingByUser(@PathVariable Long userId) throws RatingServiceException 
	{
	    RatingDTO rating =ratingService.getRatingById(userId);
	   
	    return new ResponseEntity<>(rating,HttpStatus.ACCEPTED);
	}

}
