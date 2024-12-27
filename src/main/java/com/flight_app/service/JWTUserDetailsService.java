
package com.flight_app.service;

import java.util.ArrayList;

import com.flight_app.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.flight_app.dto.UserDto;
import com.flight_app.entities.AuthEntity;

import lombok.extern.log4j.Log4j2;

/**
 * JWTUserDetailsService get and set the users
 *
 */

@Service
@Log4j2
public class JWTUserDetailsService implements UserDetailsService {

	/**
	 * user repository
	 *
	 */
	private final UserRepository userRepository;
	
	/**
	 * model mapper bean
	 *
	 */
	private final ModelMapper modelMapper;

	/**
	 * all argument constructor
	 *
	 */
	public JWTUserDetailsService(UserRepository userRepository, ModelMapper modelMapper) {
		this.userRepository = userRepository;
		this.modelMapper = modelMapper;
	}

	/**
	 * JWTUserDetailsService get and set the users
	 *
	 */
	@Override
	public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
		log.info("called loadUserByUsername");

		final AuthEntity user = userRepository.findByUserName(username);
		if (user != null) {
			return new User(user.getUserName(), bcryptPassword(user.getPassword()), new ArrayList<>());
		} else {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}

	}

	/**
	 * bcryptPassword
	 *
	 */

	public static String bcryptPassword(final String password) {
		log.info("called bcryptPassword");
		final PasswordEncoder encoder = new BCryptPasswordEncoder();

		return encoder.encode(password);
	}

	/**
	 * find user from DB
	 *
	 */
	public UserDto findUser(final String userName) {
		log.info("called findUser");
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		final AuthEntity user = userRepository.findByUserName(userName);
		if (user != null) {
			return modelMapper.map(user, UserDto.class);
		} else {
			log.error("Error fetching user");
			return null;
		}
	}
}
