package com.flight_app.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * FlightDto
 *
 */

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FlightDto {

	/**
	 * flight id
	 *
	 */
	private String flightId;

	/**
	 * airLine
	 *
	 */
	private String airLine;

	/**
	 * source
	 *
	 */
	private String source;

	/**
	 * destination
	 *
	 */
	private String destination;

	/**
	 * startDate
	 *
	 */
	private LocalDate startDate;

	/**
	 * endDate
	 *
	 */
	private LocalDate endDate;

	/**
	 * startTime
	 *
	 */
	private String startTime;

	/**
	 * endTime
	 *
	 */
	private String endTime;

	/**
	 * instrumentUsed
	 *
	 */
	private String instrumentUsed;

	/**
	 * businessClassSeats
	 *
	 */
	private Integer businessClassSeats;

	/**
	 * nonBusinessClassSeats
	 *
	 */
	private Integer nonBusinessClassSeats;

	/**
	 * ticketCost
	 *
	 */
	private Double ticketCost;

	/**
	 * totalRows
	 *
	 */
	private Integer totalRows;

	/**
	 * meal
	 *
	 */
	private String meal;

	/**
	 * status
	 *
	 */
	private String status;

}
