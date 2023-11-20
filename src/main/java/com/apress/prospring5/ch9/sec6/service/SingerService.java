package com.apress.prospring5.ch9.sec6.service;

import com.apress.prospring5.ch9.sec6.entities.Singer;

import java.util.List;

public interface SingerService {

    public List<Singer> findAll();
    public Singer findById(Long id);
    public Singer save(Singer singer);
    public long countAll();

}
