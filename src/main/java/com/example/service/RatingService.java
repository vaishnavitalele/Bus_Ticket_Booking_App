package com.example.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.BusTicketBookingApplication.BusTicketBookingApplication;
import com.example.customexception.RatingServiceException;
import com.example.dto.RatingDTO;
import com.example.entity.Rating;
import com.example.entity.User;
import com.example.mapper.RatingMapper;
import com.example.repository.RatingRepository;
import com.example.repository.UserRepository;

import jakarta.validation.Valid;

import java.util.List;

import java.util.stream.Collectors;

@Service
public class RatingService {
	
	
	public static Logger logger = LoggerFactory.getLogger(BusTicketBookingApplication.class);

	@Autowired
	private RatingRepository ratingRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RatingMapper ratingmap;
	

	public RatingDTO createRating(@Valid long userid, RatingDTO ratingDTO) 
	{
		logger.info("Giving Rating To App");

		User user = userRepository.findById(userid).orElse(null);

		if (user == null) 
		{
			logger.warn("User is not found");
			
			throw new IllegalArgumentException("User Not Found");
		}

		// Set properties from DTO
		Rating rating = new Rating();
		rating.setFirstName(ratingDTO.getFirstName());
		rating.setLastName(ratingDTO.getLastName());
		rating.setPhoneNumber(ratingDTO.getPhoneNumber());
		rating.setEmailId(ratingDTO.getEmailId());
		rating.setRating(ratingDTO.getRating());
		rating.setUserId(user);

		// Calculate averageRating
		updateAverageRating(rating);

		ratingRepository.save(rating);

		logger.info("Rating Done Sucessfully");
		
		return ratingmap.convertToDTO(rating);
	}

	
	public RatingDTO getRatingById(Long id) throws RatingServiceException 
	{
		logger.info("Gettng Rating Details");
		
		Rating rating = ratingRepository.findById(id).orElse(null);

		if (rating == null) 
		{
			logger.warn("Your rating is not found");
			
			throw new RatingServiceException("Rating Details Not Found");
		}

		return ratingmap.convertToDTO(rating);
	}
	

	public List<RatingDTO> getAllRatings() throws RatingServiceException 
	{
		logger.info("You are getting all rating deatils");
		
		List<Rating> ratings = ratingRepository.findAll();
		
		if (ratings.isEmpty()) 
		{
			logger.warn("Your rating is not available");
			
			throw new RatingServiceException("No Rating Available");
		}

		return ratings.stream().map(ratingmap::convertToDTO).collect((Collectors.toList()));
	}

	
	public RatingDTO updateRating(@Valid Long id, RatingDTO ratingDTO) throws RatingServiceException 
	{
		logger.info("You are enter in rating update method");
		
		Rating existingrating = ratingRepository.findById(id).orElse(null);

		if (existingrating == null)
		{
			logger.warn("Your rating is not available");
			
			throw new RatingServiceException("Rating Not Found");
		}

		// Update properties from DTO
		existingrating.setFirstName(ratingDTO.getFirstName());
		existingrating.setLastName(ratingDTO.getLastName());
		existingrating.setPhoneNumber(ratingDTO.getPhoneNumber());
		existingrating.setEmailId(ratingDTO.getEmailId());
		existingrating.setRating(ratingDTO.getRating());

		// Recalculate averageRating
		updateAverageRating(existingrating);

		ratingRepository.save(existingrating);
		
		logger.info("Rating Details Updated sucessfully");

		return ratingmap.convertToDTO(existingrating);

	}
	

	public void deleteRating(Long id) throws RatingServiceException
	{
		logger.info("You are enter in rating delete method");
		
		if (ratingRepository.existsById(id)) 
		{
			logger.info("Rating Deleted Sucessfully");
			
			ratingRepository.deleteById(id);
		} 
		else 
		{
			logger.warn("Your rating is not found");
			
			throw new RatingServiceException("Rating Details Not Found");
		}
	}
	
	
	public RatingDTO getRatingByUser(Long userId) throws RatingServiceException 
	{
		logger.info("getting Rating Details by UserId");
		
		Rating rating = ratingRepository.findByUser_UserId(userId);

		if (rating != null) 
		{
			
			return ratingmap.convertToDTO(rating);
		} 
		else 
		{
			logger.warn("Your rating is not found");
			
			throw new RatingServiceException("Rating Details Not Found");
		}
	}

	// Calculating Average Rating
	private void updateAverageRating(Rating rating) 
	{
		logger.info("You are enter in Calulating  average rating method");
		
		List<Rating> ratings = ratingRepository.findAll();
		
		double totalRating = ratings.stream().mapToInt(Rating::getRating).sum();
		
		double averageRating = (totalRating + rating.getRating()) / (ratings.size() + 1);
		
		logger.info("Calculated Average Rating");
		
		rating.setAverageRating(averageRating);
	}


	

	
}
