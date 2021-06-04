package com.adepo.adeline.controller;

import com.adepo.adeline.model.Aircraft;
import com.adepo.adeline.model.Flight;
import com.adepo.adeline.repository.AircraftRepository;
import com.adepo.adeline.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@Controller
public class FlightController {

    @Autowired
    private FlightRepository flightRepository;
    @Autowired
    private AircraftRepository aircraftRepository;

    @RequestMapping(value = "/flights/list", method = RequestMethod.GET)
    public String flights(Model model){
        model.addAttribute("flights", flightRepository.findAll());
        return "flight/list";
    }

    @RequestMapping(value = "/flights/availableFlight", method = RequestMethod.GET)
    public String availableFlight(Model model, @RequestParam String takeOffPoint, @RequestParam String destination){
        model.addAttribute("availableFlight", flightRepository.findByTakeOffPointAndDestinationAndAvailableSeatsGreaterThan(takeOffPoint, destination, 0));
        return "flight/availableFlight";

    }

    @RequestMapping(value = "/flights/create/{id}", method = RequestMethod.GET)
    public String create(@PathVariable("id") int id,  Model model) {

        model.addAttribute("aircraft", aircraftRepository.findById(id).get());
        return "flight/create";

    }

    @RequestMapping(value = "/flights/add", method = RequestMethod.POST)
    public String add(Model model, @RequestParam int id, @RequestParam String flightNumber, @RequestParam String takeOffTime, @RequestParam String arrivalTime, @RequestParam String takeOffPoint, @RequestParam String destination, @RequestParam int availableSeat) throws ParseException {

        Aircraft aircraft = aircraftRepository.findById(id).get();

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm");
        java.util.Date utilDate = formatter.parse(takeOffTime);
        Date takeOff_Time = new Date(utilDate.getTime());

        java.util.Date utilDate2 = formatter.parse(arrivalTime);
        Date arrival_Time = new Date(utilDate2.getTime());

        Flight flight = new Flight(flightNumber, aircraft, takeOff_Time, arrival_Time, takeOffPoint, destination, availableSeat);
        flightRepository.save(flight);
        return "redirect:/flights/list";
    }

    @RequestMapping(value = "/flights/edit/{id}", method = RequestMethod.GET)
    public String showUpdateForm(@PathVariable("id") int id, Model model) {

        model.addAttribute("flight", flightRepository.findById(id).get());
        return "flight/edit";
    }

    @RequestMapping(value = "/flights/update", method = RequestMethod.POST)
    public String updateFlight(Model model, @RequestParam int id, @RequestParam String flightNumber, @RequestParam String takeOffTime, @RequestParam String arrivalTime, @RequestParam String takeOffPoint, @RequestParam String destination, @RequestParam int availableSeat) throws ParseException {

        Flight flight= flightRepository.findById(id).get();

        flight.setFlightNumber(flightNumber);

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm");
        java.util.Date utilDate = formatter.parse(takeOffTime);
        Date takeOff_Time = new Date(utilDate.getTime());
        flight.setTakeOffTime(takeOff_Time);

        java.util.Date utilDate2 = formatter.parse(arrivalTime);
        Date arrival_Time = new Date(utilDate2.getTime());
        flight.setArrivalTime(arrival_Time);

        flight.setTakeOffPoint(takeOffPoint);
        flight.setDestination(destination);
        flight.setAvailableSeats(availableSeat);

        flightRepository.save(flight);

        return "redirect:/flights/list";
    }

    @RequestMapping(value = "/flights/delete/{id}", method = RequestMethod.GET)
    public String remove(@PathVariable("id") int id, Model model) {

        Flight flight = flightRepository.findById(id).get();

        flightRepository.delete(flight);
        return "redirect:/flights/list";
    }
}
