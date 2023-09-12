package com.apress.prospring5.ch5.sec6;

import org.springframework.aop.Advisor;
import org.springframework.aop.framework.Advised;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;

/**
 * 예제 5-45. 다양한 타입 프록시를 테스트하는 예제
 */
public class ProxyPerfTest {

    public static void runCglibTests(Advisor advisor, SimpleBean target){
        ProxyFactory pf = new ProxyFactory();
        pf.setProxyTargetClass(true);
        pf.setTarget(target);
        pf.addAdvisor(advisor);

        SimpleBean proxy = (SimpleBean) pf.getProxy();
        System.out.println("==========Running CGLIB (Standard) Tests");
        test(proxy);
    }

    public static void runCglibFrozenTests(Advisor advisor, SimpleBean target){
        ProxyFactory pf = new ProxyFactory();
        pf.setProxyTargetClass(true);
        pf.setTarget(target);
        pf.addAdvisor(advisor);
        pf.setFrozen(true);

        SimpleBean proxy = (SimpleBean) pf.getProxy();
        System.out.println("==========Running CGLIB (Frozen) Tests");
        test(proxy);
    }

    public static void runJdkTests(Advisor advisor, SimpleBean target){
        ProxyFactory pf = new ProxyFactory();
        pf.setTarget(target);
        pf.addAdvisor(advisor);
        pf.setInterfaces(new Class[]{SimpleBean.class}); //책에서는 그냥 nes Class{}로 나와있으나 오류남.

        SimpleBean proxy = (SimpleBean) pf.getProxy();
        System.out.println("==========Running JDK Tests");
        test(proxy);
    }

    private static void test(SimpleBean bean){
        long before = 0;
        long after = 0;

        System.out.println("==========Testing Advised Method");
        before = System.currentTimeMillis();
        for(int x=0; x<500000; x++){
            bean.advised();
        }
        after = System.currentTimeMillis();
        System.out.println("==========Took " + (after - before) + " ms");


        System.out.println("==========Testing equals() Method");
        before = System.currentTimeMillis();
        for(int x=0; x<500000; x++){
            bean.equals(bean);
        }
        after = System.currentTimeMillis();
        System.out.println("==========Took " + (after - before) + " ms");


        System.out.println("==========Testing hashCode() Method");
        before = System.currentTimeMillis();
        for(int x=0; x<500000; x++){
            bean.hashCode();
        }
        after = System.currentTimeMillis();
        System.out.println("==========Took " + (after - before) + " ms");


        Advised advised = (Advised) bean;

        System.out.println("==========Testing Advised.getProxyTargetClass() Method");
        before = System.currentTimeMillis();
        for(int x=0; x<500000; x++){
            advised.getTargetClass();
        }
        after = System.currentTimeMillis();
        System.out.println("==========Took " + (after - before) + " ms");

        System.out.println("==========>>>\n");

    }


    public static void main(String[] args) {
        SimpleBean target = new DefaultSimpleBean();
        Advisor advisor = new DefaultPointcutAdvisor(new TestPointcut(), new NoOpBeforeAdvice());

        runCglibTests(advisor, target);
        runCglibFrozenTests(advisor, target);
        runJdkTests(advisor, target);
    }
}
