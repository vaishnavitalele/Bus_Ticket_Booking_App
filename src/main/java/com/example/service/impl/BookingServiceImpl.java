package com.example.service.impl;

import java.time.LocalDateTime;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.BusTicketBookingApplication.BusTicketBookingApplication;
import com.example.customexception.BookingNotFoundException;
import com.example.customexception.BusNotFoundException;
import com.example.customexception.BusServiceException;
import com.example.customexception.TripNotFoundException;
import com.example.dto.BookingDTO;
import com.example.entity.Booking;
import com.example.entity.Bus;
import com.example.entity.Trip;
import com.example.entity.User;
import com.example.mapper.BookingMapper;
import com.example.mapper.UserBookingMapper;
import com.example.repository.BookingRepository;
import com.example.repository.BusRepository;
import com.example.repository.TripRepository;
import com.example.repository.UserRepository;
import com.example.service.BookingService;

import jakarta.validation.Valid;


@Service
public class BookingServiceImpl implements BookingService {
	
	public static Logger logger = LoggerFactory.getLogger(BusTicketBookingApplication.class);

	@Autowired
	private BookingMapper bookingmap;
	
	@Autowired
    private BookingRepository bookingrepo;

    @Autowired
    private TripRepository triprepo;
    
    @Autowired
    private BusRepository busrepo;
    
    @Autowired
	private UserRepository userrepo;
    

	@Override
	public BookingDTO addBooking(@Valid long userId,long tripId,BookingDTO bookingdto) throws TripNotFoundException, BusNotFoundException, BusServiceException 
	{
		logger.info("You are enter in booking process");
		
		User user = userrepo.findById(userId)
				.orElseThrow(() -> new IllegalArgumentException("User Not Found"));
	
		
		Trip trip = triprepo.findById(tripId)
                .orElseThrow(() -> new TripNotFoundException("Trip not found"));
		
		Bus bus = busrepo.findById(trip.getBus().getBusId()).orElse(null);

		if(bus != null)
		{
			if(bus.getTotalSeatAvailable()<bookingdto.getBookingSeat())
			{
				logger.warn("seats not available try for another bus .");
				
				throw new BusServiceException("Seat Not Available");
			}
			
	        bus.setTotalSeatAvailable(bus.getTotalSeatAvailable()-bookingdto.getBookingSeat());
	       
	        busrepo.save(bus);
		}
		
		double ticketPrice = trip.getRate() * bookingdto.getBookingSeat();

        Booking booking = new Booking();
        booking.setName(bookingdto.getName());
        booking.setAge(bookingdto.getAge());
        booking.setBookingSeat(bookingdto.getBookingSeat());
        booking.setAmount(ticketPrice);
        booking.setEmailId(bookingdto.getEmailId());
        bookingdto.getLocaldatetime();
		booking.setLocaldatetime(LocalDateTime.now());
        booking.setTrip(trip);
        booking.setUser(user);
        Booking createdBooking = bookingrepo.save(booking);
		
        logger.info("Booking Process Done");
        
        return bookingmap.convertToDTO(createdBooking);
  
	}
	

	@Override
	public void deleteBooking(long bookingId) throws BookingNotFoundException   
	{
		logger.info("You are enter in booking delete process");
		
		Booking booking = bookingrepo.findById(bookingId).orElse(null);
		
		if(bookingrepo.existsById(bookingId))
		{
			bookingrepo.deleteById(bookingId);
			
			Trip trip = booking.getTrip();
			
			Bus bus = busrepo.findById(trip.getBus().getBusId()).orElse(null);
			
			int addedSeat = bus.getTotalSeatAvailable() + booking.getBookingSeat();
			
			bus.setTotalSeatAvailable(addedSeat);
			
			busrepo.save(bus);
		}
		else
		{
			throw new BookingNotFoundException("Booking Details Not Found With : "+bookingId);
		}
		
		logger.info("Booking Deleted Sucessfully");
	}
	

	@Override
	public BookingDTO getBooking(long bookingId) throws BookingNotFoundException 
	{
		logger.info("You are getting booking deatils by bookingId");
		
		Booking booking = bookingrepo.findById(bookingId).orElse(null);
		
		if(booking == null)
		{
			throw new BookingNotFoundException("Booking Details Not Found With : "+bookingId);
		}
		
		return bookingmap.convertToDTO(booking);
	}
	

	@Override
	public List<BookingDTO> getAllBooking() throws BookingNotFoundException 
	{
		logger.info("You are getting all booking deatils");

		List<Booking> existingbooking = bookingrepo.findAll();
		
		if(existingbooking.isEmpty())
		{
			throw new BookingNotFoundException("Booking Details Not Found");
		}
		
		List<BookingDTO> bookingDTOList = new ArrayList<>();
		
		for(Booking booking : existingbooking)
		{
			  BookingDTO bookingdto =  bookingmap.convertToDTO(booking);
			  
			  bookingDTOList.add(bookingdto);
		}
		
		return bookingDTOList;
	}


	@Override
	public BookingDTO getBookingByUser(long userId) throws BookingNotFoundException 
	{
		logger.info("You are getting  booking deatils by userId");
		
		Booking booking = bookingrepo.findById(userId).orElse(null);
		
		if(booking != null)
		{
			return (bookingmap.convertToDTO(booking));
		}
		else
		{
			throw new BookingNotFoundException("No Booking Details Available");
		}
	}
}
