package com.example.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.dto.AdminDTO;
import com.example.entity.Admin;


@Component
public class AdminMapper {
	
	@Autowired
    private  ModelMapper modelMapper;

    public AdminMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public AdminDTO convertToDTO(Admin admin) {
        return modelMapper.map(admin, AdminDTO.class);
    }

    public Admin convertToEntity(AdminDTO admindto) {
        return modelMapper.map(admindto, Admin.class);
    }
}
