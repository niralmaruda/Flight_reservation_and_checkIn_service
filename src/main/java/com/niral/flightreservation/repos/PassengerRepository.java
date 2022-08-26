package com.niral.flightreservation.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.niral.flightreservation.entities.Passenger;

public interface PassengerRepository extends JpaRepository<Passenger, Long> {

}
