package com.apress.prospring5.ch3;

import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.util.StopWatch;

/*
 * 3-83. <replace-method> 태그를 사용해(메서드 대체) 성능 차이를 확인.
 * 동일 타입의 단일 빈에 대한 특정 메서드만 대체하려는 경우에 유용함.
 */
public class MethodReplacementDemo {

    public static void displayInfo(ReplacementTarget target){
        System.out.println(target.formatMessage("Thanks for playing, try again!"));
        StopWatch stopWatch = new StopWatch();
        stopWatch.start("perfTest");

        for(int x=0; x<1000000; x++){
            String out = target.formatMessage("No filter in my head");
        }

        stopWatch.stop();;
        System.out.println("Time taken to get 1000000 Times : " + stopWatch.getTotalTimeMillis());

    }

    public static void main(String[] args){
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:spring/ch3/app-context-xml.xml");
        ctx.refresh();

        //ReplacementTarget 클래스에서 formatMessage를 FormatMessageReplacer로 대체한 클래스 (메서드 대체 사용)
        //Method Replace가 제공하는 오버라이드 된 구현체를 반영함.
        //동적으로 대체한 메서드가 정적으로 정의한 메서드보다 몇배 느림.
        ReplacementTarget replacementTarget = (ReplacementTarget) ctx.getBean("replacementTarget");

        //본래의 ReplacementTarget 클래스 (메서드 대체 사용하지 않음)
        ReplacementTarget standardTarget = (ReplacementTarget) ctx.getBean("standardTarget");

        displayInfo(replacementTarget);
        displayInfo(standardTarget);

    }
}
