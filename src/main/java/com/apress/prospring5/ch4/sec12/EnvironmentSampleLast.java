package com.apress.prospring5.ch4.sec12;

import org.springframework.context.support.GenericApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.MutablePropertySources;

import java.util.HashMap;
import java.util.Map;

/**
 * 4.12 Environment와 PropertySource 추상화.
 * ex 4-81 : Environment의 간단한 예제 코드
 *
 * Environment 인터페이스가 캡슐화하는 다른 주요 요소는 프로퍼티가 있다.
 * 프로퍼티는 애플리케이션 폴더나 데이터베이스 접근 정보 등과 같은 애플리케이션의 전반적인 환경 구성을 저장하는데 사용한다.
 *
 * ConfigurableEnvironment 인터페이스를 통해 MutablePropertySources를 가지고 온다
 * (* MutablePropertySources : 자신이 포함하고 있는 프로퍼티 소스를 조작할 수 있는 PropertySources 인터페이스의 기본 구현체)
 * 그 다음 Map 하나를 만들고 여기에 애플리케이션에서 사용할 프로퍼티를 넣은 후,
 * 이 맵을 인자로 전달해 MapPropertySource 클래스의 인스턴스를 생성한다.
 */
public class EnvironmentSampleLast {
    public static void main(String[] args) {
        GenericApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.refresh();

        ConfigurableEnvironment env = ctx.getEnvironment();
        MutablePropertySources propertySources = env.getPropertySources();

        Map<String,Object> addMap = new HashMap<>();
        addMap.put("application.home", "application_home");
        //addMap.put("user.home", "application_home");
        //-> 스프링은 실행중인JVM, 환경변수, 애플리캐이션정의프로퍼티 순서대로 접근하기 떄문에
        // addLast를 사용하면 새로 정의한 user.home값을 읽지 않고
        // 시스템 프로퍼티와 동일한 값인 user.home 값을 읽는다.

        propertySources.addLast(new MapPropertySource("prospring5_MAP", addMap));

        System.out.println("==========user.home: " + System.getProperty("user.home"));
        System.out.println("==========JAVA_Home: " + System.getenv("JAVA_Home"));

        System.out.println("==========user.home: " + env.getProperty("user.home"));
        System.out.println("==========JAVA_Home: " + env.getProperty("JAVA_Home"));
        System.out.println("==========application.home: " + env.getProperty("application.home"));

        ctx.close();

    }
}
