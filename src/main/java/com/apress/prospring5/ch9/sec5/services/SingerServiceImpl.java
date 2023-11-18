package com.apress.prospring5.ch9.sec5.services;

import com.apress.prospring5.ch9.sec5.entities.Singer;
import com.apress.prospring5.ch9.sec5.repos.SingerRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

@Service("singerService")
@Repository
public class SingerServiceImpl implements SingerService{

    @Autowired
    private SingerRepository singerRepository;

    @Autowired
    private TransactionTemplate transactionTemplate;

    @PersistenceContext
    private EntityManager em;

    @Override
    public long contAll(){
        /* 람다식을 사용할 경우 */
        /* return transactionTemplate.execute(
                transactionStatus -> em.createNamedQuery(Singer.COUNT_ALL, Long.class).getSingleResult()
        ); */

        /* 람다식을 사용하지 않는 경우 */
        return transactionTemplate.execute(new TransactionCallback<Long>() {
            @Override
            public Long doInTransaction(TransactionStatus status) {
                return em.createNamedQuery(Singer.COUNT_ALL, Long.class).getSingleResult();
            }
        });

    }

}
