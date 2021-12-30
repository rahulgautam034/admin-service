package com.flight_app.ui;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * FlightRequestModel to get flight data 
 *
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class FlightRequestModel {

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
	private long ticketCost;

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
