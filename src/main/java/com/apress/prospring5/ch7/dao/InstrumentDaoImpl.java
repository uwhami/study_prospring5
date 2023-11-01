package com.apress.prospring5.ch7.dao;

import com.apress.prospring5.ch7.entities.Instrument;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InstrumentDaoImpl implements InstrumentDao{

    private final static Logger logger = LoggerFactory.getLogger(InstrumentDaoImpl.class);
    private SessionFactory sessionFactory;

    @Override
    public Instrument save(Instrument instrument) {
        logger.info("==========instrument save");
       return sessionFactory.getCurrentSession().merge(instrument);
    }
}

