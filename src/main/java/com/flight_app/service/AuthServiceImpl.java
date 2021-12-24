package com.flight_app.service;

import org.springframework.stereotype.Service;

import com.flight_app.dao.UserRepository;
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

	public final UserRepository userRepository;

	public AuthServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	/**
	 * validate the user
	 *
	 */
	@Override
	public AuthEntity validateUser(AuthDTO authDTO) {
		log.info("called validateUser");
		AuthEntity user = userRepository.findByUserName(authDTO.getUserName());

		if (user != null) {
			if (user.getPassword().equals(authDTO.getPassword())) {
				return user;
			} else {
				return null;
			}
		} else {
			return null;

		}
	}

}
