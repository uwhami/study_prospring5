package com.apress.prospring5.ch4.sec15;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 *
 * XML 설정없이 바로 실행 가능한 스트링 부트!
 *
 * @Component에서 특화된 애너테이션인 @Controller 애너테이션을 사용한다.
 * 이런 타입의 클래스는 특정 요청 URL가 매핑된 @RequestMapping 애너테이션을 적용한 매서드를 가지고 있다.
 * @RestController는 REST 서비스에 사용하는 @Controller 애너테이션이다.
 *
 * localhost:8080 접속시 아래 정보로 로그인하면 helloworld를 볼 수 있다.
 *
 * LOGIN ID : user
 * password : 콘솔창에 있는 password
 */
@SpringBootApplication(scanBasePackageClasses = HelloWorld.class)
public class WebApplication {

    private static Logger logger = LoggerFactory.getLogger(WebApplication.class);

    public static void main(String[] args) throws Exception{
        ConfigurableApplicationContext ctx = SpringApplication.run(WebApplication.class, args);
        assert(ctx != null);
        logger.info("==========Application starts...");

        System.in.read();
        ctx.close();
    }

}
