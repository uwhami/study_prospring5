package com.apress.prospring5.ch5.sec5;

import com.apress.prospring5.ch5.sec4.Singer;

public class GoodGuitarist implements Singer {
    @Override
    public void sing() {
        System.out.println("========== GoodGuitarist : Who says I can't be free From all of the things that I used to be");
    }
}
