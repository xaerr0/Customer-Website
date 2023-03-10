package com.springboot.customerwebsite.controllers;

import com.springboot.customerwebsite.models.Customer;
import com.springboot.customerwebsite.models.Instrument;
import com.springboot.customerwebsite.models.securitymodels.UserPrincipal;
import com.springboot.customerwebsite.services.CustomerService;
import com.springboot.customerwebsite.services.InstrumentService;
import com.springboot.customerwebsite.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    private final CustomerService customerService;

    @Autowired
    private final InstrumentService instrumentService;

    @Autowired
    PasswordEncoder encoder;

    @GetMapping("/register")
    public String registerPage(Model model) {
        UserPrincipal userPrincipal = new UserPrincipal();
        userPrincipal.setCustomer(new Customer());
        model.addAttribute("user", userPrincipal);
        return "/register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") UserPrincipal user) throws Exception {
        customerService.saveCustomer(user.getCustomer());
        userService.saveUser(user);
        return "redirect:/edit-profile";
    }

    @GetMapping("/welcome")
    public String successfulLoginPage(Model model, Principal principal) {
        UserPrincipal user = userService.loadUserByUsername(principal.getName());
//        model.addAttribute("principal",principal);
        model.addAttribute(user);
        return "/";
    }

    @GetMapping("/login")
    public String login() {
        return "/login";
    }

    @GetMapping("/user-dashboard")
    public String userDashboard(Model model, @AuthenticationPrincipal UserPrincipal user) {
        Customer customer = customerService.getCustomer(user.getCustomer().getId());
        Instrument instrument = customer.getInstrument();
        model.addAttribute("customer", customer);
        model.addAttribute("instrument", instrument);
        return "user-dashboard";
    }
}