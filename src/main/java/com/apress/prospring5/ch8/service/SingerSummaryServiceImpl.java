package com.apress.prospring5.ch8.service;

import com.apress.prospring5.ch8.view.SingerSummary;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("singerSummaryService")
@Repository
@Transactional
public class SingerSummaryServiceImpl implements SingerSummaryService{

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<SingerSummary> findAll() {
        List<SingerSummary> result = em.createQuery("select new com.apress.prospring5.ch8.view.SingerSummary(" +
                                                    "s.firstName, s.lastName, a.title) from Singer s " +
                                                    "left join s.albums a " +
                "where a.releaseDate = (select max(a2.releaseDate) " +
                "from Album a2 where a2.singer.id = s.id)", SingerSummary.class).getResultList();
        return result;
    }



}
