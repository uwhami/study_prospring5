package com.apress.prospring5.ch4.sec11;

import org.springframework.context.support.GenericApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.util.List;

/**
 * 4.11.1 스프링 프로파일 기능 사용 예제
 *
 * JVM인수로 profile 애트리뷰트에 따라 빈 인스턴스를 생성
 * VM options에서 -Dspring.profiles.active="kindergarten" 또는 -Dspring.profiles.active="highschool" 적용함.
 * XML 파일에 profile 을 설정함.
 */
public class ProfileXmlConfigDemo {
    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:spring/ch4/sec11/*-config.xml");
        ctx.refresh();

        //JVM인수로 profile 애트리뷰트에 따라 빈 인스턴스를 생성
        //VM options에서 -Dspring.profiles.active="kindergarten" 또는 -Dspring.profiles.active="highschool" 적용함.
        FoodProviderService foodProviderService = ctx.getBean("foodProviderService", FoodProviderService.class);
        List<Food> foodList = foodProviderService.provideLunchSet();
        for(Food food : foodList){
            System.out.println("==========Food : " + food.getName());
        }
        ctx.close();
    }
}
