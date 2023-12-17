package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.customexception.RatingServiceException;
import com.example.dto.RatingDTO;
import com.example.service.RatingService;

import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/ratings")
public class RatingController {
	
	@Autowired
	private RatingService ratingService;
	

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
	
	
	@GetMapping("/getAllrating")
	public ResponseEntity<List<RatingDTO>> getAllRatings() throws RatingServiceException 
	{
	    List<RatingDTO> allRating = ratingService.getAllRatings();
	   
	    return new ResponseEntity<>(allRating,HttpStatus.ACCEPTED);
	}

	
	@PutMapping("/updaterating/{id}")
	public ResponseEntity<RatingDTO> updateRating(@PathVariable Long id, @Valid @RequestBody RatingDTO ratingDTO) throws RatingServiceException 
	{
		RatingDTO updatedRating = ratingService.updateRating(id, ratingDTO);
		
		return new ResponseEntity<>(updatedRating,HttpStatus.ACCEPTED);
	    
	}
	

	@DeleteMapping("/deleterating/{id}")
	public ResponseEntity<String> deleteRating(@PathVariable Long id) throws RatingServiceException
	{
		ratingService.deleteRating(id);
		
		return new ResponseEntity<>("Rating Deleted Sucessfully",HttpStatus.OK);
	}
	
	
	@GetMapping("/getratingByuser/{userId}")
	public ResponseEntity<RatingDTO> getRatingByUser(@PathVariable Long userId) throws RatingServiceException 
	{
	    RatingDTO rating =ratingService.getRatingByUser(userId);
	   
	    return new ResponseEntity<>(rating,HttpStatus.ACCEPTED);
	}
}
				

				

			    
			

		

	



