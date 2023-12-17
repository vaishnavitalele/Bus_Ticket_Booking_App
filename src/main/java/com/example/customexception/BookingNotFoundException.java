package com.example.customexception;

public class BookingNotFoundException extends Exception{
	
	public BookingNotFoundException(String message)
	{
		super(message);
	}
}
