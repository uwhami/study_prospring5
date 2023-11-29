package com.apress.prospring5.ch11.xml_scheduled_task.services;

import com.apress.prospring5.ch11.xml_scheduled_task.entities.Car;

import java.util.List;

public interface CarService {
    List<Car> findAll();
    Car save(Car car);
    void updateCarAgeJob();
    boolean isDone();
}
