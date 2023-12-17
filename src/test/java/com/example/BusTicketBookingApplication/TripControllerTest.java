package com.example.BusTicketBookingApplication;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import com.example.controller.TripController;
import com.example.customexception.BusNotFoundException;
import com.example.customexception.BusServiceException;
import com.example.customexception.TripNotFoundException;
import com.example.dto.TripDTO;
import com.example.entity.Trip;
import com.example.service.TripService;

@SpringBootTest

public class TripControllerTest {

    @Mock
    private TripService tripService;

    @InjectMocks
    private TripController tripController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddTrip() throws TripNotFoundException, BusNotFoundException, BusServiceException {
        TripDTO tripDTO = new TripDTO();
        when(tripService.addingTrip(anyLong(), any(TripDTO.class))).thenReturn(tripDTO);

        ResponseEntity<TripDTO> response = tripController.addTrip(1L, tripDTO);

        verify(tripService, times(1)).addingTrip(1L, tripDTO);
        
        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
        Assertions.assertEquals(tripDTO, response.getBody());
    }

    
    @Test
    public void testDeleteTrip() throws TripNotFoundException {
        // Mock data
        long tripId = 1L;

        // Call the method to test
        ResponseEntity<String> responseEntity = tripController.deleteTrip(tripId);

        // Verify the response
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isEqualTo("Trip Deleted SucessFully");

        // Verify interactions with dependencies
        verify(tripService, times(1)).deleteTrip(tripId);
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
    
    @Test
    public void testFindTrips() throws TripNotFoundException {
        // Mock data
        String fromLocation = "Location1";
        String toLocation = "Location2";
        LocalDate localDate = LocalDate.now();
        Trip trip = new Trip();

        // Mock dependencies
        when(tripService.findtrip(fromLocation, toLocation, localDate)).thenReturn(trip);

        // Call the method to test
        ResponseEntity<Trip> responseEntity = tripController.findTrips(fromLocation, toLocation, localDate);

        // Verify the response
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.ACCEPTED);
        assertThat(responseEntity.getBody()).isEqualTo(trip);

        // Verify interactions with dependencies
        verify(tripService, times(1)).findtrip(fromLocation, toLocation, localDate);
    }
}


