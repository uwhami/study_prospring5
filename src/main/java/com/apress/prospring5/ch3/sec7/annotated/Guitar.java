package com.apress.prospring5.ch3.sec7.annotated;

import org.springframework.stereotype.Component;

@Component("gopher")
public class Guitar {
    public void sing(){
        System.out.println("==========Cm Eb Fm Ab Bb : Annotation");
    }
}
