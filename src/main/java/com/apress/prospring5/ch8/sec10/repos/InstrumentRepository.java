package com.apress.prospring5.ch8.sec10.repos;

import com.apress.prospring5.ch8.sec10.entities.Instrument;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by iuliana.cosmina on 5/12/17.
 */
public interface InstrumentRepository extends CrudRepository<Instrument, Long> {

}
