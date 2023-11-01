package com.apress.prospring5.ch7.config;

import com.apress.prospring5.ch7.dao.InstrumentDao;
import com.apress.prospring5.ch7.dao.SingerDao;
import com.apress.prospring5.ch7.entities.Album;
import com.apress.prospring5.ch7.entities.Instrument;
import com.apress.prospring5.ch7.entities.Singer;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 7.8 엔터티로 테이블을 생성하도록 하이버네이트 구성하기 위한 커스텀 데이터 등록 빈.
 *
 * DBInitializer 빈이 데이터베이스 내용을 초기화 하는 서비스를 제공하는 빈임을 나타내려고 @Service 애너테이션을 추가했다.
 */
@Service
public class DBInitializer {

    private Logger logger = LoggerFactory.getLogger(DBInitializer.class);

    @Autowired SingerDao singerDao;
    @Autowired InstrumentDao instrumentDao;

    /**
     * 단순한 빈인 DBInitializer는 저장소(DAO 빈)를 의존성으로 주입 받으며
     * @PostConstruct 애너테이션이 적용된 초기화 메서드를 갖는다.
     * 이 초기화 메서드는 객체를 생성한 뒤 해당 객체를 데이터베이스에 저장하고,
     * DBInitializer 빈이 데이터베이스 내용을 초기화 하는 서비스를 제공하는 빈임을 나타내려고 @Service 애너테이션을 추가했다.
     */
    @PostConstruct
    public void initDB(){
        logger.info("Starting database initialization...");

        Instrument guitar = new Instrument();
        guitar.setInstrumentId("Guitar");
        instrumentDao.save(guitar);

        Instrument piano = new Instrument();
        piano.setInstrumentId("Piano");
        instrumentDao.save(piano);

        Instrument voice = new Instrument();
        voice.setInstrumentId("Voice");
        instrumentDao.save(voice);

        Singer singer = new Singer();
        singer.setFirstName("John");
        singer.setLastName("Mayer");
        singer.setBirthDate(new Date((new GregorianCalendar(1977, 9, 16)).getTime().getTime()));
        singer.addInstrument(guitar);
        singer.addInstrument(piano);

        Album album1 = new Album();
        album1.setTitle("The Search For Everything");
        album1.setReleaseDate(new java.sql.Date((new GregorianCalendar(2017, 0, 20)).getTime().getTime()));
        singer.addAlbum(album1);

        Album album2 = new Album();
        album2.setTitle("Battle Studies");
        album2.setReleaseDate(new java.sql.Date((new GregorianCalendar(2009, 10, 17)).getTime().getTime()));
        singer.addAlbum(album2);

        singerDao.save(singer);

        singer = new Singer();
        singer.setFirstName("Eric");
        singer.setLastName("Clapton");
        singer.setBirthDate(new Date((new GregorianCalendar(1945, 2, 30)).getTime().getTime()));
        singer.addInstrument(guitar);

        Album album = new Album();
        album.setTitle("From The Cradle");
        album.setReleaseDate(new java.sql.Date((new GregorianCalendar(1994, 8, 13)).getTime().getTime()));
        singer.addAlbum(album);

        singerDao.save(singer);

        singer = new Singer();
        singer.setFirstName("John");
        singer.setLastName("Butler");
        singer.setBirthDate(new Date((new GregorianCalendar(1975, 3, 1)).getTime().getTime()));
        singer.addInstrument(guitar);

        singerDao.save(singer);
        logger.info("Database initialization finished.");
    }


}
