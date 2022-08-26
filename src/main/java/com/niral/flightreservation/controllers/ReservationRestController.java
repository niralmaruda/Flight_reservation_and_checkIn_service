package com.niral.flightreservation.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.niral.flightreservation.dto.UpdateReservationRequest;
import com.niral.flightreservation.entities.Reservation;
import com.niral.flightreservation.repos.ReservationRepository;

@RestController
public class ReservationRestController {
	
	private static Logger LOGGER = LoggerFactory.getLogger(ReservationRestController.class);	

	
	@Autowired
	private ReservationRepository reservationRepository;
	
	@RequestMapping("/reservations/{id}")
	public Reservation findReservatoin(@PathVariable("id") Long id) {
		LOGGER.info("inside findReservatoin() " + id);
		return reservationRepository.findById(id).get();
	}
	
	@RequestMapping(value = "/reservations", method = RequestMethod.POST)
	public Reservation updateReservation(@RequestBody UpdateReservationRequest request) {
		LOGGER.info("inside updateReservation() " + request);
		Reservation reservation = reservationRepository.findById(request.getId()).get();
		reservation.setNumberOfBags(request.getNumberOfBags());
		reservation.setCheckedIn(request.getCheckedIn());
		LOGGER.info("Saving reservation");
		Reservation updatedRervation = reservationRepository.save(reservation);
		return updatedRervation;
	}
}
