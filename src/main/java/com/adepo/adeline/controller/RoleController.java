package com.adepo.adeline.controller;

import com.adepo.adeline.model.Aircraft;
import com.adepo.adeline.model.Role;
import com.adepo.adeline.repository.AircraftRepository;
import com.adepo.adeline.repository.RoleRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class RoleController {
    @Autowired
    private RoleRepository roleRepository;

    @RequestMapping(value = "/roles/list", method = RequestMethod.GET)
    public String roles(Model model){
        model.addAttribute("roles", roleRepository.findAll());
        return "role/list";
    }

    @RequestMapping(value = "/roles/create", method = RequestMethod.GET)
    public String create(Model model) {
        return "role/create";
    }

    @RequestMapping(value = "/roles/add", method = RequestMethod.POST)
    public String add(Model model,@RequestParam String name) {

        Role role = new Role(name);
        roleRepository.save(role);
        return "redirect:/roles/list";
    }

    @RequestMapping(value = "/roles/edit/{id}", method = RequestMethod.GET)
    public String showUpdateForm(@PathVariable("id") int id, Model model) {

        model.addAttribute("role", roleRepository.findById(id).get());
        return "role/edit";
    }

    @RequestMapping(value = "/roles/update", method = RequestMethod.POST)
    public String updateRole(Model model, @RequestParam int id, @RequestParam String name) {

        Role role= roleRepository.findById(id).get();
        role.setName(name);
        roleRepository.save(role);

        return "redirect:/roles/list";
    }

    @RequestMapping(value = "/roles/delete/{id}", method = RequestMethod.GET)
    public String remove(@PathVariable("id") int id, Model model) {

        Role role = roleRepository.findById(id).get();

        roleRepository.delete(role);
        return "redirect:/roles/list";
    }

//    @RequestMapping(value = "/aircrafts/list", method = RequestMethod.GET)
//    public String update(Model model, @RequestParam String registrationNumber,@RequestParam String name, @RequestParam String type, @RequestParam int capacity){
//        model.addAttribute("aircraft", aircraftRepository.findAircraftByRegistrationNumber(registrationNumber));
//        model.addAttribute("message", "Thank you for flying with us...");
//        return "aircraft/list";
//    }
}
