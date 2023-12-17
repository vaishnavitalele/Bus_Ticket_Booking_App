package com.example.exceptionhandling;

import java.util.HashMap;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.customexception.BookingNotFoundException;
import com.example.customexception.BusNotFoundException;
import com.example.customexception.RatingServiceException;
import com.example.customexception.TripNotFoundException;



@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(TripNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<String> handleTripNotFoundException(TripNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
    
	@ExceptionHandler(value=MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> handleInvalidException(MethodArgumentNotValidException ex)
	{
		Map<String, String> errormap = new HashMap<>();
		ex.getBindingResult().getFieldErrors().forEach(error -> 
		
			errormap.put(error.getField(), error.getDefaultMessage())
		);
		
		return new ResponseEntity<>(errormap,HttpStatus.BAD_REQUEST);
	}


    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<String> handleGenericException(Exception ex) 
    {
        return new ResponseEntity<>("Internal server error: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    @ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(value=BusNotFoundException.class)
	public ResponseEntity<String> handleIllegalArgumentException(BusNotFoundException ex)
	{
		return new ResponseEntity<>(ex.getMessage(),HttpStatus.BAD_REQUEST);
	}
    
    @ResponseStatus(HttpStatus.NOT_FOUND)
   	@ExceptionHandler(value=BookingNotFoundException.class)
   	public ResponseEntity<String> handleIllegalArgumentException(BookingNotFoundException ex)
   	{
   		return new ResponseEntity<>(ex.getMessage(),HttpStatus.BAD_REQUEST);
   	}
    
    @ExceptionHandler(value=IllegalArgumentException.class)
	public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex)
	{
		return new ResponseEntity<>(ex.getMessage(),HttpStatus.BAD_REQUEST);
	}
    
    @ExceptionHandler(value=RatingServiceException.class)
   	public ResponseEntity<String> handleIllegalArgumentException(RatingServiceException ex)
   	{
   		return new ResponseEntity<>(ex.getMessage(),HttpStatus.BAD_REQUEST);
   	}
}
