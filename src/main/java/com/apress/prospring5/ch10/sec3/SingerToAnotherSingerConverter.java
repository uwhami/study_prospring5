package com.apress.prospring5.ch10.sec3;

import org.springframework.core.convert.converter.Converter;

public class SingerToAnotherSingerConverter implements Converter<Singer,AnotherSinger> {

    @Override
    public AnotherSinger convert(Singer singer){
        AnotherSinger anotherSinger = new AnotherSinger();
        anotherSinger.setFirstName(singer.getFirstName());
        anotherSinger.setLastName(singer.getLastName());
        anotherSinger.setPersonalSite(singer.getPersonalSite());
        anotherSinger.setBirthDate(singer.getBirthDate());

        return anotherSinger;
    }

}
