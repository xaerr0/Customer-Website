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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class CustomerController {

    @Autowired
    private final CustomerService customerService;

    @Autowired
    private final UserService userService;

    @GetMapping("/")
    public String viewHomePage() {
        return "/index";
    }


    @GetMapping("/new")
    public String showNewCustomerPage(Model model) {
        // Here a new (empty) Customer is created and then sent to the view
        Customer customer = new Customer();
        model.addAttribute("customer", customer);
        return "new-customer";
    }

    @PostMapping(value = "/save")
    // As the Model is received back from the view, @ModelAttribute
    // creates a Customer based on the object you collected from
    // the HTML page above
    public String saveCustomer(@ModelAttribute("customer") Customer customer, Authentication authentication) throws Exception {
        UserPrincipal user = (UserPrincipal) authentication.getPrincipal();
//        customer = customerService.saveCustomer(customer);
        user.setCustomer(customer);
        userService.saveUser(user);

        return "redirect:/admin-dashboard";
    }

    @GetMapping("/edit/{id}")
    // The path variable "id" is used to pull a customer from the database
    public ModelAndView showEditCustomerPage(@PathVariable(name = "id") Long id) {
        // Since the previous methods use Model, this one uses ModelAndView
        // to get some experience using both. Model is more common these days,
        // but ModelAndView accomplishes the same thing and can be useful in
        // certain circumstances. The view name is passed to the constructor.
        ModelAndView mav = new ModelAndView("edit-customer");
        Customer customer = customerService.getCustomer(id);
        mav.addObject("customer", customer);
        return mav;
    }

    @PostMapping("/update/{id}")
    public String updateCustomer(@PathVariable(name = "id") Long id, @ModelAttribute("customer") Customer customer, Model model) {
        if (!id.equals(customer.getId())) {
            model.addAttribute("message",
                    "Cannot update, customer id " + customer.getId()
                    + " doesn't match id to be updated: " + id + ".");
            return "error-page";
        }
        customerService.saveCustomer(customer);
        return "redirect:/admin-dashboard";
    }

    @RequestMapping("/delete/{id}")
    public String deleteCustomer(@PathVariable(name = "id") Long id) {
        customerService.deleteCustomer(id);
        return "redirect:/admin-dashboard";
    }
}