package com.apress.prospring5.ch5.sec4;

import org.springframework.aop.ThrowsAdvice;
import org.springframework.aop.framework.ProxyFactory;

import java.lang.reflect.Method;

/**
 * 5.4.10 스로우 어드바이스 만들기.
 * 예제 5-19. 메서드 시그니처를 모두 보여주는 SimpleThrowsAdvice 클래스.
 *
 * * 스로우(Throws) 어드바이스
 * - 항상 메서드를 호출하는 조인포인트 이후에 실행된다.
 * - 메서드가 예외를 던질 때만 실행된다. (프로그램 실행을 거의 제어할 수 없음)
 * - 발생한 예외를 무시하고 메서드에 대한 값을 대신 반환할 수 없다.
 * -* 프로그램 흐름을 변경할 수 있는 유일한 방법 : 발생한 예외 타입을 변경한다!
 * --> 해당 API의 모든 클래스에 어드바이스를 적용해 예외 계층 구조를 좀 더 명확하게 관리하고 다시 정의할 수 있다.
 * --> 애플리케이션 전반에 퍼져 있는 에러 로깅 코드의 양을 줄일 수도 있다.
 *
 */
public class SimpleThrowsAdvice implements ThrowsAdvice {

    /**
     * 일방적인 Exception이 던져지면 첫번째 afterThrowing() 메서드가 호출되지만
     * IllegalArgumentException이 던져지면 두번째 afterThrowing() 메서드가 호출된다.
     */
    public void afterThrowing(Exception ex) throws Throwable{
        System.out.println("==========***");
        System.out.println("==========Generic Exception Capture");
        System.out.println("==========Caught: " + ex.getClass().getName());
        System.out.println("==========***\n");
    }

    public void afterThrowing(Method method, Object args, Object target, IllegalArgumentException ex) throws Throwable{
        System.out.println("==========***");
        System.out.println("==========IllegalArgumentException Capture");
        System.out.println("==========Caught: " + ex.getClass().getName());
        System.out.println("==========Method: " + method.getName());
        System.out.println("==========***\n");
    }

    public static void main(String[] args) {
        ErrorBean errorBean = new ErrorBean();

        ProxyFactory pf = new ProxyFactory();
        pf.setTarget(errorBean);
        pf.addAdvice(new SimpleThrowsAdvice());

        ErrorBean proxy = (ErrorBean) pf.getProxy();

        try{
            proxy.errorProneMethod();
        }catch(Exception ignored){

        }

        try{
            proxy.otherErrorProneMethod();
        }catch(Exception ignored){

        }

    }

}
