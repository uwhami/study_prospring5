package com.apress.prospring5.ch9.sec6.service;

import com.apress.prospring5.ch9.sec6.entities.Singer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceException;
import org.springframework.orm.jpa.JpaSystemException;

import java.util.List;

public class SingerServiceImpl implements SingerService{

    @PersistenceContext(unitName = "emfA")
    private EntityManager emA;
    @PersistenceContext(unitName = "emfB")
    private EntityManager emB;

    @Override
    public List<Singer> findAll() {
        return null;
    }

    @Override
    public Singer findById(Long id) {
        return null;
    }

    @Override
    public Singer save(Singer singer) {
        Singer singerB = new Singer();
        singerB.setFirstName(singer.getFirstName());
        singerB.setLastName(singer.getLastName());
        singerB.setBirthDate(singer.getBirthDate());
        if (singer.getId() == null) {
            emA.persist(singer);
            if(true) {
                throw new JpaSystemException(new PersistenceException("==========Simulation of wrong situation 잘못된 상황의 시뮬레이션"));
            }
            emB.persist(singerB);
        } else {
            emA.merge(singer);
            emB.merge(singer);
        }
        return singer;
    }

    @Override
    public long countAll() {
        return 0;
    }
}
