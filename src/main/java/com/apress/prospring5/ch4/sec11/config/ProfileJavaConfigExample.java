package com.apress.prospring5.ch4.sec11.config;

import com.apress.prospring5.ch4.sec11.Food;
import com.apress.prospring5.ch4.sec11.FoodProviderService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

import java.util.List;

/**
 * 4.11.2 자바 구성을 사용하는 스프링 프로파일.
 *
 * 이 클래스는 XML 파일과 마찬가지 방식으로 사용된다.
 * 컨텍스트에는 양쪽 모두를 사용하도록 등록되지만, JVM 옵션에 설정된 -Dspring.profiles.active 값에 따라서 실제로 둘 중 하나만
 * ApplicationContext 인스턴스를 생성하는데 사용된다.
 */
public class ProfileJavaConfigExample {
    public static void main(String[] args) {
        GenericApplicationContext ctx = new AnnotationConfigApplicationContext(KindergartenConfig.class, HighschoolConfig.class);
        FoodProviderService foodProviderService = ctx.getBean("foodProviderService", FoodProviderService.class);

        List<Food> lunchSet = foodProviderService.provideLunchSet();

        for(Food food : lunchSet){
            System.out.println("==========Food : " + food.getName());
        }
        ctx.close();

    }
}
