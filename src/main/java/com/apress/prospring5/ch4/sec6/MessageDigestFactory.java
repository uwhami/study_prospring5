package com.apress.prospring5.ch4.sec6;

import java.security.MessageDigest;

/**
 * 4.6.3 factory-bean과 factory-method 애트리뷰트 사용하기.
 * 스프링을 사용하지 않는 서드파티(third-party)애플리케이션이 제공하는 자바빈 인스턴스를 생성해야 할 때.
 * 외부 팩터리 클래스를 사용하는 예제.
 *
 * xml에서 factory-bean, factory-method 애트리뷰트를 지정한다.
 */
public class MessageDigestFactory {
    private String algorithmName = "MD5";

    public MessageDigest createInstance() throws Exception {
        return MessageDigest.getInstance(algorithmName);
    }

    public void setAlgorithmName(String algorithmName){
        this.algorithmName = algorithmName;
    }
}
