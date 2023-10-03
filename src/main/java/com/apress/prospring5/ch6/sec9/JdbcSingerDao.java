package com.apress.prospring5.ch6.sec9;

import com.apress.prospring5.ch6.dao.SingerDao;
import com.apress.prospring5.ch6.entities.Album;
import com.apress.prospring5.ch6.entities.Singer;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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

    /**
     * 6.10 ResultSetExtractor를 사용해 중첩 도메인 객체 조회하기.
     *
     * 예제 6-40. RowMapper<T>는 단일 도메인 객체에만 로우를 매핑할 수 있다.
     * 좀더 복잡한 객체 구조에서는 ResultSetExtractor 인터페이스를 사용해야 한다.
     */
    @Override
    public List<Singer> findAllWithAlbums() {
        String sql = "select s.id, s.first_name, s.last_name, s.birth_date, a.id as album_id, a.title, a.release_date " +
                "from singer s left join album a on s.id = a.singer_id";
        return namedParameterJdbcTemplate.query(sql, new SingerWithDetailExtractor());
    }

    private static final class SingerWithDetailExtractor implements ResultSetExtractor<List<Singer>> {
        @Override
        public List<Singer> extractData(ResultSet rs) throws SQLException, DataAccessException {
            Map<Long, Singer> map = new HashMap<>();
            Singer singer;
            while(rs.next()){
                Long id = rs.getLong("id");
                singer = map.get(id);
                if(singer == null){
                    singer = new Singer();
                    singer.setId(id);
                    singer.setFirstName(rs.getString("first_name"));
                    singer.setLastName(rs.getString("last_name"));
                    singer.setBirthDate(rs.getDate("birth_date"));
                    singer.setAlbums(new ArrayList<>());
                    map.put(id, singer);
                }
                Long albumId = rs.getLong("album_id");
                if(albumId > 0){
                    Album album = new Album();
                    album.setId(albumId);
                    album.setSingerId(id);
                    album.setTitle(rs.getString("title"));
                    album.setReleaseDate(rs.getDate("release_date"));
                    singer.addAlbum(album);
                }
            }
            return new ArrayList<>(map.values());
        }

    }

    @Override
    public void insertWithAlbum(Singer singer) {

    }
}
