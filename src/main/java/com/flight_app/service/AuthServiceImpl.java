package com.flight_app.service;

import com.flight_app.repository.UserRepository;
import org.springframework.stereotype.Service;

import com.flight_app.dto.AuthDTO;
import com.flight_app.entities.AuthEntity;

import lombok.extern.log4j.Log4j2;

/**
 * AuthServiceImpl for user validation
 *
 */

@Service
@Log4j2
public class AuthServiceImpl implements AuthService {

	/**
	 * user repository
	 *
	 */
	public final UserRepository userRepository;

	/**
	 * class all args constructor
	 *
	 */
	public AuthServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	/**
	 * validate the user
	 *
	 */
	@Override
	public AuthEntity validateUser(final AuthDTO authDTO) {
		log.info("called validateUser");
		final AuthEntity user = userRepository.findByUserName(authDTO.getUserName());

		if (user != null && user.getPassword().equals(authDTO.getPassword())) {
			return user;
		} else {
			return null;

		}
	}

}
