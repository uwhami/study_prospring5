package com.apress.prospring5.ch3.sec5.sub6;

/*
 * 예제 3-103. 자바에서 싱글턴 패턴의 일반적인 구현 예제. (싱글턴 인스턴스(단일 인스턴스를 갖는 객체)와 싱글턴 패턴이 있음)
 * 싱글턴 패턴은 애플리케이션 전체에서 클래스의 단일 인스턴스를 유지 관리하고 접근하도록 하는 목표는 달성하지만, 결합도가 증가하게 된다.
 * 싱글턴 패턴을 사용하면 싱글턴 인스턴스를 필요로 하는 대부분의 객체가 싱글턴 객체에 직접 접근하기 떄문에 임의로 구성을 변경하기 어렵다.
 *
 * 스프링을 사용하면 싱글턴 디자인 패턴을 사용하지 않고도 싱글턴 인스턴스 생성 모델을 활용할 수 있음.
 */
public class Singleton {

    private static Singleton instance;

    static{
        instance = new Singleton();
    }

    public static Singleton getInstance(){
        return instance;
    }

}

