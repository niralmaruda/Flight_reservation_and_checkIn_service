package com.niral.flightreservation.controllers;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.niral.flightreservation.entities.Flight;
import com.niral.flightreservation.repos.FlightRepository;

@Controller
public class FlightController {
	
	@Autowired
	private FlightRepository flightRepository;
	
	private static Logger LOGGER = LoggerFactory.getLogger(FlightController.class);
	
	@RequestMapping("/findFlights")
	public String findFlights(@RequestParam("from") String from, 
								@RequestParam("to") String to, 
								@RequestParam("departureDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date departureDate,
								ModelMap modelMap) {
		LOGGER.info("Inside findFlights() from: " +from+ "To: " + to + "Departure date: " + departureDate);
		List<Flight> flights = flightRepository.findFlights(from, to, departureDate);
		modelMap.addAttribute("flights", flights);
		LOGGER.info("Flights found are: " + flights);
		return "displayFlights";
	}
	
	@RequestMapping("admin/showAddFlight")
	public String showAddFlight() {
		return "addFlight";
	}
}
