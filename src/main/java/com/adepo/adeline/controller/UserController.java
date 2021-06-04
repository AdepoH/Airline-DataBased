package com.adepo.adeline.controller;

import com.adepo.adeline.model.Role;
import com.adepo.adeline.model.User;
import com.adepo.adeline.model.viewmodels.RegisterUserModel;
import com.adepo.adeline.repository.RoleRepository;
import com.adepo.adeline.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
public class UserController {
    final
    PasswordEncoder passwordEncoder;
    final UserRepository userRepository;
    final
    RoleRepository roleRepository;
    public UserController(UserRepository userRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    @GetMapping("/users/create")
    public String create(Model model){
        return "user/register";
    }
    @PostMapping(value = "/users/register")
    public String register(Model model, RedirectAttributes redirectAttributes, RegisterUserModel registerUserModel){
        //String regex="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&-+=()])(?=\\\\S+$).{8,20}$";
        //Pattern p = Pattern.compile(regex);
       // Matcher m = p.matcher(registerUserModel.getPassword());
        if(!registerUserModel.getPassword().equals(registerUserModel.getConfirmPassword())){
            redirectAttributes.addAttribute("error","Password does not match ");
        } else if( userRepository.existsByUsername(registerUserModel.getUserName())){
            redirectAttributes.addAttribute("error","User with same id already exist ");
        }
        else if( registerUserModel.getPassword().isBlank()||registerUserModel.getPassword().isEmpty()){
            redirectAttributes.addAttribute("error","Password can not be empty or blank ");
      }

 //       else if( !m.matches()){
//            redirectAttributes.addAttribute("error","Password is not strong enough");
//        }
       else{
            User u = new User();
            u.setUsername(registerUserModel.getUserName());
            u.setPassword(passwordEncoder.encode(registerUserModel.getPassword()));
            Optional<Role> optionalRole= roleRepository.findByName("USER");
            if(optionalRole.isPresent()) {
                Role role =optionalRole.get();
                List<Role> roleList = new ArrayList<>();
                roleList.add(role);
                u.setRoles(roleList);
            }
            userRepository.save(u);
            //redirectAttributes.addAttribute("error","");
            return "redirect:/login";
        }
        return "redirect:/users/create";
    }
    @GetMapping("/admins/adminIndex")
    public String admin(Model model){

        return "/admin/adminIndex";

    }

    /*@GetMapping("/users/logout")
    public String logout(Model model){
        return "/index";
    }
    @PostMapping(value = "/users/register")
    public String remove(@PathVariable("id") int id, Model model) {

        User user = userRepository.get(id).get();

        userRepository.delete(user);
        return "redirect:/index";
    }*/
}
