package com.example.customexception;

public class TripNotFoundException extends Exception{
	public TripNotFoundException(String msg) {
		super(msg);
	}

}
