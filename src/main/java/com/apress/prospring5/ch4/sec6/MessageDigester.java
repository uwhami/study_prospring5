package com.apress.prospring5.ch4.sec6;

import java.security.MessageDigest;

/**
 * 이제 애플리케이션에서 FactoryBean을 어떻게 사용하는지 살펴보자!
 */
public class MessageDigester {
    private MessageDigest digest1;
    private MessageDigest digest2;

    public void setDigest1(MessageDigest digest1) {
        this.digest1 = digest1;
    }

    public void setDigest2(MessageDigest digest2) {
        this.digest2 = digest2;
    }

    public void digest(String msg){
        System.out.println("==========digest1 Used");
        digest(msg, digest1);

        System.out.println("==========digest2 Used");
        digest(msg, digest2);
    }

    public void digest(String msg, MessageDigest digest){
        System.out.println("==========Using Algorithm : " + digest.getAlgorithm());
        digest.reset();
        byte[] bytes = msg.getBytes();
        byte[] out = digest.digest(bytes);
        System.out.println(out);
    }

}
