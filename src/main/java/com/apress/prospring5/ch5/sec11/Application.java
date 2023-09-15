package com.apress.prospring5.ch5.sec11;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * 예제 5-85. 스프링 부트로 전환된 예제.
 *
 * 애플리케이션에 스프링 부트 aspectj-boot 라이브러리를 의존성으로 추가하면
 * 기본적으로 AOP 스프링 지원기능을 사용할 수 있으므로
 * @EnableAspectJAutoProxy(proxyTargetClass=true) 애너테이션이 필요하지 않다.
 * 스프링 부트는 프록시 타입을 자동으로 감지하므로 추가 설정을 하지 않아도 된다.
 *
 * AppConfig 클래스의 애너테이션을 모두 주석처리하고 실행시키면 실행된다.
 */
@SpringBootApplication
public class Application {

    private static Logger logger = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) throws Exception{
        ConfigurableApplicationContext ctx = SpringApplication.run(Application.class, args);
        assert(ctx!=null);

        NewDocumentarist documentarist = ctx.getBean("documentarist", NewDocumentarist.class);
        documentarist.execute();

        System.in.read();
        ctx.close();
    }
}
