package com.apress.prospring5.ch4.sec15;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Arrays;

/**
 * 4.15 스프링 부트
 *
 * 스프링 부트 목적 : 처음 스프링을 사용해 애플리케이션을 만드는 작업을 간단하게 하기 위함.
 * 스프링 부트는 사용자가 필요한 의존성을 직접 추측해서 모으는 작업을 없애고 측정이나 상태점검 처럼 대부분 애플리케이션이 필요로 하는
 * 몇가지 공통 기능을 제공한다.
 *
 * 스프링 부트는 버전별로 지원하는 의존성 관리 목록을 가지고 있다.
 * 필요한 라이브러리의 버전이 사전에 선택돼 있어 API가 완벽히 일치하게 되며, 이러한 라이브러리와 버전은 스프링 부트가 관리한다.
 *
 * 스프링 부트를 업그레이드 하면 이러한 버전도 업그레이드가 된다.
 */

/**
 * @SpringBootApplication 애너테이션은 @Configuration, @EnableAutoConfiguration, @ComponentScan 을 포함하고 있다. (p.318)\
 * @Configuration : 해당 클래스가 @Bean 으로 빈을 정의하는 클래스임을 나타냄.
 * @EnableAutoConfiguration : 사용자가 필요로 할 빈을 추측해 구성한 뒤 ApplicationContext를 활성화 한다.
 * @ComponentScan :
 *
 *
 * 스프링 부트를 사용함으로 XML 파일 없이 애너테이션만으로 Hellow World 빈 정의를 찾아서 생성했다.
 * @SpringBeanApplication 를 붙인 클래스를 검색하기 때문!
 *
 */

@SpringBootApplication
public class Application {

    private static Logger logger = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) throws Exception{
        ConfigurableApplicationContext ctx = SpringApplication.run(Application.class, args);

        //컨텍스트를 가져왔는지 확인함.(ctx가 null이 아니라는 가정)
        assert(ctx != null);
        logger.info("==========Searching Bean...");

        //listing all bean definition names - 컨텍스트에 정의된 모든 빈 목록 가져오기.
        Arrays.stream(ctx.getBeanDefinitionNames()).forEach(logger::info);

        HelloWorld hw = ctx.getBean(HelloWorld.class);
        hw.sayHi();

        //애플리케이션 종료 확인 : 바로 종료되는것이 아니라 개발자가 키를 누를때까지 대기 후, 누른 후 종료되도록 함.
        System.in.read();
        ctx.close();
    }

}
