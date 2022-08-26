package com.niral.flightreservation.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.niral.flightreservation.entities.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

}
