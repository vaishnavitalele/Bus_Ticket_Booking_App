package com.example.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.dto.RatingDTO;
import com.example.entity.Rating;


@Component
public class RatingMapper {
	
	@Autowired
    private  ModelMapper modelMapper;

    public RatingMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public RatingDTO convertToDTO(Rating rating) {
        return modelMapper.map(rating, RatingDTO.class);
    }

    public Rating convertToEntity(RatingDTO ratingDTO) {
        return modelMapper.map(ratingDTO, Rating.class);
    }

}
