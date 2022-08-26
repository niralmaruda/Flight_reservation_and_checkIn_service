package com.niral.flightreservation.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.niral.flightreservation.controllers.ReservationController;
import com.niral.flightreservation.dto.ReservationRequest;
import com.niral.flightreservation.entities.Flight;
import com.niral.flightreservation.entities.Passenger;
import com.niral.flightreservation.entities.Reservation;
import com.niral.flightreservation.repos.FlightRepository;
import com.niral.flightreservation.repos.PassengerRepository;
import com.niral.flightreservation.repos.ReservationRepository;
import com.niral.flightreservation.util.PDFGenerator;


@Service
public class ReservationServiceImpl implements ReservationService {

	@Value("${com.niral.flightreservation.itinerary.dirpath}")
	private String ITINERARY_DIR;


	@Autowired
	private FlightRepository flightRepository;
	
	
	@Autowired
	private PassengerRepository passengerRepository;
	
	@Autowired
	private ReservationRepository reservationRepository;
	
	@Autowired
	private PDFGenerator pdfGenerator;
	
	private static Logger LOGGER = LoggerFactory.getLogger(ReservationController.class);	
	
	@Override
	@Transactional
	public Reservation bookFlight(ReservationRequest request) {
		LOGGER.info("Inside bookFlight()");
		// Step 1: Make Payment.
		
		// Step 2:
		Long flightId = request.getFlightId();
		LOGGER.info("fetching flight from flightId: "+ flightId);
		Flight flight = flightRepository.findById(flightId).get();
		
		Passenger passenger = new Passenger();
		passenger.setFirstName(request.getPassengerFirstName());
		passenger.setLastName(request.getPassengerLastName());
		passenger.setEmail(request.getPassengerEmail());
		passenger.setPhone(request.getPassengerPhone());
		LOGGER.info("saving the passenger"+ passenger);
		Passenger savedPassenger = passengerRepository.save(passenger);
		
		Reservation reservation = new Reservation();
		reservation.setFlight(flight);
		reservation.setPassenger(savedPassenger);
		reservation.setCheckedIn(false);
		LOGGER.info("saving the reservation"+ reservation);
		Reservation savedReservation = reservationRepository.save(reservation);
		LOGGER.info("generating itinerary.");
		pdfGenerator.generateItinerary(savedReservation, ITINERARY_DIR+savedReservation.getId()+".pdf");
		return savedReservation;
	}

}
