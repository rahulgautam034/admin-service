
package com.flight_app.service;

import java.util.ArrayList;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.flight_app.dao.UserRepository;
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

	private final UserRepository userRepository;
	private final ModelMapper modelMapper;

	public JWTUserDetailsService(UserRepository userRepository, ModelMapper modelMapper) {
		this.userRepository = userRepository;
		this.modelMapper = modelMapper;
	}

	/**
	 * JWTUserDetailsService get and set the users
	 *
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		log.info("called loadUserByUsername");

		AuthEntity user = userRepository.findByUserName(username);
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

	public static String bcryptPassword(String password) {
		log.info("called bcryptPassword");
		PasswordEncoder encoder = new BCryptPasswordEncoder();

		return encoder.encode(password);
	}

	/**
	 * find user from DB
	 *
	 */
	public UserDto findUser(String userName) {
		log.info("called findUser");
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		AuthEntity user = userRepository.findByUserName(userName);
		log.info("user ************"+user);
		if (user != null) {
			return modelMapper.map(user, UserDto.class);
		} else {
			log.error("Error fetching user");
			return null;
		}
	}
}
