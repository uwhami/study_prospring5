package com.apress.prospring5.ch7.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;

import static jakarta.persistence.GenerationType.IDENTITY;

/**
 * 7.3.1 단순 매핑.
 */
@Entity
@Table(name="album")
public class Album implements Serializable {

    private Long albumId;
    private String title;
    private Date releaseDate;
    private int version;

    /** 7.3.2 일대다 매핑. */
    private Singer singer;


    @Id //객체의 기본키 임을 뜻함.
    @GeneratedValue(strategy = IDENTITY) //id값이 등록 도중 벡엔드에서 생성됨을 뜻함.
    @Column(name = "ALBUM_ID")
    public Long getId() {
        return albumId;
    }

    public void setId(Long id) {
        this.albumId = id;
    }

    @Column
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "RELEASE_DATE")
    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    @Version
    @Column(name = "VERSION")
    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }


    /** 7.3.2 일대다 매핑. */
    @ManyToOne
    @JoinColumn(name = "SINGER_ID")
    public Singer getSinger() {
        return singer;
    }

    public void setSinger(Singer singer) {
        this.singer = singer;
    }

    @Override
    public String toString() {
        return "==========Album{" +
                "id=" + albumId +
                ", title='" + title + '\'' +
                ", releaseDate=" + releaseDate +
                ", version=" + version +
                '}';
    }
}
