package com.apress.prospring5.ch6.sec11.dao;

import com.apress.prospring5.ch6.dao.SingerDao;
import com.apress.prospring5.ch6.entities.Album;
import com.apress.prospring5.ch6.entities.Singer;
import com.apress.prospring5.ch6.sec12.SelectAllSingers;
import com.apress.prospring5.ch6.sec12.SelectSingerByFirstName;
import com.apress.prospring5.ch6.sec12.UpdateSinger;
import com.apress.prospring5.ch6.sec13.InsertSinger;
import com.apress.prospring5.ch6.sec14.InsertSingerAlbum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 6.11 JDBC 조작을 모델링하는 스프링 클래스.
 *
 * @Repository 는 데이터베이스를 조작하는 빈에 적용하도록 특수하게 설계된 애너테이션인 @Component 애너테이션이다.
 */
@Repository("singerDao")
public class JdbcSingerDao implements SingerDao {

    private static Logger logger = LoggerFactory.getLogger(JdbcSingerDao.class);
    private DataSource dataSource;

    /** 6.12 MappingSqlQuery<T>를 사용해 데이터 질의하기에서 추가. */
    private SelectAllSingers selectAllSingers;
    private SelectSingerByFirstName selectSingerByFirstName;
    private UpdateSinger updateSinger;

    /** 6.13 데이터 등록 및 생성된 키 조회하기. */
    private InsertSinger insertSinger;

    /** 6.14 BatchSqlUpdate를 사용하는 배치 조작 */
    private InsertSingerAlbum insertSingerAlbum;

    @Resource(name = "dataSource")
    public void setDataSource(DataSource dataSource){
        this.dataSource = dataSource;
        this.selectAllSingers = new SelectAllSingers(dataSource);
        this.selectSingerByFirstName = new SelectSingerByFirstName(dataSource);
        this.updateSinger = new UpdateSinger(dataSource);
        this.insertSinger = new InsertSinger(dataSource);
    }

    @Override
    public List<Singer> findAll() {
        return selectAllSingers.execute();
    }

    @Override
    public List<Singer> findByFirstName(String firstName) {
        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("first_name", firstName);
        return selectSingerByFirstName.executeByNamedParam(paramMap);
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
        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("first_name", singer.getFirstName());
        paramMap.put("last_name", singer.getLastName());
        paramMap.put("birth_date", singer.getBirthDate());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        insertSinger.updateByNamedParam(paramMap, keyHolder);
        singer.setId(keyHolder.getKey().longValue());
        logger.info("==========Register new Singer, id : " + singer.getId());
    }

    @Override
    public void update(Singer singer) {
        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("first_name", singer.getFirstName());
        paramMap.put("last_name", singer.getLastName());
        paramMap.put("birth_date", singer.getBirthDate());
        paramMap.put("id", singer.getId());
        updateSinger.updateByNamedParam(paramMap);
        logger.info("==========Updated Singer Infomation. id : " + singer.getId());
    }

    @Override
    public void delete(Long singerId) {

    }

    @Override
    public List<Singer> findAllWithAlbums() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "select s.id, s.first_name, s.last_name, s.birth_date, a.id as album_id, a.title, a.release_date " +
                "from singer s left join album a on s.id = a.singer_id";

        return jdbcTemplate.query(sql,(rs -> { /** ResultSetExtratcor<List<Singer>> 클래스를 상속한 클래스를 람다식으로 표현. */
            Map<Long,Singer> map = new HashMap<>();
            Singer singer;
            while(rs.next()){
                Long id = rs.getLong("id");
                singer = map.get(id);
                if(singer == null) {
                    singer = new Singer();
                    singer.setId(id);
                    singer.setFirstName(rs.getString("first_name"));
                    singer.setLastName(rs.getString("last_name"));
                    singer.setBirthDate(rs.getDate("birth_date"));
                    singer.setAlbums(new ArrayList<>());
                    map.put(id, singer);
                }
                Long albumId = rs.getLong("album_id");
                if(albumId != null && albumId > 0){
                    Album album = new Album();
                    album.setSingerId(rs.getLong("album_id"));
                    album.setSingerId(id);
                    album.setTitle(rs.getString("title"));
                    album.setReleaseDate(rs.getDate("release_date"));
                    singer.addAlbum(album);
                }
            }
            return new ArrayList<>(map.values());
        }));
    }

    @Override
    public void insertWithAlbum(Singer singer) {
        insertSingerAlbum = new InsertSingerAlbum(dataSource);
        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("first_name", singer.getFirstName());
        paramMap.put("last_name", singer.getLastName());
        paramMap.put("birth_date", singer.getBirthDate());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        insertSinger.updateByNamedParam(paramMap, keyHolder);
        singer.setId(keyHolder.getKey().longValue());
        logger.info("==========New Singer is registered. id : " + singer.getId());
        List<Album> albums = singer.getAlbums();
        if(albums != null){
            for(Album album : albums){
                paramMap = new HashMap<>();
                paramMap.put("singer_id", singer.getId());
                paramMap.put("title", album.getTitle());
                paramMap.put("release_date", album.getReleaseDate());
                insertSingerAlbum.updateByNamedParam(paramMap);
            }
        }
        insertSingerAlbum.flush();  //대기중인 등록 조작을 일괄 처리하는 메서드 호출.
    }
}
