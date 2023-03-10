package com.springboot.customerwebsite.services;

import com.springboot.customerwebsite.models.Instrument;
import com.springboot.customerwebsite.repositories.InstrumentRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class InstrumentServiceImpl implements InstrumentService {

    @Autowired
    final InstrumentRepo instrumentRepo;


    @Override
    public List<Instrument> getAllInstruments() {
        return instrumentRepo.findAll();
    }

    @Override
    @Transactional
    public Instrument saveInstrument(Instrument instrument) {
        if (instrument.getId() == null) {
            instrument.setRentedOut(0);
        }
        return instrumentRepo.save(instrument);
    }

    @Override
    public Instrument getInstrument(Long id) {
        return instrumentRepo.findById(id)
                .orElse(null);
    }

    @Override
    @Transactional
    public void deleteInstrument(Long id) {
        instrumentRepo.deleteById(id);
    }

    @Override
    @Transactional
    public List<Instrument> saveAllInstruments(List<Instrument> instrumentList) {
        return instrumentRepo.saveAll(instrumentList);
    }

    @Override
    public void deleteAllInstruments() {
        instrumentRepo.deleteAll();
    }

    @Override
    @Transactional
    public List<Instrument> getAvailableInstruments() {
        // manually checking for available instruments
        List<Instrument> allInstruments = getAllInstruments();
        List<Instrument> availableInstruments = new ArrayList<>();

        availableInstruments = allInstruments.stream().filter(i -> i.getOnHand() > 0).collect(Collectors.toList());

//        for (Instrument instrument : allInstruments) {
//            if (instrument.getOnHand() > 0) {
//                availableInstruments.add(instrument);
//            }
//        }
        return availableInstruments;
    }
}