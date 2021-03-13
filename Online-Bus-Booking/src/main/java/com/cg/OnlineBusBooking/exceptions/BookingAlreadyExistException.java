package com.cg.OnlineBusBooking.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//Http Status at input time to confirm what the issue is
@ResponseStatus(code = HttpStatus.CONFLICT)
public class BookingAlreadyExistException extends RuntimeException {
	
	public BookingAlreadyExistException() {
		
	}
	
	public BookingAlreadyExistException(String message) {
		super(message);
	}
}
