package com.cg.OnlineBusBooking.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//Http Status at input time to confirm what the issue is
@ResponseStatus(code = HttpStatus.CONFLICT)
public class BusDoesnotExistException extends RuntimeException{
	
	public BusDoesnotExistException() {
	}
	
	public BusDoesnotExistException(String message) {
		super(message);
	}

}