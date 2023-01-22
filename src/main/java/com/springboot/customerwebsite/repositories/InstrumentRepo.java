package com.springboot.customerwebsite.repositories;

import com.springboot.customerwebsite.models.Instrument;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstrumentRepo extends JpaRepository<Instrument, Long> {
}