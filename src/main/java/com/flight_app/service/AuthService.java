package com.flight_app.service;

import com.flight_app.dto.AuthDTO;
import com.flight_app.entities.AuthEntity;

/**
 * for user authentication
 *
 */
public interface AuthService {

	/**
	 * validate user
	 *
	 */
	AuthEntity validateUser(AuthDTO user);

}
