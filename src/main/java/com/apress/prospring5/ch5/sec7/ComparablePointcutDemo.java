package com.apress.prospring5.ch5.sec7;

import com.apress.prospring5.ch2.common.Guitar;
import com.apress.prospring5.ch5.sec4.SimpleBeforeAdvice;
import com.apress.prospring5.ch5.sec5.GrammyGuitarist;
import org.springframework.aop.Advisor;
import org.springframework.aop.ClassFilter;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.ComposablePointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.StaticMethodMatcher;

import java.lang.reflect.Method;

/**
 * 5.7.2 ComposablePointcut 사용하기
 *
 * 이전의 Pointcut 에서는 Advisor 마다 하나의 포인트컷만을 사용했으나,
 * 두 개 이상의 포인트컷을 함께 적용할 수 이따.
 * ComposablePointcut을 사용해 두 포인트컷을 단일 포인트 컷으로 결합한다.
 * ComposablePointcut은 union()와 intersection() 두 메서드를 제공한다.
 */
public class ComparablePointcutDemo {

    public static class SingMethodMatcher extends StaticMethodMatcher{
        @Override
        public boolean matches(Method method, Class<?> targetClass) {
            return (method.getName().startsWith("si"));
        }
    }

    public static class TalkMethodMatcher extends StaticMethodMatcher{
        @Override
        public boolean matches(Method method, Class<?> targetClass) {
            return "talk".equals(method.getName());
        }
    }

    public static class RestMethodMatcher extends StaticMethodMatcher{
        @Override
        public boolean matches(Method method, Class<?> targetClass) {
            return (method.getName().endsWith("st"));
        }
    }

    public static GrammyGuitarist getProxy(ComposablePointcut pc, GrammyGuitarist target){
        Advisor advisor = new DefaultPointcutAdvisor(pc, new SimpleBeforeAdvice());

        ProxyFactory pf = new ProxyFactory();
        pf.addAdvisor(advisor);
        pf.setTarget(target);
        return (GrammyGuitarist) pf.getProxy();
    }

    public static void testInvoke(GrammyGuitarist proxy){
        proxy.sing();
        proxy.sing(new Guitar());
        proxy.rest();
        proxy.talk();
    }

    public static void main(String[] args) {
        GrammyGuitarist johnMayer = new GrammyGuitarist();

        ComposablePointcut pc = new ComposablePointcut(ClassFilter.TRUE, new SingMethodMatcher());

        System.out.println("==========Test1 >> ");
        GrammyGuitarist proxy = getProxy(pc, johnMayer);
        testInvoke(proxy);
        System.out.println();

        // union 메서드를 추가하면 or 조건을 추가한다.
        System.out.println("==========Test2 >> ");
        pc.union(new TalkMethodMatcher());
        proxy = getProxy(pc, johnMayer);
        testInvoke(proxy);
        System.out.println();

        // intersection 메서드를 추가하면 and 조건을 추가한다.
        System.out.println("==========Test3 >> ");
        pc.intersection(new RestMethodMatcher());
        proxy = getProxy(pc, johnMayer);
        testInvoke(proxy);
        System.out.println();
    }
}
