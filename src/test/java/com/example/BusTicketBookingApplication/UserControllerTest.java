package com.example.BusTicketBookingApplication;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.controller.UserController;
import com.example.customexception.BusNotFoundException;
import com.example.customexception.TripNotFoundException;
import com.example.dto.BusDTO;
import com.example.dto.UserDTO;
import com.example.dto.UserLoginDTO;
import com.example.entity.Trip;
import com.example.entity.User;
import com.example.service.BookingService;
import com.example.service.RatingService;
import com.example.service.TripService;
import com.example.service.UserService;

public class UserControllerTest {

    @Mock
    private UserService userService;

    @Mock
    private BookingService bookingService;

    @Mock
    private TripService tripService;

    @Mock
    private RatingService ratingService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }
    
   

    @Test
    public void testRegisterUserWithInvalidInput() {
        UserDTO userDto = new UserDTO("userUsername", "userPassword", "mobNo", "emailId", "firstname", "lastname");

        // Mock the behavior of userService.registerUser() method
        doThrow(new IllegalArgumentException("Invalid input")).when(userService).registerUser(userDto);

        ResponseEntity<String> response = userController.registerUser(userDto);

        assertEquals("Invalid input", response.getBody());
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }



    @Test
    public void testGettingTrip() throws TripNotFoundException {
        List<Trip> tripList = new ArrayList<>(); // Add some trips to the list

        // Mock the behavior of tripService.getAllTrip() method
        when(tripService.getAllTrip()).thenReturn(tripList);

        ResponseEntity<List<Trip>> response = userController.gettingTrip();

        assertEquals(tripList, response.getBody());
        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
    }


    @Test
    public void testGetUserById() {
        long userId = 1L;
        User user = new User();

        when(userService.getUserById(userId)).thenReturn(user);

        ResponseEntity<User> responseEntity = userController.getUserById(userId);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isEqualTo(user);

        verify(userService, times(1)).getUserById(userId);
    }

    
}
