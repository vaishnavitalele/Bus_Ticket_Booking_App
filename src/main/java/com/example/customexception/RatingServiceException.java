package com.example.customexception;

public class RatingServiceException  extends Exception {
	
	public RatingServiceException(String message)
	{
		super(message);
	}
}
