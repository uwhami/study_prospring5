package com.apress.prospring5.ch7.dao;


import com.apress.prospring5.ch7.entities.Singer;
import org.hibernate.HibernateException;

import java.util.List;

public interface SingerDao {

    List<Singer> findAll() throws HibernateException;
    List<Singer> findAllWithAlbum();
    Singer findById(Long id);
    Singer save(Singer  singer);
    void delete(Singer singer);

}
