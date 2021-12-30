package com.flight_app.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * flight exception message
 *
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class FlightDataException extends RuntimeException {

	private static final long serialVersionUID = 5911816906518274419L;
	
	/**
	 * message
	 */
	private String message;
	
	

}
