package com.apress.prospring5.ch5.sec7;

import org.springframework.aop.Advisor;
import org.springframework.aop.Pointcut;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.ControlFlowPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.security.core.parameters.P;

public class ControlFlowDemo {

    public void run(){
        TestBean target = new TestBean();

        //ControlFlowPointcut 인스턴스 생성.
        /**
         * 아래 줄에서는 ControlFlowDemo 인스턴스의 test() 메서드에 대해 ControlFlowPointcut 인스턴스를 생성.
         * 'ControlFlowPointcut 인스턴스에 해당되는 Advisor를 사용해
         * 어드바이스가 적용되는 프록시 객체의 모든 메서드에 포인트컷을 적용한다' 라는 의미이다.
         */
        Pointcut pc = new ControlFlowPointcut(ControlFlowDemo.class, "test");
        Advisor advisor = new DefaultPointcutAdvisor(pc, new SimpleBeforeAdvice());

        ProxyFactory pf = new ProxyFactory();
        pf.setTarget(target);
        pf.addAdvisor(advisor);

        TestBean proxy = (TestBean) pf.getProxy();
        System.out.println("==========\tTrying normal invoke");
        proxy.foo();
        System.out.println("==========\n\tTrying under ControlFlowDemo.test()");
        test(proxy);
    }

    private void test(TestBean bean){
        bean.foo();
    }

    public static void main(String[] args) {
        ControlFlowDemo ex = new ControlFlowDemo();
        ex.run();
    }
}
