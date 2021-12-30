package com.flight_app.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * AuthDTO
 *
 */

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AuthDTO {

	/**
	 * user name
	 *
	 */
	private String userName;

	/**
	 * password
	 *
	 */
	private String password;

}
