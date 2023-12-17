package com.example.service;

import java.util.List;

import com.example.customexception.BookingNotFoundException;
import com.example.customexception.BusNotFoundException;
import com.example.customexception.BusServiceException;
import com.example.customexception.TripNotFoundException;
import com.example.dto.BookingDTO;

import jakarta.validation.Valid;

public interface BookingService {

	BookingDTO addBooking(@Valid long userId, long tripId, BookingDTO bookingdto) throws TripNotFoundException, BusNotFoundException, BusServiceException;

	void deleteBooking(@Valid long bookingId) throws  BookingNotFoundException;

	BookingDTO getBooking(@Valid long bookingId) throws BookingNotFoundException;

	List<BookingDTO> getAllBooking() throws BookingNotFoundException;

	BookingDTO getBookingByUser(long userId) throws BookingNotFoundException;

}
