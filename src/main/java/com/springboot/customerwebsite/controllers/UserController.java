package com.springboot.customerwebsite.controllers;

import com.springboot.customerwebsite.models.User;
import com.springboot.customerwebsite.repositories.UserRepo;
import com.springboot.customerwebsite.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor

public class UserController {

    @Autowired
    private final UserService userService;

    @Autowired
    PasswordEncoder encoder;

    @GetMapping("/register")
    public String registerPage(Model model){
        model.addAttribute("user", new User());
        return "/register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") User user) throws Exception {
        userService.saveUser(user);
        return "register";
    }

}