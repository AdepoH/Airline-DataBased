package com.adepo.adeline.repository;

import com.adepo.adeline.model.Flight;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface FlightRepository extends CrudRepository<Flight, Integer> {

    Flight findFlightByFlightNumber(String flightNumber);

        @Query(value = "SELECT * FROM flight f WHERE f.takeOffPoint = :takeOffPoint and f.destination = :destination and date(f.takeOffTime) = date(:takeOffTime) and f.availableSeats > 0", nativeQuery = true)
        List<Flight> searchAvailableFlights(String takeOffPoint, String destination, Date takeOffTime);

      List<Flight> findByTakeOffPointAndDestinationAndAvailableSeatsGreaterThan(String takeOffPoint, String destination, int i);
}
