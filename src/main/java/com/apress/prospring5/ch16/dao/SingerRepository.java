package com.apress.prospring5.ch16.dao;

import com.apress.prospring5.ch16.entities.Singer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

/* PagingAndSortingRepository : 페이징과 정렬 추상화를 사용해 엔터티를 조회하는 메서드를 제공하는 CrudRepository 의 고급 확장기능
*  추가 변경 없이도 쿼리 결과가 이미 정렬된 데이터를 반환하며 인터페이스에는 이 데이터를 표시하기만 하면 된다.
* */
public interface SingerRepository extends PagingAndSortingRepository<Singer, Long>, CrudRepository<Singer, Long> {

}
