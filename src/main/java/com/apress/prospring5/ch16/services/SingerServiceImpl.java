package com.apress.prospring5.ch16.services;

import com.apress.prospring5.ch16.dao.SingerRepository;
import com.apress.prospring5.ch16.entities.Singer;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service("singerService")
public class SingerServiceImpl implements SingerService{

    @Autowired
    private SingerRepository singerRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Singer> findAll() {
        return Lists.newArrayList(singerRepository.findAll());
    }

    @Override
    public Singer findById(Long id) {
        return singerRepository.findById(id).get();
    }

    @Override
    public Singer save(Singer singer) {
        return singerRepository.save(singer);
    }

    @Override
    public Page<Singer> findAllByPage(Pageable pageable) {
        return singerRepository.findAll(pageable);
    }
}
