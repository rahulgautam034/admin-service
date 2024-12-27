package com.flight_app.proxy;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import com.flight_app.dto.FlightDto;

/**
 * used to connect FLIGHT-WS
 *
 */
@FeignClient(value = "FLIGHT-WS")
public interface FlightProxy {

	/**
	 * add new flight
	 * 
	 * @param flightDto
	 * @return
	 */
	@PostMapping("/airline/inventory/add")
	FlightDto addFlight(@RequestBody FlightDto flightDto);

	/**
	 * block flight
	 * 
	 * @param flightId
	 * @return
	 */
	@PutMapping("/airline/inventory/block/{flightId}")

	public FlightDto blockFlight(@PathVariable String flightId);

	/**
	 * get all flights
	 * 
	 * @return
	 */
	@GetMapping("/airline/flights")

	public List<FlightDto> getAllFlights();

}
