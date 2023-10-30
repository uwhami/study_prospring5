package com.apress.prospring5.ch7.dao;


import com.apress.prospring5.ch7.entities.Singer;
import jakarta.annotation.Resource;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

//import javax.annotation.Resource;
import java.util.List;

/** 7.4 하이버네이트 Session 인터페이스
 * @Repository : 스프링 빈으로 선언.
 */
@Transactional
@Repository("singerDao")
public class SingerDaoImpl implements SingerDao {

    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory(){
        return sessionFactory;
    }

    @Resource(name = "sessionFactory")
    public void setSessionFactory(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }


    /**
     * 7.4.2 지연로딩을 하는 간단한 쿼리
     *
     * 이상태로 실행시키면 could not initialize proxy - no Session 에러가 발생한다.
     * 이는 하이버네이트가 기본으로 연관 관계를 지연 로딩(Lazy Loading)하며,
     * 레코드에 연관된 테이블을 조인하지 않기 때문이다.
     * 지연로딩을 하는 근본적인 이유는 성능때문이다.
     * 어떤 쿼리가 수많은 레코드를 조회하면서 모든 연관 관계 레코드를 함꼐 조회한다면 데이터 전송이 발생해 성능이 저하될 것이다.
     */
    @Override
    @Transactional(readOnly = true)
    public List<Singer> findAll() throws HibernateException {
        return sessionFactory.getCurrentSession().createQuery("from Singer s", Singer.class).list();
    }

    /**
     * 7.4.3 연관 관계 데이터를 조회하는 쿼리.
     * NamedQuery 인스턴스 이름을 인자로 전달.
     */
    @Override
    public List<Singer> findAllWithAlbum() {
        return sessionFactory.getCurrentSession().createNamedQuery("Singer.findAllWithAlbum", Singer.class).list();
    }

    @Override
    public Singer findById(Long id) {
        return (Singer) sessionFactory.getCurrentSession().createNamedQuery("Singer.findById", Singer.class)
                .setParameter("id",id).uniqueResult();
    }

    @Override
    public Singer save(Singer singer) {
        return null;
    }

    @Override
    public void delete(Singer singer) {

    }
}
