package com.apress.prospring5.ch3;

import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.util.StopWatch;

/*
 * 예제 3-74.
 */
public class LookupDemo {

    public static void displayInfo(String beanName, DemoBean bean){
        Singer singer1 = bean.getMySinger();
        Singer singer2 = bean.getMySinger();

        System.out.println("[" + beanName + "] : Are Singer instances same ? " + (singer1 == singer2));

        StopWatch stopWatch = new StopWatch();
        stopWatch.start("lookupDemo");
        for(int x=0; x<100000; x++){
            Singer singer = bean.getMySinger();
            singer.sing();
        }

        stopWatch.stop();

        System.out.println("Time taken to get 100000 times : " + stopWatch.getTotalTimeMillis() + " ms");
    }

    public static void main(String... args){
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:spring/ch3/app-context-xml.xml");
        ctx.refresh();

        DemoBean abstractBean = ctx.getBean("abstractLookupBean", DemoBean.class);
        DemoBean standardBean = ctx.getBean("standardLookupBean", DemoBean.class);

        displayInfo("abstractLookupBean",abstractBean); //false, 938 ms
        displayInfo("standardLookupBean",standardBean); //true, 7ms
        /*
         * abstractLookupBean 은 getMySinger()을 호출할 때마다 Singer의 새 인스턴스를 검색해야 하므로 참조가 동일하지 않다.
         * standardLookupBean은 Singer의 단일 인스턴스가 수정자 주입을 통해 빈으로 전달되어 저장되며 반환되므로 참조가 동일하다.
         */

        ctx.close();
    }
}

