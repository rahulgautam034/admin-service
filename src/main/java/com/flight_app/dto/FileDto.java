package com.flight_app.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * for upload/download document
 *
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class FileDto {

	private String flightId;

	private String name;

	private String fileType;

	private byte[] img;
	
}
