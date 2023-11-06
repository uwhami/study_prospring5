package com.apress.prospring5.ch8.service;

import com.apress.prospring5.ch8.entities.Singer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 8.1.3 ORM 매핑에 JPA 애너테이션 사용하기.
 */
@Service("jpaSingerServiceEmpty")
@Repository /* 클래스에 데이터 액세스 로직이 들어 있음을 나타내며 데이터베이스 벤더가 제공하는 예외를 스프링 자체 DataAccessException 계층으로 변환한다.*/
@Transactional
public class SingerServiceImpl implements SingerService {

    final static String ALL_SINGER_NATIVE_QUERY = "select id, first_name, last_name, birth_date, version from singer";

    private static Logger logger = LoggerFactory.getLogger(SingerServiceImpl.class);

    @PersistenceContext
    private EntityManager em;

    @Transactional(readOnly = true)
    @Override
    public List<Singer> findAll() {
        return findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public List<Singer> findByFirstName(String firstName) {
        return findByFirstName(firstName);
    }

    @Transactional(readOnly = true)
    @Override
    public Singer findById(Long id) {
        return null;
    }

    @Override
    public Singer save(Singer singer) {
        return null;
    }

    @Override
    public void delete(Singer singer) {

    }

    @Transactional(readOnly = true)
    @Override
    public List<Singer> findAllByNativeQuery() {
        return null;
    }
}
