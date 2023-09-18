package com.apress.prospring5.ch6.dao;

import com.apress.prospring5.ch6.entity.Singer;

import java.util.List;

/**
 * 예제 6-6. SingerDao 인터페이스.
 *
 * SINGER 테이블의 모든 데이터 액세스 서비스를 캡슐화 하는 인터페이스.
 * 여기에 있는 메서드는 CRUD라는 용어에 해당하는 역할을 담당하는 메서드이다.
 */
public interface SingerDao {
    List<Singer> findAll();
    List<Singer> findByFirstName(String firstName);
    String findNameById(Long id);
    String findLastNameById(Long id);
    String findFirstNameById(Long id);
    void insert(Singer singer);
    void update(Singer singer);
    void delete(Long singerId);
    List<Singer> findAllWithAlbums();
    void insertWithAlbum(Singer singer);
}
