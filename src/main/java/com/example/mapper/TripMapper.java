package com.example.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import com.example.dto.TripDTO;
import com.example.entity.Trip;

@Component
public class TripMapper {
	
	@Autowired
    private  ModelMapper modelMapper;

    public TripMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public TripDTO convertToDTO(Trip trip) {
        return modelMapper.map(trip, TripDTO.class);
    }

    public Trip convertToEntity(TripDTO tripDTO) {
        return modelMapper.map(tripDTO, Trip.class);
    }

}
