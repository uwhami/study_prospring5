package com.apress.prospring5.ch3.sec5.sub5.annotated;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/*
 * 3-100~102. @AliasFor에 또다른 애너테이션을 사용해서 애너테이션 애트리뷰트에 대한 별칭을 만드는데는 한계가 있음.
 * 스테레오타입 에너테이션(@Component와 Component 애너테이션을 더 특화시킨애너테이션)에 사용할 수 없다.
 */
@Component("johnMayer")
//@Award(prize={"grammy","platinum dist"})
//@Award(value={"grammy", "platinum disk"})
//@Award({"grammy", "platinum disk"})
@Trophy(name={"grammy","platinum dist"})
public class Singer {

    public String lyric = "We found a message in a bottle we were drinking";

    public void setLyric(@Value("For all my running, I can understand") String lyric) {
        this.lyric = lyric;
    }

    public void sing(){
        System.out.print(lyric);
    }
}
