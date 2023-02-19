package com.springboot.customerwebsite.controllers;

import com.springboot.customerwebsite.models.securitymodels.UserPrincipal;
import com.springboot.customerwebsite.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
@RequiredArgsConstructor

public class UserController {

    @Autowired
    private final UserService userService;

    @Autowired
    PasswordEncoder encoder;

    @GetMapping("/register")
    public String registerPage(Model model){
        model.addAttribute("user", new UserPrincipal());
        return "/register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") UserPrincipal user) throws Exception {
        userService.saveUser(user);
        return "/welcome";
    }

    @GetMapping("/welcome")
    public String successfulLoginPage(Model model, Principal principal){

        UserPrincipal user = userService.loadUserByUsername(principal.getName());
//        model.addAttribute("principal",principal);
        model.addAttribute(user);

        return "welcome";
    }


}