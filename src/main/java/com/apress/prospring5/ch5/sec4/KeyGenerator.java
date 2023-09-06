package com.apress.prospring5.ch5.sec4;

import java.util.Random;

/**
 * 예제 5-12. KeyGenerator 클래스.
 */
public class KeyGenerator {
    protected static final long WEAK_KEY = 0xFFFFFFF0000000L;
    protected static final long STRONG_KEY = 0xACDF03F590AE56L;

    private Random rand = new Random();

    public long getKey(){
        int x = rand.nextInt(3);
        if(x==1){
            return WEAK_KEY;
        }
        return STRONG_KEY;
    }
}
