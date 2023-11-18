package com.apress.prospring5.ch9.sec5.repos;

import com.apress.prospring5.ch9.sec5.entities.Singer;
import org.springframework.data.repository.CrudRepository;

public interface SingerRepository extends CrudRepository<Singer, Long> {


}
