package com.apress.prospring5.ch3.sec6.config;

import com.apress.prospring5.ch3.sec6.sandbox.*;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.GenericApplicationContext;

public class TargetDemo {

    @Configuration
    static class TargetConfig{

        @Bean
        public Foo fooImplOne(){
            return new FooImplOne();
        }

        @Bean
        public Foo fooImplTwo(){
            return new FooImplTwo();
        }

        @Bean
        public Bar bar(){
            return new Bar();
        }

        @Bean
        public TrickyTarget trickyTarget() {
            return new TrickyTarget();
        }
    }

    public static void main(String[] args){
        //컴포넌트 스캐닝을 활성화하지 않았기 때문에 스테레오 타입 애너테이션을 사용한 빈 선언은 무시됨.
        GenericApplicationContext ctx = new AnnotationConfigApplicationContext(TargetConfig.class);
        TrickyTarget t = ctx.getBean(TrickyTarget.class);
        ctx.close();
    }

    /**
     * 3.6 단원을 마치며..
     * 자동와이어링을 사용해야 하는가라고 묻는다면 정답은 '아니오'다.
     * 소규모 어플리케이션에서는 시간을 절약할 수 있지만 많은 나쁜 사례로 이어질 수 있다.
     * 대규모 어플리케이션에서는 유연성이 떨어진다.
     * byName을 사용하는 것이 좋은 생각처럼 보일 수 있지만, 사용자가 자동와이어링 기능의 장점을 얻으려고 클래스에 인위적인 프로퍼티 이름을 지정하는 결과로 이어진다.
     */
}
