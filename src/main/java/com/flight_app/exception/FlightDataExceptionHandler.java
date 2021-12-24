package com.flight_app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.flight_app.ui.ErrorResponseModel;

/**
 * flight exception handler
 *
 */
@ControllerAdvice

public class FlightDataExceptionHandler {
	
	/**
	 * flight exception handling
	 * @param e
	 * @return
	 */
	@ExceptionHandler(FlightDataException.class)
	
	public ResponseEntity<?> handleFlightException(FlightDataException e){
		
		ErrorResponseModel error = new ErrorResponseModel();
		
		error.setCode(HttpStatus.BAD_REQUEST);
		error.setMessage(e.getMessage());
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
		
	}

}
