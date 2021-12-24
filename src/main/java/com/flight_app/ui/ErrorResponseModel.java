package com.flight_app.ui;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ErrorResponseModel {

	private HttpStatus code;

	private String status;

	private String message;

	private String stackTrace;

	private Object data;

}
