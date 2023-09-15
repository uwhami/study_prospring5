package com.apress.prospring5.ch5.sec11;

import com.apress.prospring5.ch2.common.Guitar;
import com.apress.prospring5.ch2.common.Singer;
import org.springframework.stereotype.Component;

@Component("johnMayer")
public class GrammyGuitarist implements Singer {

    @Override
    public void sing() {
        System.out.println("==========sing: Gravity is working against me and gravity wants to bring me down.");
    }

    public void sing(Guitar guitar){
        System.out.println("==========play: Studying is fun, but I want to go home and rest. I am so sleepy");
    }

    public void rest(){
        System.out.println("==========zzz");
    }

    public void talk(){
        System.out.println("==========talk : I like this Prospring5 book except it's too thick.");
    }

}
