package com.apress.prospring5.ch4.sec6;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;

import java.security.MessageDigest;

public class MessageDigestFactoryBean implements FactoryBean<MessageDigest>, InitializingBean {

    private String algorithmName = "MD5";
    private MessageDigest messageDigest = null;

    //스프링에게 FactoryBean이 싱글턴 인스턴스를 관리하는지를 알려줌.
    @Override
    public boolean isSingleton() {
        return true;
    }

    //스프링이 FactoryBean이 생성한 객체를 얻으려 할때 호출하는 메서드
    @Override
    public MessageDigest getObject() throws Exception {
        return messageDigest;
    }

    //새로만든 FactoryBean이 어떤 객체 타입을 반환하는지 스프링에게 알려줄때 사용되는 메서드. -꼭 필요한 경우가 아니라면 가급적 인터페이스 타입을 반환하는것이 낫다.
    @Override
    public Class<MessageDigest> getObjectType() {
        return MessageDigest.class;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        messageDigest = MessageDigest.getInstance(algorithmName);
    }

    public void setAlgorithmName(String algorithmName) {
        this.algorithmName = algorithmName;
    }
}
