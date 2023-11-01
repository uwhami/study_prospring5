package com.apress.prospring5.ch7.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "instrument")
public class Instrument implements Serializable {

    private String instrumentId;

    /** 7.3.3 다대다 매핑 */
    private Set<Singer> singers = new HashSet<>();


    @Id
    @Column(name = "INSTRUMENT_ID")
    public String getInstrumentId() {
        return instrumentId;
    }

    public void setInstrumentId(String instrumentId) {
        this.instrumentId = instrumentId;
    }


    /** 7.3.3 다대다 매핑 */
    @ManyToMany
    @JoinTable(name = "singer_instrument",
               joinColumns = @JoinColumn(name="INSTRUMENT_ID"),
               inverseJoinColumns = @JoinColumn(name="SINGER_ID"))
    public Set<Singer> getSingers() {
        return singers;
    }

    public void setSingers(Set<Singer> singers) {
        this.singers = singers;
    }

    public void setSinger(Singer singer){
        singers.add(singer);
    }



    @Override
    public String toString() {
        return "==========Instrument{" +
                "instrumentId='" + instrumentId + '\'' +
                '}';
    }
}
