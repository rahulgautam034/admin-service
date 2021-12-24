package com.flight_app.proxy;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.flight_app.dto.FlightDto;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

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
	public FlightDto addFlight(@RequestBody FlightDto flightDto);

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
