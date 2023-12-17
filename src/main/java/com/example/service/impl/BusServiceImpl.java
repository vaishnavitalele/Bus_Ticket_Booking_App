package com.example.service.impl;

import java.util.List;

import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.BusTicketBookingApplication.BusTicketBookingApplication;
import com.example.customexception.BusNotFoundException;
import com.example.dto.BusDTO;
import com.example.entity.Bus;
import com.example.mapper.BusMapper;
import com.example.repository.BusRepository;
import com.example.service.BusService;

import jakarta.validation.Valid;

@Service
public class BusServiceImpl implements BusService {
	
	public static Logger logger = LoggerFactory.getLogger(BusTicketBookingApplication.class);
	
	@Autowired
	private BusRepository busrepo;
	
	@Autowired
	private BusMapper busmap;
	

	@Override
	public BusDTO createBus(@Valid BusDTO busdto) throws BusNotFoundException 
	{
		logger.info("Adding Bus");
		
		if(busrepo.existsByBusNo(busdto.getBusNo()))
		{
			logger.warn("This bus is already added ,please add another bus");
			
			throw new BusNotFoundException("Bus Already Added");
		}
		
		Bus bus = new Bus();
		bus.setBusName(busdto.getBusName());
		bus.setBusNo(busdto.getBusNo());
		bus.setBusType(busdto.getBusType());
		bus.setSeatCapacity(busdto.getSeatCapacity());
		bus.setTotalSeatAvailable(busdto.getSeatCapacity());
       
		Bus savedBus = busrepo.save(bus);
        
		logger.info("Bus Added Sucessfully");
		
		return busmap.convertToDTO(savedBus);
    }


	@Override
	public BusDTO updateBus(@Valid long busId, BusDTO busDto) throws BusNotFoundException 
	{
		logger.info("You are enter in bus update process");
		
		Bus exitingBus = busrepo.findById(busId).orElse(null);
		
		if(exitingBus != null)
		{
			exitingBus.setBusName(busDto.getBusName());
			exitingBus.setBusNo(busDto.getBusNo());
			exitingBus.setBusType(busDto.getBusType());
			exitingBus.setSeatCapacity(busDto.getSeatCapacity());
			exitingBus.setTotalSeatAvailable(busDto.getSeatCapacity());
	
			Bus updatedBus = busrepo.save(exitingBus);
			
			logger.info("Bus Details Updated Sucessfully");
			
			return busmap.convertToDTO(updatedBus);
		}
		else
		{
			logger.warn("Bus Not Found");
			
			throw new BusNotFoundException("Bus Not Found");
		}
	}


	@Override
	public String deleteBusbyId(@Valid long busId) throws BusNotFoundException 
	{
		logger.info("You are enter in bus delete process");
		
		Bus bus = busrepo.findById(busId).orElse(null);
		
		if(bus != null)
		{	
			busrepo.deleteById(busId);
			
			logger.info("Bus Deleted SucessFully");
			
			return "Bus Deleted SucessFully :";
		}
		else
		{
			logger.warn("Your bus Id is not found");
			
			throw new BusNotFoundException("Bus not found with ID: " + busId);
		}
	}


	@Override
	public BusDTO getBusbyId(@Valid long busId) throws BusNotFoundException 
	{
		logger.info("You are getting bus deatils by busId");
		
		Bus bus = busrepo.findById(busId).orElse(null);
		
		if(bus != null)
		{
			return busmap.convertToDTO(bus);
		}
		else
		{
			logger.warn("Your bus Id is not found");
			
			throw new BusNotFoundException("Bus not found with ID: " + busId);
		}
	}


	@Override
	public List<BusDTO> getAllBuses() throws BusNotFoundException 
	{	
		logger.info("You are getting All bus deatils");
		
		List<Bus> allBuses = busrepo.findAll();
		
		if(allBuses.isEmpty())
		{
			throw new BusNotFoundException("No Buses Found");
		}
		
		return allBuses.stream().map(busmap::convertToDTO).collect((Collectors.toList()));
	}


	@Override
	public BusDTO updateSeat(@Valid long busId, int seatCapacity) throws BusNotFoundException 
	{
		logger.info("You are enter in busseats update process");
		
		Bus exitingBus = busrepo.findById(busId).orElse(null);
		
		if(exitingBus != null)
		{
			exitingBus.setSeatCapacity(seatCapacity);
			exitingBus.setTotalSeatAvailable(exitingBus.getSeatCapacity());
	
			Bus updatedBus = busrepo.save(exitingBus);
			
			logger.info("Bus Seat Updated Sucessfully");
			
			return busmap.convertToDTO(updatedBus);
		}
		else
		{
			logger.warn("Your bus Id is not found");
			
			throw new BusNotFoundException("Bus Details Not Found With Id : "+busId);
		}
		
	}
}
