package com.springboot.customerwebsite.services;

import com.springboot.customerwebsite.models.Instrument;

import java.util.List;

public interface InstrumentService {
    List<Instrument> getAllInstruments();

    Instrument saveInstrument(Instrument instrument);

    Instrument getInstrument(Long id);

    void deleteInstrument(Long id);

    List<Instrument> saveAllInstruments(List<Instrument> instrumentList);
}