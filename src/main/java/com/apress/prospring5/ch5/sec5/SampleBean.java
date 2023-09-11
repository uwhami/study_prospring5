package com.apress.prospring5.ch5.sec5;

/**
 * 5.5.5 DynamicMethodMatcherPointcut을 사용해 동적 포인트컷 만들기
 *
 * 예제 5-29. 동적 포인트컷 생성을 위한 SampleBean 클래스
 */
public class SampleBean {
    public void foo(int x){
        System.out.println("==========Invoked foo() with: " + x);
    }

    public void bar(){
        System.out.println("==========Invoked bar()");
    }
}
