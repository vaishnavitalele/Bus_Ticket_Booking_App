package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.Rating;



public interface RatingRepository extends JpaRepository<Rating, Long>{
	
	 Rating findByUser_UserId(Long userId);

}
