package com.apress.prospring5.ch9.service;

import com.apress.prospring5.ch9.entities.Singer;

import java.util.List;

public interface SingerService {
    public List<Singer> findAll();

    Singer findById(Long id);

    Singer save(Singer singer);

    Long countAll();
}
