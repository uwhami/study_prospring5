package com.apress.prospring5.ch5.sec5;

import com.apress.prospring5.ch2.common.Guitar;
import com.apress.prospring5.ch2.common.Singer;

/**
 * 5.5.9 애너테이션 매칭 포인트컷 생성
 * 예제 5-39. 또다른 Guitarist 클래스 구현체 예제.
 */
public class Guitarist2 implements Singer {

    @Override
    public void sing() {
        System.out.println("==========Dream of ways to throw it all away");
    }

    @AdviceRequired
    public void sing(Guitar guitar){
        System.out.println("==========play: " + guitar.play());
    }

    public void rest(){
        System.out.println("==========zzz");
    }
}
