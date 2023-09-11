package com.apress.prospring5.ch5.sec5;


import com.apress.prospring5.ch2.common.Guitar;
import com.apress.prospring5.ch2.common.Singer;

/**
 * 예제 5-31. sing 메서드가 두개
 *
 * 단순 이름 매칭 포인트컷 사용 예제.
 */
public class GrammyGuitarist implements Singer {

    @Override
    public void sing() {
        System.out.println("==========sing: Grvity is working against me and gravity wants to bring me down");
    }

    public void sing(Guitar guitar){
        System.out.println("==========play: " + guitar.play());
    }

    public void rest(){
        System.out.println("==========zzz");
    }

    public void talk(){
        System.out.println("==========talk");
    }
}
