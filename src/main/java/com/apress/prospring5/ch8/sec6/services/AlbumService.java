package com.apress.prospring5.ch8.sec6.services;

import com.apress.prospring5.ch8.sec6.entities.Album;
import com.apress.prospring5.ch8.sec6.entities.Singer;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AlbumService {
    List<Album> findBySinger(Singer singer);
    List<Album> findByTitle(String title);
}
