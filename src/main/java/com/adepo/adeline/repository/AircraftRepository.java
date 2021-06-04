package com.adepo.adeline.repository;

import com.adepo.adeline.model.Aircraft;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AircraftRepository extends CrudRepository<Aircraft, Integer> {


    Aircraft findAircraftById(int id);

    Aircraft findAircraftByRegistrationNumber(String registrationNumber);

}
