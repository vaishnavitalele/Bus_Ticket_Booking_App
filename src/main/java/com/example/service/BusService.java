package com.example.service;

import java.util.List;


import org.springframework.stereotype.Service;

import com.example.customexception.BusNotFoundException;

import com.example.dto.BusDTO;

import jakarta.validation.Valid;

@Service
public interface BusService {

	BusDTO createBus(@Valid BusDTO busDTO) throws  BusNotFoundException;

	BusDTO updateBus(@Valid long busId, BusDTO busDto) throws BusNotFoundException;

	String deleteBusbyId(@Valid long busId) throws BusNotFoundException;

	BusDTO getBusbyId(@Valid long busId) throws BusNotFoundException;

	List<BusDTO> getAllBuses() throws BusNotFoundException;

	BusDTO updateSeat(@Valid long busId, int seatCapacity) throws BusNotFoundException;
	
}
