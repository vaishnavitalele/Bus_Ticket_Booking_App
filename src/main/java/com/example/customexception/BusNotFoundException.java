package com.example.customexception;

public class BusNotFoundException extends Exception{
	
	public BusNotFoundException(String message)
	{
		super(message);
	}
}
