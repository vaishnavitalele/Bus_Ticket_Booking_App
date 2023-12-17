package com.example.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import com.example.entity.User;

@Component
public class UserBookingMapper {
	
	@Autowired
    private  ModelMapper modelMapper;

    public UserBookingMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public UserBookingMapper convertToDTO(User user) {
        return modelMapper.map(user, UserBookingMapper.class);
    }

    public User convertToEntity(UserBookingMapper userbookingDTO) {
        return modelMapper.map(userbookingDTO, User.class);
    }

}
