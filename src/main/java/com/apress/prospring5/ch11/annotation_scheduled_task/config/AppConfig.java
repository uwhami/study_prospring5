package com.apress.prospring5.ch11.annotation_scheduled_task.config;

import org.springframework.context.annotation.*;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

/**
 * 새로운 클래스에 @Import 애너테이션을 사용해 데이터 접속 구성을 결합하고
 * @ImportResource 애너테이션을 사용해 XML 구성을 결합하는 방식이다.
 */
@Configuration
//@Import({DataServiceConfig.class})
//@EnableScheduling
@EnableAsync    /* 비동기 태스크 실행 구성을 위한 설정 */
@ComponentScan(basePackages = "com.apress.prospring5.ch11.annotation_scheduled_task") /* 비동기 태스크 실행 구성을 위한 설정 */
public class AppConfig {

    /* TaskScheduler 빈을 정의하여 사용. */
    @Bean
    TaskScheduler casScheduler(){
        ThreadPoolTaskScheduler carScheduler = new ThreadPoolTaskScheduler();
        carScheduler.setPoolSize(10);
        return carScheduler;
    }

}
