package com.example.mapper;

import org.modelmapper.ModelMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.dto.BusDTO;
import com.example.entity.Bus;

@Component
public class BusMapper {

	@Autowired
    private  ModelMapper modelMapper;

    public BusMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public BusDTO convertToDTO(Bus bus) {
        return modelMapper.map(bus, BusDTO.class);
    }

    public Bus convertToEntity(BusDTO busDTO) {
        return modelMapper.map(busDTO, Bus.class);
    }
}
