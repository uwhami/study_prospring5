package com.apress.prospring5.ch8.sec6.services;


import com.apress.prospring5.ch8.sec6.entities.Singer;

import java.util.List;

public interface SingerService {
    List<Singer> findAll();
    List<Singer> findByFirstName(String firstName);
    List<Singer> findByFirstNameAndLastName(String firstName, String lastName);
}
