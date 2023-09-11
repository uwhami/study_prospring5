package com.apress.prospring5.ch5.sec5;

import org.springframework.aop.ClassFilter;
import org.springframework.aop.support.StaticMethodMatcherPointcut;

import java.lang.reflect.Method;

/**
 * 5.5.4 StaticMethodMatcherPointcut을 사용해 정적 포인트컷 생성하기.
 */
public class SimpleStaticPointcut extends StaticMethodMatcherPointcut {

    @Override
    public boolean matches(Method method, Class<?> targetClass) {
        return ("sing".equals(method.getName()));
    }

    @Override
    public ClassFilter getClassFilter() {
        return cls -> (cls == GoodGuitarist.class);
        /**
         * 람다식을 사용하지 않은 getClassFilter 메서드
         * return new ClassFilter(){
         *   public boolean matches(Calss<?> cls){
         *      return (cls == GoodGuitarist.class);
         *   }
         *  };
         */

    }
}
