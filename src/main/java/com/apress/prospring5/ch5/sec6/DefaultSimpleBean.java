package com.apress.prospring5.ch5.sec6;

public class DefaultSimpleBean implements SimpleBean{
    private long dummy = 0;

    @Override
    public void advised() {
        dummy = System.currentTimeMillis();
    }

    @Override
    public void unadvised() {
        dummy = System.currentTimeMillis();
    }
}
