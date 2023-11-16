package com.apress.prospring5.ch8.sec6.services;

import com.apress.prospring5.ch8.sec6.entities.Singer;
import com.apress.prospring5.ch8.sec6.repos.SingerRepository;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("springJpaSingerService")
@Transactional
public class SingerServiceImpl implements SingerService {

    @Autowired
    private SingerRepository singerRepository;

    /* findAll 은 crudRepository 인터페이스에 이미 정의되어 있다. */
    @Override
    @Transactional(readOnly = true)
    public List<Singer> findAll() {
        return Lists.newArrayList(singerRepository.findAll());
    }

    /* findByFirstName 은 스프링  데이터 JPA 가 제공하는 공통 명명 규칙을 사용하여, 네임드 쿼리를 제공할 필요가 없다.
    * 대신 스프링 데이터 JPA 가 '유추'해 메서드 이름을 기반으로 쿼리를 생성한다. */
    @Override
    @Transactional(readOnly = true)
    public List<Singer> findByFirstName(String firstName) {
        return singerRepository.findByFirstName(firstName);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Singer> findByFirstNameAndLastName(String firstName, String lastName) {
        return singerRepository.findByFirstNameAndLastName(firstName, lastName);
    }
}
