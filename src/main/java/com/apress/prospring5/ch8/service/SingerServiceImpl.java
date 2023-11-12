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
@Service("jpaSingerService")
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
        return em.createNamedQuery(Singer.FIND_ALL).getResultList();
    }

    @Transactional(readOnly = true)
    @Override
    public List<Singer> findAllWithAlbum() {
        return em.createNamedQuery(Singer.FIND_ALL_WITH_ALBUM).getResultList();
    }

    @Transactional(readOnly = true)
    @Override
    public Singer findById(Long id) {
        return em.createNamedQuery(Singer.FIND_SINGER_BY_ID, Singer.class).setParameter("id",id).getSingleResult();
    }

    /**
     * 8.3.1 Register Data.
     *
     * persist 메서드를 호출하면 EntityManager는 엔터티를 데이터베이스에 저장하며,
     * 추가된 엔터티를 현재 퍼시스턴스 컨텍스트의 관리 대상 인스턴스로 등록한다.
     */
    @Override
    public Singer save(Singer singer) {
        if(singer.getId() == null){
            logger.info("==========Insert new Singer");
            em.persist(singer);
        }else{
            em.merge(singer);
            logger.info("==========Update singer info");
        }
        logger.info("==========Save Singer Info (id:" + singer.getId());
        return singer;
    }

    @Override
    public void delete(Singer singer) {
        Singer mergedContact = em.merge(singer);
        em.remove(mergedContact);

        logger.info("==========Deleted Singer(id : " + singer.getId());
    }

    @Transactional(readOnly = true)
    @Override
    public List<Singer> findAllByNativeQuery() {
        return em.createNativeQuery(ALL_SINGER_NATIVE_QUERY, Singer.class).getResultList();
    }
}
