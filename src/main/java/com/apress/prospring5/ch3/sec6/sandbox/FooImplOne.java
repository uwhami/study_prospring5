package com.apress.prospring5.ch3.sec6.sandbox;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary //3-124. 자동와이어링 할때 스프링에게 자신의 빈을 우선순위를 높게 지정함.(정확히 두 개의 빈이 있는 경우만 유효함)
public class FooImplOne implements Foo{
}
