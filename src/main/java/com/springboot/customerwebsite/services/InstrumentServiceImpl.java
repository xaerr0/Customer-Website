package com.springboot.customerwebsite.services;

import com.springboot.customerwebsite.models.Instrument;
import com.springboot.customerwebsite.repositories.InstrumentRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    public Instrument saveInstrument(Instrument instrument) {
        return instrumentRepo.save(instrument);
    }

    @Override
    public Instrument getInstrument(Long id) {
        return instrumentRepo.findById(id)
                .orElse(null);
    }

    @Override
    public void deleteInstrument(Long id) {
        instrumentRepo.deleteById(id);

    }

    @Override
    public List<Instrument> saveAllInstruments(List<Instrument> instrumentList) {
        return instrumentRepo.saveAll(instrumentList);
    }
}