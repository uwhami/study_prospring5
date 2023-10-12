package com.apress.prospring5.ch7.entities;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * 7.3 하이버네이트 애너테이션으로 ORM 매핑하기.
 * 7.3.1 단순 매핑
 *
 * @Entity 애너테이션은 해당 엔터티가 매핑된 엔터티 클래스임을 나타냄.
 * @Table  애너테이션은 Singer 엔터티 클래스가 매핑돼야 할 데이터베이스 테이블 이름을 정의함.
 * 각 매핑 애트리뷰트에는 컬럼 이름을 지정한 @Column 애너테이션을 적용한다.
 *  *테이블이름이 클래스타입과 같다면 테이블 이름을 생략, 컬럼 이름이 애트리뷰트 이름과 같다면 컬럼 이름을 생략 가능하다.
 */
@Entity
@Table(name = "singer")
public class Singer implements Serializable {

    private Long id;
    private String firstName;
    private String lastName;
    private Date birthDate;
    private int version;

    /** 7.3.2 일대다 매핑. Singer 엔ㅌ터티와 Album 엔터티의 일대다 관계. */
    private Set<Album> albums = new HashSet<>();

    /** 7.3.3 다대다 매핑. */
    private Set<Instrument> instruments = new HashSet<>();

    @Id //객체의 기본키 임을 뜻함.
    @GeneratedValue //id값이 등록 도중 벡엔드에서 생성됨을 뜻함.
    @Column(name = "ID")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "FIRST_NAME")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "LAST_NAME")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /** Temporal 설정으로 java.util.Date가 java.sql.Date로 매핑 가능하다. */
    @Temporal(TemporalType.DATE)
    @Column(name = "BIRTH_DATE")
    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    @Version //version 애트리뷰트를 제어 수단으로 사용하는 낙관적 잠금 매커티즘을 사용하게 한다.
    @Column(name = "VERSION")
    public int getVersion() {
        return version;
    }
    /** 하이버네이트가 레코드를 수정할 떄마다 엔터티 인스턴스의 version과 데이터베이스 레코드의 version을 비교하여,
     * 반약 버전이 같으면 이전에 아무도 수정하지 않았다는 뜻이므로 하이버네이트가 데이터를 수정하며 version을 증가시킨다.
     * 반면 버전이 같지 않으면 이전에 누군가가 데이터베이스 레코드를 수정했다는 뜻이므로
     * 하이버네이트가 StableObjectStateException 예외를 던지며,
     * 스프링은 이 예외를 HibernateOptimisticLockingFailureException으로 변환한다.
     * 예제에서는 버전관리에 정수값을 사용했으나 타임스탬프도 사용할 수 있다.
     */

    public void setVersion(int version) {
        this.version = version;
    }



    /** 7.3.2 일대다 매핑.
     * @OneToMany : 일대 다 관계를 나타내는 애너테이션.
     * cascade : 수정 작업이 수정할 테이블부터 관련 있는 자식 테이블의 레코드까지 "모두 전이돼야 함(cascade)"을 나타냄.
     * orphanRemoval : Set에 들어있는 앨범이 수정됐을 때 더이상 존재하지 않는 앨범 레코드를 데이터베이스에서 삭제해야 함을 표시.
     */
    @OneToMany(mappedBy = "singer", cascade = CascadeType.ALL, orphanRemoval = true)
    public Set<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(Set<Album> albums) {
        this.albums = albums;
    }


    /** 7.3.3 다대다 매핑.
     *  JoinTable : 하이버네이트가 검색해야 할 조인 테이블을 지정.
     */
    @ManyToMany
    @JoinTable(name="singer_instrument",
               joinColumns = @JoinColumn(name="SINGER_ID"),
               inverseJoinColumns = @JoinColumn(name="INSTRUMENT_ID"))
    public Set<Instrument> getInstruments() {
        return instruments;
    }

    public void setInstruments(Set<Instrument> instruments) {
        this.instruments = instruments;
    }


    @Override
    public String toString() {
        return "==========Singer{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDate=" + birthDate +
                ", version=" + version +
                '}';
    }
}
