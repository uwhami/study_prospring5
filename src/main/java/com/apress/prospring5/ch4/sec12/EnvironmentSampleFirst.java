package com.apress.prospring5.ch4.sec12;

import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.MutablePropertySources;

import java.util.HashMap;
import java.util.Map;

/**
 * 4.12 - 예제 4-82 : 프로퍼티를 읽어 들일 순서 수정.
 *
 * propertySources.addFirst 를 사용하여 Environment가 읽어들일 프로퍼티 순서를 제어한다.
 */
public class EnvironmentSampleFirst {
    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.refresh();

        ConfigurableEnvironment env = ctx.getEnvironment();
        MutablePropertySources propertySources = env.getPropertySources();

        Map<String,Object> appMap = new HashMap<>();
        appMap.put("user.home","application_home");

        propertySources.addFirst(new MapPropertySource("prospring5_MAP", appMap));

        System.out.println("==========user.home: " + System.getProperty("user.home"));
        System.out.println("==========JAVA_Home: " + System.getenv("JAVA_Home"));

        System.out.println("==========user.home: " + env.getProperty("user.home"));
        System.out.println("==========JAVA_Home: " + env.getProperty("JAVA_Home"));

        ctx.close();
    }
}
