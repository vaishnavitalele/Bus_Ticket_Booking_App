package com.example.BusTicketBookingApplication;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.customexception.RatingServiceException;
import com.example.dto.RatingDTO;
import com.example.entity.Rating;
import com.example.entity.User;
import com.example.mapper.RatingMapper;
import com.example.repository.RatingRepository;
import com.example.repository.UserRepository;
import com.example.service.RatingService;

public class RatingServiceTest {

    @Mock
    private RatingRepository ratingRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private RatingMapper ratingMapper;

    @InjectMocks
    private RatingService ratingService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    
   
    @Test
    public void testGetAllRatings() throws RatingServiceException {
        // Mock data
        Rating rating = new Rating("firstName",
		"lastName",
		" phoneNumber",
		 "emailId",
		 5,
		 10, null);

        // Mock dependencies
        when(ratingRepository.findAll()).thenReturn(Collections.singletonList(rating));

        // Call the method to test
        List<RatingDTO> result = ratingService.getAllRatings();

        // Verify the result
        assertNotNull(result);
        // Add more assertions based on your actual implementation

        // Verify interactions with dependencies
        verify(ratingRepository, times(1)).findAll();
    }

    
    
   
    @Test
    public void testGetRatingById2() throws RatingServiceException {
        // Mock data
        Long ratingId = 1L;
        RatingDTO ratingDTO = new RatingDTO();
        Rating rating = new Rating();

        // Mock dependencies
        when(ratingRepository.findById(ratingId)).thenReturn(Optional.of(rating));
        when(ratingMapper.convertToDTO(rating)).thenReturn(ratingDTO);

        // Call the method to test
        RatingDTO result = ratingService.getRatingById(ratingId);

        // Verify the result
        assertThat(result).isEqualTo(ratingDTO);

        // Verify interactions with dependencies
        verify(ratingRepository, times(1)).findById(ratingId);
        verify(ratingMapper, times(1)).convertToDTO(rating);
    }

    @Test
    public void testGetAllRatings2() throws RatingServiceException {
        // Mock data
        Rating rating1 = new Rating();
        Rating rating2 = new Rating();
        List<Rating> ratings = Arrays.asList(rating1, rating2);

        // Mock dependencies
        when(ratingRepository.findAll()).thenReturn(ratings);
        when(ratingMapper.convertToDTO(any(Rating.class))).thenReturn(new RatingDTO());

        // Call the method to test
        List<RatingDTO> result = ratingService.getAllRatings();

        // Verify the result
        assertThat(result).hasSize(2);

        // Verify interactions with dependencies
        verify(ratingRepository, times(1)).findAll();
        verify(ratingMapper, times(2)).convertToDTO(any(Rating.class));
    }

  
    @Test
    public void testDeleteRating() {
        // Mock data
        Long ratingId = 1L;

        // Mock dependencies
        when(ratingRepository.existsById(ratingId)).thenReturn(true);
        doNothing().when(ratingRepository).deleteById(ratingId);

        // Call the method to test
        assertDoesNotThrow(() -> ratingService.deleteRating(ratingId));

        // Verify interactions with dependencies
        verify(ratingRepository, times(1)).existsById(ratingId);
        verify(ratingRepository, times(1)).deleteById(ratingId);
    }

    @Test
    public void testGetRatingByUser() throws RatingServiceException {
        // Mock data
        Long userId = 1L;
        RatingDTO ratingDTO = new RatingDTO();
        Rating rating = new Rating();

        // Mock dependencies
        when(ratingRepository.findByUser_UserId(userId)).thenReturn(rating);
        when(ratingMapper.convertToDTO(rating)).thenReturn(ratingDTO);

        // Call the method to test
        RatingDTO result = ratingService.getRatingByUser(userId);

        // Verify the result
        assertThat(result).isEqualTo(ratingDTO);

        // Verify interactions with dependencies
        verify(ratingRepository, times(1)).findByUser_UserId(userId);
        verify(ratingMapper, times(1)).convertToDTO(rating);
    }
    
    
}

