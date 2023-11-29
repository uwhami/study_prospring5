package com.apress.prospring5.ch11.annotation_scheduled_task.repos;

import com.apress.prospring5.ch11.annotation_scheduled_task.entities.Car;
import org.springframework.data.repository.CrudRepository;

public interface CarRepository extends CrudRepository<Car, Long> {

}
