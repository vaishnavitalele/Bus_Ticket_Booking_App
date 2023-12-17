package com.example.BusTicketBookingApplication;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.controller.BusController;
import com.example.customexception.BusNotFoundException;
import com.example.customexception.BusServiceException;
import com.example.dto.BusDTO;
import com.example.service.BusService;

@ExtendWith(MockitoExtension.class)
public class BusControllerTest {

    @Mock
    private BusService busService;

    @InjectMocks
    private BusController busController;

    @Test
    public void testCreateBus() throws BusNotFoundException {
        BusDTO busDto = new BusDTO( 1, "busName",  "busType",  "busNo", 40);

        // Mock the behavior of busService.createBus() method
        when(busService.createBus(any(BusDTO.class))).thenReturn(busDto);

        ResponseEntity<BusDTO> response = busController.createBus(busDto);

        assertEquals(busDto, response.getBody());
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }


    @Test
    public void testGetBus() throws BusNotFoundException, BusServiceException {
        long busId = 1;
        BusDTO expectedBusDto = new BusDTO(1, "busName",  "busType",  "busNo", 40);

        // Mock the behavior of busService.getBusbyId() method
        when(busService.getBusbyId(eq(busId))).thenReturn(expectedBusDto);

        ResponseEntity<BusDTO> response = busController.getBus(busId);

        assertEquals(expectedBusDto, response.getBody());
        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
    }

    @Test
    public void testGetAllBus() throws BusNotFoundException {
        List<BusDTO> busList = new ArrayList<>(); 

        // Mock the behavior of busService.getAllBuses() method
        when(busService.getAllBuses()).thenReturn(busList);

        ResponseEntity<List<BusDTO>> response = busController.getAllBus();

        assertEquals(busList, response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}



