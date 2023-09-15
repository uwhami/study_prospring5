package com.apress.prospring5.ch5.sec11;

import com.apress.prospring5.ch2.common.Guitar;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @Aspect 애너테이션은 이 클래스가 애스펙트 클래스라는 것을 나타낸다.
 * XML 설정에서 <context:component-scan> 태그를 사용해 컴포넌트를 스캔하는 것처럼
 * 스프링이 컴포넌트를 스캔할 수 있게 하려면 클래스에 @Component 애너테이션을 적용해야 한다.
 */
@Component
@Aspect
public class AnnotatedAdvice {


    //execution(execution(* 뒤에 띄어쓰기를 꼭 해줘야 함. 안그러면 오류남.
    @Pointcut("execution(* com.apress.prospring5.ch5.sec11..sing*(com.apress.prospring5.ch2.common.Guitar))" +
            " && args(value)")
    public void singExecution(Guitar value){

    }

    @Pointcut("bean(john*)")
    public void isJohn(){

    }

    /**
     * 비포어드바이스
     * singExecution(value) && isJohn()의 값은 두 포인트컷이 어드바이스를 적용하기 위한 조건을 나타내며
     * 이것은 ComparablePointcut의 intersection 연산과 같다.
     */
    @Before("singExecution(value) && isJohn()")
    public void simpleBeforeAdvice(JoinPoint joinPoint, Guitar value){
        if(value.getBrand().equals("Gibson")){
            System.out.println("==========Executing: "
                                + joinPoint.getSignature().getDeclaringTypeName() + " "
                                + joinPoint.getSignature().getName()
                                + " argument: " + value.getBrand());
        }
    }

    /**
     * 어라운드 어드바이스
     */
    @Around("singExecution(value) && isJohn()")
    public Object simpleAroundAdvice(ProceedingJoinPoint pjp, Guitar value) throws Throwable{
        System.out.println("==========Before execution: "
                            + pjp.getSignature().getDeclaringTypeName() + " "
                            + pjp.getSignature().getName()
                            + " argument: " + value.getBrand());
        Object retVal = pjp.proceed();

        System.out.println("==========After execution: "
                            + pjp.getSignature().getDeclaringTypeName() + " "
                            + pjp.getSignature().getName()
                            + " argument: " + value.getBrand());

        return retVal;
    }

}
