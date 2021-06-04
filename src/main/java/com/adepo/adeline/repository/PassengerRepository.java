package com.adepo.adeline.repository;

import com.adepo.adeline.model.Passenger;
import org.springframework.data.repository.CrudRepository;

public interface PassengerRepository  extends CrudRepository<Passenger, Integer> {

    Passenger findPassengerByLastNameAndFirstName(String lastName, String firstName);
}
