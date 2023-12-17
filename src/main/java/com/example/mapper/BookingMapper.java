package com.example.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.dto.BookingDTO;
import com.example.entity.Booking;


@Component
public class BookingMapper {
	
	@Autowired
    private  ModelMapper modelMapper;

    public BookingMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public BookingDTO convertToDTO(Booking booking) {
        return modelMapper.map(booking, BookingDTO.class);
    }

    public Booking convertToEntity(BookingDTO bookingdto) {
        return modelMapper.map(bookingdto, Booking.class);
    }

}
