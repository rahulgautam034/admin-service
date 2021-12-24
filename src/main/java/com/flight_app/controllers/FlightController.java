package com.flight_app.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flight_app.dto.FlightDto;
import com.flight_app.exception.FlightDataException;
import com.flight_app.proxy.FlightProxy;
import com.flight_app.ui.FlightRequestModel;
import com.flight_app.ui.FlightResponseModel;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.log4j.Log4j2;

/**
 * admin flight controller
 *
 */
@RestController
@CrossOrigin(origins = "*")
@Log4j2
@RequestMapping("/api/admin/v1.0/flight")
public class FlightController {

	private final ModelMapper modelMapper;

	private final FlightProxy flightProxy;

	@Autowired
	public FlightController(ModelMapper modelMapper, FlightProxy flightProxy) {
		this.modelMapper = modelMapper;
		this.flightProxy = flightProxy;
	}

	/**
	 * add new flight in inventory
	 *
	 */
	@PostMapping()
	@CircuitBreaker(name = "myCircuitBreaker", fallbackMethod = "myTestFallBack")
	public ResponseEntity<FlightResponseModel> addNewFlight(@RequestBody FlightRequestModel flightRequestModel) {
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		log.info("started addNewFlight **");

		FlightDto flightDto = modelMapper.map(flightRequestModel, FlightDto.class);
		String[] uuid = UUID.randomUUID().toString().split("-");

		flightDto.setFlightId(uuid[0]);
		final FlightDto res = flightProxy.addFlight(flightDto);
		if (res.getFlightId().isEmpty()) {
			log.error("failed to add new Flight.");
			throw new FlightDataException("failed to add new Flight.");
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(modelMapper.map(res, FlightResponseModel.class));

	}

	/**
	 * block the existing flight
	 *
	 */
	@PutMapping("/{flightId}")
	@CircuitBreaker(name = "myCircuitBreaker", fallbackMethod = "myTestFallBack")
	public ResponseEntity<FlightResponseModel> blockFlight(@PathVariable String flightId) {
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		log.info("started blockFlight **");
		final FlightDto res = flightProxy.blockFlight(flightId);
		return ResponseEntity.status(HttpStatus.OK).body(modelMapper.map(res, FlightResponseModel.class));

	}

	/**
	 * find all the flights
	 *
	 */
	@GetMapping("/flights")
	@CircuitBreaker(name = "myCircuitBreaker", fallbackMethod = "myTestFallBack")
	public ResponseEntity<List<FlightResponseModel>> getAllFlights() {
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		log.info("started getAllFlights **");
		List<FlightResponseModel> flights = new ArrayList<>();
		List<FlightDto> res = flightProxy.getAllFlights();
		for (FlightDto flightDto : res) {
			flights.add(modelMapper.map(flightDto, FlightResponseModel.class));
		}
		return ResponseEntity.status(HttpStatus.OK).body(flights);

	}

	/**
	 * circuit breaker message if used service is down
	 *
	 */
	public ResponseEntity<?> myTestFallBack(Exception e) {
		log.info("started myTestFallBack **");
		return ResponseEntity.ok("within myTestFallBack method. FLIGHT-WS is down" + e.toString());
	}
}
