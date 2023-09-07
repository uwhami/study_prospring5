package com.apress.prospring5.ch5.sec5;

import com.apress.prospring5.ch5.sec4.Singer;

public class GreatGuitarist implements Singer {

    @Override
    public void sing() {
        System.out.println("========== GreatGuitarist : I shot the sheriff, But I did not shoot the deputy");
    }
}
