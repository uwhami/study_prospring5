package com.apress.prospring5.ch9.repos;


import com.apress.prospring5.ch9.entities.Instrument;
import org.springframework.data.repository.CrudRepository;


public interface InstrumentRepository extends CrudRepository<Instrument, Long> {

}
