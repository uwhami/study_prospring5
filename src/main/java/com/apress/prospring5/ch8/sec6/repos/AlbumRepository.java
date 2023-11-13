package com.apress.prospring5.ch8.sec6.repos;

import com.apress.prospring5.ch8.sec6.entities.Album;
import com.apress.prospring5.ch8.sec6.entities.Singer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * 8.8 스프링 데이터 JPA로 커스텀 쿼리 사용하기.
 */
public interface AlbumRepository extends JpaRepository<Album, Long> {
    List<Album> findBySinger(Singer singer);

    @Query("select a from Album a where a.title like %:title%")
    List<Album> findByTitle(@Param("title") String title);
}
