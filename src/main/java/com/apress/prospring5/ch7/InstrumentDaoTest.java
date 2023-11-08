package com.apress.prospring5.ch7;

import com.apress.prospring5.ch7.config.AdvancedConfig;
import com.apress.prospring5.ch7.dao.InstrumentDao;
import com.apress.prospring5.ch7.dao.SingerDao;
import com.apress.prospring5.ch7.entities.Instrument;
import com.apress.prospring5.ch7.entities.Singer;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

import static com.apress.prospring5.ch7.SpringHibernateDemo.listSingersWithAlbum;
import static org.junit.Assert.assertNotNull;

public class InstrumentDaoTest {

    private static Logger logger = LoggerFactory.getLogger(SingerDaoTest.class);

    private GenericApplicationContext ctx;
    private SingerDao singerDao;
    private InstrumentDao instrumentDao;

    @Before
    public void setUp(){
        ctx = new AnnotationConfigApplicationContext(AdvancedConfig.class);
        singerDao = ctx.getBean(SingerDao.class);
        instrumentDao = ctx.getBean(InstrumentDao.class);
        assertNotNull(instrumentDao);
    }

    @Test
    public void testSaveInstrument(){
        Singer singer = singerDao.findById(3L);
        Instrument instrument = new Instrument();
        instrument.setInstrumentId("Add Test");
        singer.addInstrument(instrument);
        instrumentDao.save(instrument);

        listSingersWithAlbum(singerDao.findAllWithAlbum());

    }

}
