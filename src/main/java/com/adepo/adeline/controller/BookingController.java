package com.adepo.adeline.controller;

import com.adepo.adeline.model.Booking;
import com.adepo.adeline.model.Flight;
import com.adepo.adeline.model.Passenger;
import com.adepo.adeline.repository.BookingRepository;
import com.adepo.adeline.repository.FlightRepository;
import com.adepo.adeline.repository.PassengerRepository;
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
import java.util.List;
import java.util.Random;

@Controller
public class BookingController {

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private PassengerRepository passengerRepository;


    @RequestMapping(value = "/bookings/list", method = RequestMethod.GET)
    public String bookings(Model model){
        model.addAttribute("bookings", bookingRepository.findAll());
        return "booking/list";
    }

    @RequestMapping(value = "/bookings/create/{id}", method = RequestMethod.GET)
    public String create(@PathVariable("id") int id, Model model) {

        model.addAttribute("flight", flightRepository.findById(id).get());
        return "booking/create";
    }

    public String generateUniqueId() {
        Random rand = new Random(); //instance of random class
        //generate random values from 0-100000
        int num = rand.nextInt(100000);
        String uniqueId = ("00" + Integer.toString(num));
        return uniqueId;
    }

    @RequestMapping(value = "/bookings/processBookings", method = RequestMethod.POST)
    public String processBookings(Model model, @RequestParam int id, @RequestParam String lastName, @RequestParam String firstName, @RequestParam int age,@RequestParam String address, @RequestParam String email, @RequestParam String phone){

        Flight flight = flightRepository.findById(id).get();

        Passenger passenger = new Passenger(lastName, firstName, age, address, email, phone);
        passengerRepository.save(passenger);

        long millis = System.currentTimeMillis();
        Date bookingDate = new Date(millis);

        Booking b = null; String bookingNumber = " ";
        do{
            bookingNumber = generateUniqueId();
            b = bookingRepository.findBookingByBookingNumber(bookingNumber);
        }while (b != null);

        Booking booking = new Booking(bookingNumber, flight, bookingDate, passenger);
        bookingRepository.save(booking);

        model.addAttribute("flight", flight);
        model.addAttribute("booking", booking);
        model.addAttribute("passenger", passenger);


        return "redirect:/bookings/list";
    }

    @RequestMapping(value = "/bookings/edit/{id}", method = RequestMethod.GET)
    public String showUpdateForm(@PathVariable("id") int id, Model model) {

        model.addAttribute("booking", bookingRepository.findById(id).get());
        return "booking/edit";
    }

    @RequestMapping(value = "/bookings/update", method = RequestMethod.POST)
    public String updateBooking(Model model, @RequestParam int id, @RequestParam int seatNumber) throws ParseException {

        Booking booking = bookingRepository.findById(id).get();

        booking.setSeatNumber(seatNumber);

        bookingRepository.save(booking);

        return "redirect:/bookings/list";
    }

    @RequestMapping(value = "/bookings/delete/{id}", method = RequestMethod.GET)
    public String remove(@PathVariable("id") int id, Model model) {

        Booking booking = bookingRepository.findById(id).get();

        bookingRepository.delete(booking);
        return "redirect:/bookings/list";
    }
}
