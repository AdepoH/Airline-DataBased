package com.adepo.adeline.controller;

import com.adepo.adeline.model.Passenger;
import com.adepo.adeline.repository.PassengerRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class PassengerController {
    @Autowired
    private PassengerRepository passengerRepository;

    @RequestMapping(value = "/passengers/list", method = RequestMethod.GET)
    public String passengers(Model model) {
        model.addAttribute("passengers", passengerRepository.findAll());
        model.addAttribute("message", "Thank you for flying with us...");
        return "passenger/list";
    }

    @RequestMapping(value = "/passengers/create", method = RequestMethod.GET)
    public String create(Model model) {
        return "passenger/create";
    }

    @RequestMapping(value = "/passengers/add", method = RequestMethod.POST)
    public String add(Model model, @RequestParam String lastName, @RequestParam String firstName,@RequestParam int age, @RequestParam String address, @RequestParam String email, @RequestParam String phone) {

        Passenger passenger = new Passenger(lastName, firstName, age, address, email, phone);
        passengerRepository.save(passenger);
        return "redirect:/passengers/list";
    }

    @RequestMapping(value = "/passengers/edit/{id}", method = RequestMethod.GET)
    public String showUpdateForm(@PathVariable("id") int id, Model model) {

        model.addAttribute("passenger", passengerRepository.findById(id).get());
        return "passenger/edit";
    }

    @RequestMapping(value = "/passengers/update", method = RequestMethod.POST)
    public String updatePassenger(Model model, @RequestParam int id, @RequestParam String lastName, @RequestParam String firstName, @RequestParam int age, @RequestParam String address, @RequestParam String email, @RequestParam String phone) {


        Passenger passenger = passengerRepository.findById(id).get();
        passenger.setLastName(lastName);
        passenger.setFirstName(firstName);
        passenger.setAge(age);
        passenger.setAddress(address);
        passenger.setEmail(email);
        passenger.setPhone(phone);
        passengerRepository.save(passenger);

        return "redirect:/passengers/list";
    }

    @RequestMapping(value = "/passengers/delete/{id}", method = RequestMethod.GET)
    public String remove(@PathVariable("id") int id, Model model) {

        Passenger passenger = passengerRepository.findById(id).get();

        passengerRepository.delete(passenger);
        return "redirect:/passengers/list";
    }
}
