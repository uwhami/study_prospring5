package com.apress.prospring5.ch8;


import com.apress.prospring5.ch8.entities.*;
import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.SetAttribute;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

import java.util.Date;

/**
 * 8.5 JPA2 크라이티리어 API로 크라이티리어 쿼리 사용하기.
 * 쿼리에서 검색 조건을 전달하여 엔터티 클래스의 메타 모델을 기반으로 데이터를 불러온다.
 * 엔터티 클래스의 메타 모델은 엔터티 클래스 이름 뒤에 언더스코어(_)를 붙여 나타낸다.
 *
 * 이런 메타 클래스를 작성하고 관리하는 것은 지루한 일!
 * 여러 툴을 사용하면 대상 엔터티 클래스의 JPA 매핑을 기반으로 자동으로 메타 모델 클래스를 생성할 수 있다.
 * hibernate-jpamodelgen 을 추가!
 */
@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Singer.class)
public abstract class Singer_ {

    public static volatile SingularAttribute<Singer, String> firstName;
    public static volatile SingularAttribute<Singer, String> lastName;
    public static volatile SetAttribute<Singer, Album> albums;
    public static volatile SetAttribute<Singer, Instrument> instruments;
    public static volatile SingularAttribute<Singer, Long> id;
    public static volatile SingularAttribute<Singer, Integer> version;
    public static volatile SingularAttribute<Singer, Date> birthDate;

}
