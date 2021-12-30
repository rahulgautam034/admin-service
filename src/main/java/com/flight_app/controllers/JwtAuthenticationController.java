
package com.flight_app.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.flight_app.cofig.JwtTokenUtil;
import com.flight_app.dto.JwtRequest;
import com.flight_app.dto.JwtResponse;
import com.flight_app.dto.UserDto;
import com.flight_app.service.JWTUserDetailsService;

import lombok.extern.log4j.Log4j2;

/**
 * for authentication & validation
 *
 */


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/admin/")
@Log4j2
public class JwtAuthenticationController {

	/**
	 * authentication manager
	 *
	 */
	private final AuthenticationManager authManager;
	/**
	 * token util bean for token validation
	 *
	 */
	private final JwtTokenUtil jwtTokenUtil;

	/**
	 * service class
	 *
	 */
	private final JWTUserDetailsService userService;

	/**
	 *constructor
	 *
	 */
	public JwtAuthenticationController(final AuthenticationManager authManager, final JwtTokenUtil jwtTokenUtil,
			final JWTUserDetailsService userService) {
		this.authManager = authManager;
		this.jwtTokenUtil = jwtTokenUtil;
		this.userService = userService;
	}

	/**
	 * authenticate user when trying to login
	 *
	 */
	@RequestMapping(value = "authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody final JwtRequest jwtRequest) throws Exception {
		log.info("started createAuthenticationToken **");

		authenticate(jwtRequest.getUsername(), jwtRequest.getPassword());

		final UserDetails userDetails = userService.loadUserByUsername(jwtRequest.getUsername());

		final String token = jwtTokenUtil.generateToken(userDetails);

		return ResponseEntity.ok(new JwtResponse(token));
	}

	/**
	 * internal authentication by authentication manager
	 *
	 */
	private void authenticate(final String username, final String password) throws Exception {
		log.info("started authenticate **");
		try {
			authManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new RuntimeException("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new RuntimeException("INVALID_CREDENTIALS", e);
		}
	}

	/**
	 * find logged in user detail
	 *
	 */
	@GetMapping("user/{userName}")
	public ResponseEntity<UserDto> getCurrentLoggedInUser(@PathVariable final String userName) {
		log.info("started getCurrentLoggedInUser **");
		final UserDto userDto = userService.findUser(userName);

		return ResponseEntity.status(HttpStatus.OK).body(userDto);
	}
}
