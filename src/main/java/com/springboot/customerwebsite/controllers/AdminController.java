package com.springboot.customerwebsite.controllers;

import com.springboot.customerwebsite.models.Customer;
import com.springboot.customerwebsite.models.securitymodels.UserPrincipal;
import com.springboot.customerwebsite.services.CustomerService;
import com.springboot.customerwebsite.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class AdminController {

    @Autowired
    private final CustomerService customerService;

    @Autowired
    private final UserService userService;


    @GetMapping("/admin-dashboard")
    public String viewCustomerList(Model model, Principal principal) {
//        userService.loadUserByUsername(principal.getName());
        // Here you call the service to retrieve all the customers
        final List<Customer> customerList = customerService.getAllCustomers();
        // Once the customers are retrieved, you can store them in model and return it to the view
        model.addAttribute("customerList", customerList);
        return "admin-dashboard";
    }

//
//    @GetMapping("/dashboard")
//    public String viewDashboard(Model model, Authentication auth) throws NoSuchUserException {
//        UserPrincipal user = UserFactory.createUser(auth);
//        user = userService.getUser(user.getId());
//        model.addAttribute("user", user);
//        switch (user.getRole().getRole()) {
//            case ROLE_USER:
//                return "user-dashboard";
//            case ROLE_ADMIN:
//                return "admin-dashboard";
//        }
    }