package com.flight_app.ui;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * error response model
 *
 */
@NoArgsConstructor
@Getter
@Setter
public class ErrorResponseModel {

	/**
	 * code
	 *
	 */
	private HttpStatus code;

	/**
	 * status
	 *
	 */
	private String status;

	/**
	 * message
	 *
	 */
	private String message;

	/**
	 * stackTrace
	 *
	 */
	private String stackTrace;

	/**
	 * data
	 *
	 */
	private Object data;

}
