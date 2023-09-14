package com.apress.prospring5.ch5.sec10;

import com.apress.prospring5.ch2.common.Contact;
import com.apress.prospring5.ch5.sec10.config.AppConfig;
import com.apress.prospring5.ch5.sec8.IsModified;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

/**
 * 5.10.2.2 ProxyFactoryBean을 사용한 인트로덕션.
 *
 * 예제 5-62. IntroductionConfigDemo 클래스.
 * 이전의 인트로덕션 예제와 동일하게 출력되지만
 * 이번에는 프록시를 ApplicationContext에서 가져왔으며 애클리케이션 코드 내에는 구성이 전혀 없다.
 *
 * 어노테이션을 이용하여 자바 구성 클래스로 가지고 오는 방법도 구현.
 */
public class IntroductionConfigDemo {
    public static void main(String[] args) {

        //ApplicationContext에서 가지고 오는 방법.
//        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
//        ctx.load("classpath:spring/ch5/sec10/app-context-xml2.xml");
//        ctx.refresh();

        //Annotation을 사용하는 방법.
        GenericApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);

        Contact bean = (Contact) ctx.getBean("bean");
        IsModified mod = (IsModified) bean;

        System.out.println("==========Is Contact ? : " + (bean instanceof Contact));
        System.out.println("==========Is IsModified ? : " + (bean instanceof IsModified));

        System.out.println("==========Has been modified ? : " + mod.isModified());
        bean.setName("johnMayer");

        System.out.println("==========Has been modified ? : " + mod.isModified());
        bean.setName("Eric Clapton");

        System.out.println("==========Has been modified ? : " + mod.isModified());


    }
}
