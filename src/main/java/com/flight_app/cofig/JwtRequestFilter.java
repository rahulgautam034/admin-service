package com.flight_app.cofig;

import java.io.IOException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.flight_app.service.JWTUserDetailsService;

import io.jsonwebtoken.ExpiredJwtException;
import lombok.extern.log4j.Log4j2;

/**
 * validate and generate new token
 *
 */
@Component
@Log4j2
public class JwtRequestFilter extends OncePerRequestFilter {

	private final JWTUserDetailsService jwtUserDetailsService;

	private final JwtTokenUtil jwtTokenUtil;

	/**
	 * constructor
	 *
	 */
	public JwtRequestFilter(final JWTUserDetailsService jwtUserDetailsService, final JwtTokenUtil jwtTokenUtil) {
		this.jwtUserDetailsService = jwtUserDetailsService;
		this.jwtTokenUtil = jwtTokenUtil;
	}

	@Override
	/**
	 * filter bearer token
	 */
	protected void doFilterInternal(final HttpServletRequest request, final HttpServletResponse response, final FilterChain chain)
			throws ServletException, IOException {
		log.info("called doFilterInternal");
		final String requestTokenHeader = request.getHeader("Authorization");

		String username = null;
		String jwtToken = null;
		// JWT Token is in the form "Bearer token". Remove Bearer word and get
		// only the Token
		if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
			jwtToken = requestTokenHeader.substring(7);
			try {
				username = jwtTokenUtil.getUsernameFromToken(jwtToken);
			} catch (IllegalArgumentException e) {
				log.info("Unable to get JWT Token");
			} catch (ExpiredJwtException e) {
				log.info("JWT Token has expired");
			}
		} else {
			log.info("JWT Token does not begin with Bearer String");
		}

		// Once we get the token validate it.
		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

			UserDetails userDetails = this.jwtUserDetailsService.loadUserByUsername(username);

			// if token is valid configure Spring Security to manually set
			// authentication
			if (jwtTokenUtil.validateToken(jwtToken, userDetails)) {

				final UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());
				usernamePasswordAuthenticationToken
						.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				// After setting the Authentication in the context, we specify
				// that the current user is authenticated. So it passes the
				// Spring Security Configurations successfully.
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			}
		}
		chain.doFilter(request, response);
	}

}
