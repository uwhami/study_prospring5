package com.apress.prospring5.ch5.sec10;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

public class ComplexAdvice {
    public void simpleBeforeAdvice(JoinPoint joinPoint, Guitar value){
        if(value.getBrand().equals("Gibson")){
            System.out.println("==========Executing : "
                    + joinPoint.getSignature().getDeclaringTypeName() + " "
                    + joinPoint.getSignature().getName());
        }
    }

    //예제 5-74. 수정된 ComplexAdvice
    public Object simpleAroundAdvice(ProceedingJoinPoint pjp, Guitar value) throws Throwable{

        System.out.println("==========Before execution: "
                            + pjp.getSignature().getDeclaringTypeName()
                            + " "
                            + pjp.getSignature().getName()
                            + " argument: " + value.getBrand());

        Object retVal = pjp.proceed();

        System.out.println("==========After execution : "
                            + pjp.getSignature().getDeclaringTypeName()
                            + " "
                            + pjp.getSignature().getName()
                            + " argument : " + value.getBrand());
        return retVal;
    }

}
