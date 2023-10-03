package com.apress.prospring5.ch6.sec11.entities;

import com.apress.prospring5.ch6.dao.SingerDao;
import com.apress.prospring5.ch6.entities.Singer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.List;

/**
 * 6.11 JDBC 조작을 모델링하는 스프링 클래스.
 *
 * @Repository 는 데이터베이스를 조작하는 빈에 적용하도록 특수하게 설계된 애너테이션인 @Component 애너테이션이다.
 */
@Repository("singerDao")
public class JdbcSingerDao implements SingerDao {

    private static Logger logger = LoggerFactory.getLogger(JdbcSingerDao.class);
    private DataSource dataSource;

    @Resource(name = "dataSource")
    public void setDataSource(DataSource dataSource){
        this.dataSource = dataSource;
    }

    @Override
    public List<Singer> findAll() {
        return null;
    }

    @Override
    public List<Singer> findByFirstName(String firstName) {
        return null;
    }

    @Override
    public String findNameById(Long id) {
        return null;
    }

    @Override
    public String findLastNameById(Long id) {
        return null;
    }

    @Override
    public String findFirstNameById(Long id) {
        return null;
    }

    @Override
    public void insert(Singer singer) {

    }

    @Override
    public void update(Singer singer) {

    }

    @Override
    public void delete(Long singerId) {

    }

    @Override
    public List<Singer> findAllWithAlbums() {
        return null;
    }

    @Override
    public void insertWithAlbum(Singer singer) {

    }
}
