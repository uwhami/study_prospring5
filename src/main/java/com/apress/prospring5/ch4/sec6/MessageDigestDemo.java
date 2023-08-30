package com.apress.prospring5.ch4.sec6;

import org.springframework.context.support.GenericXmlApplicationContext;

/**
 * 4.6.1 - 예제 4-27. FactoryBean 테스트
 *
 * BeanFactory에 MessageDigest 빈을 구성한 적이 없지만 SHA1과 MD5 알고리즘을 사용하는
 * MessageDigest구현체가 MessageDigest빈으로 제공됐다. 이런 방식으로 BeanFactory가 동작한다.
 *
 * FactoryBean<MessageDigest> 을 상속받는 클래스를 만들어서, 해당클래스를 빈으로 등록하고
 * MessageDigester 클래스에 프로퍼티에 등록한 빈 클래스를 참조했다.
 *
 * FactoryBean은 new 연산자로 인스턴스를 생성할 수 없는 클래스를 다룰때 완벽한 솔루션이다.
 * 자바 클래스로 구성을 할 때는 FactoryBean 사용방법에 차이가 있다.
 * 컴파일러가 적절한 타입으로 프로퍼티를 설정하도록 제한한다.
 *
 * 다음예제부터 살펴보자. (MessageDigesterConfigDemo)
 */
public class MessageDigestDemo {
    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:spring/ch4/sec6/app-context-xml.xml");
        ctx.refresh();

        MessageDigester digester = ctx.getBean("digester", MessageDigester.class);
        digester.digest("Hello World!");
        ctx.close();

    }
}
