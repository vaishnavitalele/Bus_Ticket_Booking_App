package com.example.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.customexception.BusNotFoundException;
import com.example.dto.BusDTO;
import com.example.service.BusService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/bus")
public class BusController {
	
	@Autowired
    private BusService busService;
	
	
	@PostMapping(path="/addbus")
    public ResponseEntity<BusDTO> createBus(@Valid @RequestBody BusDTO busDto) throws BusNotFoundException
    {
        BusDTO createdBus =  busService.createBus(busDto);
		
        return ResponseEntity.status(HttpStatus.CREATED).body(createdBus);
    }
	
	
	@PutMapping("/updatebus")
	public ResponseEntity<BusDTO> updateBus(@RequestParam long busId ,@Valid @RequestBody BusDTO busDto) throws  BusNotFoundException
    {
        
		BusDTO updatedBus = busService.updateBus(busId,busDto);
        
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(updatedBus);
    }
	
	
	@DeleteMapping("/deletebus")
	public ResponseEntity<String> deleteBus(@RequestParam long busId) throws BusNotFoundException
    {
       
		String deletedBus = busService.deleteBusbyId(busId);
        
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(deletedBus + busId);
    }
	
	
	@GetMapping("/getbus")
	public ResponseEntity<BusDTO> getBus(@RequestParam long busId) throws BusNotFoundException
    {
		
		BusDTO getbus = busService.getBusbyId(busId);
        
		return  ResponseEntity.status(HttpStatus.ACCEPTED).body(getbus);
    }
	
	
	@GetMapping("/getAllbus")
	public ResponseEntity<List<BusDTO>> getAllBus() throws BusNotFoundException
    {
		
		List<BusDTO> buses = busService.getAllBuses();
        
		return ResponseEntity.ok(buses);
    }
	
	
	@PutMapping("/updateseat")
	public ResponseEntity<BusDTO> updateSeats(@RequestParam long busId, @Valid @RequestParam int seatCapacity) throws BusNotFoundException
	{
		
		BusDTO updatedBus = busService.updateSeat(busId,seatCapacity);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(updatedBus);
		
	}
}
