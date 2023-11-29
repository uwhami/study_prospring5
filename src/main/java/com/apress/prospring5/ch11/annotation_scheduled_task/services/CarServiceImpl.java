package com.apress.prospring5.ch11.annotation_scheduled_task.services;

import com.apress.prospring5.ch11.annotation_scheduled_task.entities.Car;
import com.apress.prospring5.ch11.annotation_scheduled_task.repos.CarRepository;
import org.assertj.core.util.Lists;
import org.joda.time.DateTime;
import org.joda.time.Years;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("carService")
@Repository
@Transactional
public class CarServiceImpl implements CarService {

    public boolean done;
    final Logger logger = LoggerFactory.getLogger(CarServiceImpl.class);

    @Autowired
    CarRepository carRepository;

    @Override
    @Transactional
    public List<Car> findAll() {
        return Lists.newArrayList(carRepository.findAll());
    }

    @Override
    public Car save(Car car) {
        return carRepository.save(car);
    }

    @Override
    @Scheduled(fixedDelay = 10000)
    public void updateCarAgeJob() {
        List<Car> cars = findAll();

        DateTime currentDate = DateTime.now();
        logger.info("========== Starting Car Age Update Job.");

        cars.forEach(car -> {
            int age = Years.yearsBetween(car.getManufactureDate(), currentDate).getYears();

            car.setAge(age);
            save(car);
            logger.info("========== Update Car Age " + car);
        });
        logger.info("========== Completed Update Care Age");
        done = true;
    }

    @Override
    public boolean isDone() {
        return done;
    }

}
