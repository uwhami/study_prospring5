package com.apress.prospring5.ch6.sec9;

import com.apress.prospring5.ch6.dao.SingerDao;
import com.apress.prospring5.ch6.entity.Singer;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JdbcSingerDao implements SingerDao, InitializingBean {

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    /**
     * 6.9.3 NamedParameterJdbcTemplate 으로 네임드 파라미터 사용하기.
     * '?' 위치 지정자 대신 접두어로 콜론(:)이 붙는 네임드 파라미터를 확인할 수 있다.
     */
    @Override
    public String findNameById(Long id){
        String sql = "SELECT first_name || ' ' || last_name FROM SINGER WHERE ID = :singerId";
        Map<String,Object> namedParameters = new HashMap<>();
        namedParameters.put("singerId", id);
        return namedParameterJdbcTemplate.queryForObject(sql, namedParameters, String.class);
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        if(namedParameterJdbcTemplate == null){
            throw new BeanCreationException("==========NamedParameterJdbcTemplate of SingerDao is null");
        }
    }

    /**
     * 6.4.9 RowMapper<T>를 사용해 도메인 객체 조회하기.
     */
    @Override
    public List<Singer> findAll() {
        String sql = "select id, first_name, last_name, birth_date from singer";
        return namedParameterJdbcTemplate.query(sql, new SingerMapper());
//        return namedParameterJdbcTemplate.query(sql, (rs, rowNum) -> {
//            Singer singer = new Singer();
//            singer.setId(rs.getLong("id"));
//            singer.setFirstName(rs.getString("first_name"));
//            singer.setLastName(rs.getString("last_name"));
//            singer.setBirthDate(rs.getDate("birth_date"));
//            return singer;
//        });
    }

    private static final class SingerMapper implements RowMapper<Singer> {
        @Override
        public Singer mapRow(ResultSet rs, int rowNum) throws SQLException {
            Singer singer = new Singer();
            singer.setId(rs.getLong("id"));
            singer.setFirstName(rs.getString("first_name"));
            singer.setLastName(rs.getString("last_name"));
            singer.setBirthDate(rs.getDate("birth_date"));
            return singer;
        }

    }

    /**
     * RowMapper<T>를 사용해 도메인 객체 조회하기 -> 람다식 사용.
     */
    @Override
    public List<Singer> findByFirstName(String firstName) {
        String sql = "select id, first_name, last_name, birth_date from singer where first_name = :first_name";
        Map<String,Object> namedParameters = new HashMap<>();
        namedParameters.put("first_name", firstName);
        return namedParameterJdbcTemplate.query(sql, namedParameters, (rs, rowNum)->{
            Singer singer = new Singer();
            singer.setId(rs.getLong("id"));
            singer.setFirstName(rs.getString("first_name"));
            singer.setLastName(rs.getString("last_name"));
            singer.setBirthDate(rs.getDate("birth_date"));
            return singer;
        });
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
