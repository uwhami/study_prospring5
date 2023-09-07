package com.apress.prospring5.ch5.sec4;

/**
 * 예제 5-18. 다른 타입의 예외를 던지는 ErrorBean 클래스.
 */
public class ErrorBean {
    public void errorProneMethod() throws Exception{
        throw new Exception("==========Generic Exception");
    }

    public void otherErrorProneMethod() throws IllegalArgumentException{
        throw new IllegalArgumentException("==========IllegalArgument Exception");
    }
}
