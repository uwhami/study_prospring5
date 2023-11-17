package com.apress.prospring5.ch9.sec4.repos;


import com.apress.prospring5.ch9.sec4.entities.Instrument;
import org.springframework.data.repository.CrudRepository;


public interface InstrumentRepository extends CrudRepository<Instrument, Long> {

}
