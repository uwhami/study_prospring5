package com.apress.prospring5.ch9.service;


import com.apress.prospring5.ch9.entities.Singer;
import com.apress.prospring5.ch9.repos.SingerRepository;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("singerService")
@Transactional
public class SingerServiceImpl implements SingerService {

    @Autowired
    private SingerRepository singerRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Singer> findAll() {
        return Lists.newArrayList(singerRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public Singer findById(Long id){
        return singerRepository.findById(id).get();
    }

    /* Transaction 애너테이션을 적용하지 않아서 클래스에 적용된 @Transaction 애너테이션이 적용된다. read-write xmfoswortusdl tkdydehlsek. */
    @Override
    public Singer save(Singer singer){
        return singerRepository.save(singer);
    }

    @Override
    @Transactional(propagation = Propagation.NEVER)
    public Long countAll(){
        return singerRepository.countAllSingers();
    }
}
