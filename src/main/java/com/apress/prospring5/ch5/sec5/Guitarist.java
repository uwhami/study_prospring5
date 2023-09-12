package com.apress.prospring5.ch5.sec5;

import com.apress.prospring5.ch2.common.Singer;

public class Guitarist implements Singer {

    @Override
    public void sing() {
        System.out.println("==========Just keep me where the light is");
    }

    public void sing2(){
        System.out.println("==========Oh gravity, stay the hell away from me");
    }

    public void rest(){
        System.out.println("==========zzz");
    }

}
