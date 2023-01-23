package com.springboot.customerwebsite.controllers;

import com.springboot.customerwebsite.models.Instrument;
import com.springboot.customerwebsite.services.CustomerService;
import com.springboot.customerwebsite.services.InstrumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class InstrumentController {

    @Autowired
    final InstrumentService instrumentService;

    @Autowired
    final CustomerService customerService;

    @GetMapping("/instruments")
    public String viewInstrumentHomePage(Model model) {
       final List<Instrument> instrumentList = instrumentService.getAllInstruments();
        model.addAttribute("instrumentList", instrumentList);
        return "instruments";
    }


    @GetMapping("/new-instrument")
    public String showNewInstrumentPage(Model model) {
        Instrument instrument = new Instrument();
        model.addAttribute("instrument", instrument);
        return "new-instrument";
    }

    @PostMapping("/instruments/save")
    public String saveInstrument(@ModelAttribute("instrument") Instrument instrument) {
        instrumentService.saveInstrument(instrument);
        return "redirect:/";
    }



}