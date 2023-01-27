package com.springboot.customerwebsite.controllers;

import com.springboot.customerwebsite.models.Customer;
import com.springboot.customerwebsite.models.Instrument;
import com.springboot.customerwebsite.services.CustomerService;
import com.springboot.customerwebsite.services.InstrumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/instruments")
public class InstrumentController {

    @Autowired
    final InstrumentService instrumentService;

    @Autowired
    final CustomerService customerService;

    @GetMapping
    public String viewInstrumentHomePage(Model model) {
        final List<Instrument> instrumentList = instrumentService.getAllInstruments();
        model.addAttribute("instrumentList", instrumentList);
        return "instruments";
    }


    @GetMapping("/new")
    public String showNewInstrumentPage(Model model) {
        Instrument instrument = new Instrument();
        model.addAttribute("instrument", instrument);
        return "new-instrument";
    }

    @PostMapping("/save")
    public String saveInstrument(@ModelAttribute("instrument") Instrument instrument) {
        instrumentService.saveInstrument(instrument);
        return "redirect:/instruments";
    }

    //TODO How to do this without mav?
    @GetMapping("/edit/{id}")
    public ModelAndView showEditInstrumentPage(@PathVariable(name = "id") Long id) {
        ModelAndView mav = new ModelAndView("edit-instrument");
        Instrument instrument = instrumentService.getInstrument(id);
        mav.addObject("instrument", instrument);
        return mav;
    }

    @PostMapping("/update/{id}")
    public String updateInstrument(@PathVariable(name = "id") Long id,
                                   @ModelAttribute("instrument") Instrument instrument, Model model) {
        if (!id.equals(instrument.getId())) {
            model.addAttribute("message",
                    "Cannot update instrument id " + instrument.getId() + " doesn't match id to be " +
                    "updated " + id + ".");
            return "error-page";
        }
        instrumentService.saveInstrument(instrument);
        return "redirect:/instruments";
    }

    @RequestMapping("/delete/{id}")
    public String deleteInstrument(@PathVariable(name = "id") Long id) {
        instrumentService.deleteInstrument(id);
        return "redirect:/instruments";
    }


    @GetMapping("/assign/{id}")
    public String assignInstrument(@PathVariable(name = "id") Long id, Model model) {
        Customer customer = customerService.getCustomer(id);
        List<Instrument> instrumentList = instrumentService.getAvailableInstruments();
        model.addAttribute("customer", customer);
        model.addAttribute("instrumentList", instrumentList);
        return "assign-instrument";
    }

    @PostMapping("/assign")
    public String saveInstrumentAssignment(@RequestParam("customerId") Long customerId,
                                           @RequestParam("instrumentId") Long instrumentId) {
        Instrument instrument = instrumentService.getInstrument(instrumentId);
        Customer customer = customerService.getCustomer(customerId);
        instrument.rentInstrument(customer);
        instrumentService.saveInstrument(instrument);
        return "redirect:/";
    }


    @GetMapping("/remove/{instrumentId}/{customerId}")
    public String removeInstrument(@PathVariable(name = "instrumentId") Long instrumentId, @PathVariable(name = "customerId") Long customerId) {
        Instrument instrument = instrumentService.getInstrument(instrumentId);
        Customer customer = customerService.getCustomer(customerId);
        instrument.returnInstrument(customer);
        instrumentService.saveInstrument(instrument);
        return "redirect:/";
    }


}