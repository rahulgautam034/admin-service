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

	/**
	 * flight id
	 */
	private String flightId;

	/**
	 * name
	 */
	private String name;

	/**
	 * file type
	 */
	private String fileType;

	/**
	 * image blob
	 */
	private byte[] img;

}
