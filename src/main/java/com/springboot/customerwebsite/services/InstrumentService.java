package com.springboot.customerwebsite.services;

import com.springboot.customerwebsite.models.Customer;
import com.springboot.customerwebsite.models.Instrument;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface InstrumentService {
    List<Instrument> getAllInstruments();

    Instrument saveInstrument(Instrument instrument);

    Instrument getInstrument(Long id);

    void deleteInstrument(Long id);

    List<Instrument> saveAllInstruments(List<Instrument> instrumentList);

    void deleteAllInstruments();

//    @Transactional
//    List<Instrument> getAvailableInstruments();

    @Transactional
    List<Instrument> getAvailableInstruments();
}