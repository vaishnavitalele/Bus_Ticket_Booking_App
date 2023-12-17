package com.example.BusTicketBookingApplication;


import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import com.example.controller.TripController;
import com.example.customexception.BookingNotFoundException;
import com.example.customexception.BusNotFoundException;
import com.example.customexception.BusServiceException;
import com.example.customexception.TripNotFoundException;
import com.example.dto.BookingDTO;
import com.example.dto.TripDTO;
import com.example.entity.Booking;
import com.example.entity.Trip;
import com.example.mapper.BookingMapper;
import com.example.repository.BookingRepository;
import com.example.repository.BusRepository;
import com.example.repository.TripRepository;
import com.example.repository.UserRepository;
import com.example.service.TripService;
import com.example.service.impl.BookingServiceImpl;

public class BookingServiceImplTest {

	@Mock
    private BookingMapper bookingMapper;
    
    @Mock
    private TripService tripService;

    @InjectMocks
    private TripController tripController;


    @Mock
    private BookingRepository bookingRepository;

    @Mock
    private TripRepository tripRepository;

    @Mock
    private BusRepository busRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private BookingServiceImpl bookingService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }
  
    @Test
    public void testGetBooking() throws BookingNotFoundException {
        // Mock data
        long bookingId = 1L;
        Booking booking = new Booking();
        BookingDTO bookingDTO = new BookingDTO();

        // Mock dependencies
        when(bookingRepository.findById(bookingId)).thenReturn(Optional.of(booking));
        when(bookingMapper.convertToDTO(booking)).thenReturn(bookingDTO);

        // Call the method to test
        BookingDTO result = bookingService.getBooking(bookingId);

        // Verify the result
        assertNotNull(result);
        // Add more assertions based on your actual implementation

        // Verify interactions with dependencies
        verify(bookingRepository, times(1)).findById(bookingId);
        verify(bookingMapper, times(1)).convertToDTO(booking);
    }

    @Test
    public void testGetAllBooking() throws BookingNotFoundException {
        // Mock data
        List<Booking> existingBooking = new ArrayList<>();
        Booking booking = new Booking();
        existingBooking.add(booking);

        // Mock dependencies
        when(bookingRepository.findAll()).thenReturn(existingBooking);
        when(bookingMapper.convertToDTO(booking)).thenReturn(new BookingDTO());

        // Call the method to test
        List<BookingDTO> result = bookingService.getAllBooking();

        // Verify the result
        assertNotNull(result);
        
        // Verify interactions with dependencies
        verify(bookingRepository, times(1)).findAll();
        verify(bookingMapper, times(1)).convertToDTO(booking);
    }
    
    

    @Test
    void testAddTrip() throws TripNotFoundException, BusNotFoundException, BusServiceException {
        TripDTO tripDTO = new TripDTO();
        when(tripService.addingTrip(anyLong(), any(TripDTO.class))).thenReturn(tripDTO);

        ResponseEntity<TripDTO> response = tripController.addTrip(1L, tripDTO);

        verify(tripService, times(1)).addingTrip(1L, tripDTO);
        
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(tripDTO, response.getBody());
    }

    private void assertEquals(HttpStatus created, HttpStatusCode statusCode) {
		
		
	}

	private void assertEquals(TripDTO tripDTO, TripDTO tripDTO2) {
		
	}

	
	@Test
    void testDeleteTrip() throws TripNotFoundException {
        long tripId = 1L;
        doNothing().when(tripService).deleteTrip(tripId);

        ResponseEntity<String> response = tripController.deleteTrip(tripId);

        verify(tripService, times(1)).deleteTrip(tripId);
        
        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
        assertEquals(new Trip(), response.getBody());
    }

    private void assertEquals(Trip trip, String body) {
		
		
	}

	@Test
    void testGettingTrip() throws TripNotFoundException {
        List<Trip> trips = Arrays.asList(new Trip(), new Trip());
        when(tripService.getAllTrip()).thenReturn(trips);

        ResponseEntity<List<Trip>> response = tripController.gettingTrip();

        verify(tripService, times(1)).getAllTrip();
        
    }

    @Test
    void testUpdateTrip() throws TripNotFoundException {
        long tripId = 1L;
        TripDTO tripDTO = new TripDTO();
        when(tripService.updateTrip(eq(tripId), any(TripDTO.class))).thenReturn(new Trip());

        ResponseEntity<Trip> response = tripController.updatetrip(tripId, tripDTO);

        verify(tripService, times(1)).updateTrip(tripId, tripDTO);
       
    }
}
