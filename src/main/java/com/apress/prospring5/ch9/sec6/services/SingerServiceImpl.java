package com.apress.prospring5.ch9.sec6.services;

import com.apress.prospring5.ch9.sec6.entities.Singer;
import com.apress.prospring5.ch9.sec6.ex.AsyncXAResourcesException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceException;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.channels.AsynchronousCloseException;
import java.util.ArrayList;
import java.util.List;

@Service("singerService")
@Repository
@Transactional
public class SingerServiceImpl implements SingerService{

    private static final String FIND_ALL = "select s from Singer s";

    @PersistenceContext(unitName = "emfA")
    private EntityManager emA;
    @PersistenceContext(unitName = "emfB")
    private EntityManager emB;

    @Override
    public List<Singer> findAll() {
        List<Singer> singersFromA = findAllInA();
        List<Singer> singersFromB = findAllInB();
        if(singersFromA.size() != singersFromB.size()){
            throw new AsyncXAResourcesException("========== XA resource들 각각이 동일한 수의 동일한 데이터를 가지고 있지 않음.");
        }
        Singer sA = singersFromA.get(0);
        Singer sB = singersFromB.get(0);
        if(!sA.getFirstName().equals(sB.getFirstName())){
            throw new AsyncXAResourcesException("========== XA resource들 각각이 동일한 수의 동일한 데이터를 가지고 있지 않음.");
        }
        List<Singer> singersFromBoth = new ArrayList<>();
        singersFromBoth.add(sA);
        singersFromBoth.add(sB);
        return singersFromBoth;
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

    public List<Singer> findAllInA(){
        return emA.createQuery(FIND_ALL).getResultList();
    }

    public List<Singer> findAllInB(){
        return emB.createQuery(FIND_ALL).getResultList();
    }
}
