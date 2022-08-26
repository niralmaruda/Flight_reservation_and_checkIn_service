package com.niral.flightreservation.service;

import com.niral.flightreservation.dto.ReservationRequest;
import com.niral.flightreservation.entities.Reservation;

public interface ReservationService {
	public Reservation bookFlight(ReservationRequest request);
}
