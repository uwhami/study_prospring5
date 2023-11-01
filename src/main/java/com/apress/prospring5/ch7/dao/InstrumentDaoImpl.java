package com.apress.prospring5.ch7.dao;

import com.apress.prospring5.ch7.entities.Instrument;
import jakarta.annotation.Resource;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository("instrumentDao")
public class InstrumentDaoImpl implements InstrumentDao{

    private final static Logger logger = LoggerFactory.getLogger(InstrumentDaoImpl.class);
    private SessionFactory sessionFactory;

    @Resource(name = "sessionFactory")
    public void setSessionFactory(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Instrument save(Instrument instrument) {
        logger.info("==========instrument save");
       return sessionFactory.getCurrentSession().merge(instrument);
    }
}

