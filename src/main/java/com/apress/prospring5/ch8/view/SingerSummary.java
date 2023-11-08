package com.apress.prospring5.ch8.view;

import java.io.Serializable;

/**
 * 8.3 생성자 표현식을 사용한 커스텀 결과 타입 쿼리
 * 커스텀 결과를 쿼리할 때는 JPA를 사용해 각 레코드에서 직접 POJO 객체를 생성할 수 있다.
 * 아래 클래스는 가수 요약 정보 쿼리의 실행 결과를 저장할 클래스이다.
 */
public class SingerSummary implements Serializable {

    private String firstName;
    private String lastName;
    private String latestAlbum;

    public SingerSummary(String firstName, String lastName, String lastestAlbum){
        this.firstName = firstName;
        this.lastName = lastName;
        this.latestAlbum = lastestAlbum;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getLatestAlbum() {
        return latestAlbum;
    }

    @Override
    public String toString() {
        return "SingerSummary{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", latestAlbum='" + latestAlbum + '\'' +
                '}';
    }
}
