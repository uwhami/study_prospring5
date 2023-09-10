package com.apress.prospring5.ch5.sec5;

import org.springframework.aop.ClassFilter;
import org.springframework.aop.support.DynamicMethodMatcherPointcut;

import java.lang.reflect.Method;

/**
 * 5.5.5 DynamicMethodMatcherPointcut을 사용해 동적 포인트 컷 만들기.
 *
 * 예제 5-29. 정적 pointcut과 마찬가지로 스프링은ㄷ 동적 포인트컷을 생성하는데 사용하는 편리한 기반 클래스를 제공.
 * *동적 검사만 구현하면 되지만 정적 검사도 함께 구현하는 이유
 * : bar()메서드에는 어드바이스를 적용할 필요가 없다.
 * 정적 검사를 사용해 이를 알려주면 스프링은 이 메서드에 동적 검사를 수행하지 않는다.
 * 또한, 정적 검사의 결과는 성능 향상을 위해 캐싱된다.
 * 하지만 정적 검사를 무시하면 스프링은 bar() 메서드를 호출할 때마다 동적 검사를 수행한다.
 * 따라서, 권장하는 방법은 먼저 getClassFilter 메서드에서 클래스 검사를 한 다음,
 * matches(Method, Class<?>) 메서드에서 메서드를 검사하고
 * 마지막으로 matches(Class<?>, Object []) 메서드에서 인수를 검사하는 것이다.
 * 이렇게 하면 포인트컷을 이해하고 유지보수하기가 훨씬 쉬워지고 성능도 향상된다.
 *
 */
public class SimpleDynamicPointcut extends DynamicMethodMatcherPointcut {

    /**
     * getClassFilter 메서드를 오버라이딩 하면 메서드 일치 여부를 비교하는 메서드에 클래스를 검사하지 않아도 되며,
     * 이는 특히 동적 검사에 중요하다.
     */
    @Override
    public ClassFilter getClassFilter() {
        return cls -> (cls == SampleBean.class);
    }

    /**
     * foo() 외의 다른 메서드는 정적 검사를 통과할 수 없으므로
     * 동적 검사를 할 때는 foo() 메서드를 처리한다.
     */
    @Override
    public boolean matches(Method method, Class<?> targetClass) {
        System.out.println("==========Static check for " + method.getName());
        return method.getName().equals("foo");
    }

    @Override
    public boolean matches(Method method, Class<?> targetClass, Object... args) {
        System.out.println("==========Dynamic check for " + method.getName());
        int x = ((Integer)args[0]).intValue();
        return (x != 100);
    }
}
