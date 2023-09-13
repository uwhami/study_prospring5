package com.apress.prospring5.ch5.sec10;

/**
 * ch5.sec5 에 있는 GrammyGuitarist 로 하면 Singer 를 상속받아서 에러난다.
 */
public class GrammyGuitarist {

    public void sing() {
        System.out.println("==========sing: Gravity is working against me ㅁnd gravity wants to bring me down");
    }

    public void sing(Guitar guitar) {
        System.out.println("==========play: " + guitar.play());
    }

    public void talk(){
        System.out.println("==========talk");
    }

}
