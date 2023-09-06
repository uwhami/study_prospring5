package com.apress.prospring5.ch5.sec3;

import org.springframework.aop.framework.ProxyFactory;

/**
 * 예제 5-3. AgentDecorator 어드바이스(좀 더 구체적으로 invoke()메서드)를 코드에 위빙하는 단계
 * 1. 어드바이스 적용 대상인 Agent의 인스턴스를 생성한다.
 * 2. 해당 인스턴스에 적용할 프록시를 생성한다.
 * 3. 생성한 프록시 팩터리가 AgentDecorator 어드바이스를 위빙하도록 한다.
 *
 * 이 예제에서는 어드바이스 적용 클래스는 스프링이나 AOP 얼라이언스 인터페이스에 어떤 의존성도 갖고 있지 않다.
 * AOP를 염두하지 않고 클래스를 작성했더라도 거의 모든 클래스에 어드바이스를 적용할 수 있다!
 *
 * 스프링 AOP의 유일한 제약점은 final 접근 제약자를 붙인 클래스에 어드바이스를 적용할 수 없다.
 * 이들 클래스는 오버라이드 할 수 없어 프록시를 생성할 수 없기 때문!
 */
public class AgentAOPDemo {
    public static void main(String[] args) {
        Agent target = new Agent(); //speak() : "Bond"가 있는 내가 만든 클래스.

        ProxyFactory pf = new ProxyFactory();
        pf.addAdvice(new AgentDecorator()); //ProxyFactory에 AgentDecorator 어드바이스를 전달.
        pf.setTarget(target);   //setTarget을 호출해 위빙 대상을 지정.

        Agent proxy = (Agent) pf.getProxy();    //ProxyFactory에 몇가지 어드바이스를 추가하여 getProxy()를 호출해 프록시를 생성.

        target.speak();
        System.out.println("==========");
        proxy.speak();

    }
}
