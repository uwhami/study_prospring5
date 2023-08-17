package com.apress.prospring5.ch3;

/*
 * 예제 3-69. 비싱글턴빈인 Singer 클래스 (메서드 주입, Method-injection)
 */
public class Singer {

    private String lyric = "I played a quick game of chess with the salt and pepper shaker";

    public void sing(){
        //콘솔을 오염시키지 않으려고 주석 처리함
        //System.out.println(lyric);
    }

}
