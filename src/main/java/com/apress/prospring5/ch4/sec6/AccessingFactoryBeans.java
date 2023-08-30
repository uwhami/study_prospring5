package com.apress.prospring5.ch4.sec6;

import org.springframework.context.support.GenericXmlApplicationContext;

import java.security.MessageDigest;

/**
 * 4.6.2 FactoryBean에 직접 접근하기
 *
 * 스프링이 팩토리빈을 참조하는 빈에 팩토리빈이 생성하는 객체를 자동으로 주입해주기 때문에
 * 팩토리빈에 직접 접근할수 없는지 궁금하다면 ? -> 가능하다.
 *
 * 하지만 FactoryBean에 직접 접근하는 방법은 불필요할 뿐 아니라 향후 바뀔지도 모르는 특정 구현에 애플리케이션을 결합시킬 뿐이다.
 */
public class AccessingFactoryBeans {

    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:spring/ch4/sec6/app-context-xml.xml");
        ctx.refresh();
        ctx.getBean("shaDigest", MessageDigest.class);

        MessageDigestFactoryBean factoryBean = (MessageDigestFactoryBean) ctx.getBean("&shaDigest");
        try{
            MessageDigest shaDigest = factoryBean.getObject();
            System.out.println(shaDigest.digest("Hello world".getBytes()));
        } catch(Exception ex){
            ex.printStackTrace();
        }
        ctx.close();

    }
}
