package com.apress.prospring5.ch11.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

/**
 * 새로운 클래스에 @Import 애너테이션을 사용해 데이터 접속 구성을 결합하고
 * @ImportResource 애너테이션을 사용해 XML 구성을 결합하는 방식이다.
 */
@Configuration
@Import({DataServiceConfig.class})
@ImportResource("classpath:spring/ch11/task-namespace-app-context.xml")
public class AppConfig {
}
