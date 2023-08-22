package com.apress.prospring5.ch3.sec5.annotated;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Singer {

    public String lyric = "We found a message in a bottle we were drinking";

    public void setLyric(@Value("For all my running, I can understand") String lyric) {
        this.lyric = lyric;
    }

    public void sing(){
        System.out.print(lyric);
    }
}
