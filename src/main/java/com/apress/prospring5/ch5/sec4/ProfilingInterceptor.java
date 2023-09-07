package com.apress.prospring5.ch5.sec4;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.util.StopWatch;

import java.lang.reflect.Method;

/**
 * 예제 5-16. 메서드 호출 시간을 프로파일링 하는 ProfilingInterceptor 클래스.
 *
 * ProfilingDemo 클래스에서 불러와서 사용한다.
 */
public class ProfilingInterceptor implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        StopWatch sw = new StopWatch();
        sw.start(invocation.getMethod().getName());

        Object returnValue = invocation.proceed();

        sw.stop();
        dumpInfo(invocation, sw.getTotalTimeMillis());
        return returnValue;
    }

    private void dumpInfo(MethodInvocation invocation, long ms){
        Method m = invocation.getMethod();
        Object target = invocation.getThis();
        Object[] args = invocation.getArguments();  //.p349에는 Object 라고만 되어있으나 에러남.

        System.out.println("==========Executed Method : " + m.getName());
        System.out.println("==========Type of Object : " + target.getClass().getName());
        System.out.println("==========With arguments: ");
        for(int x=0; x<args.length; x++){
            System.out.print("       >" + args[x]);
        }
        System.out.print("==========\n");
        System.out.println("==========Times Took : " + ms + " ms");

        /**
         * 위 내용으로 어떤 메서드가 실행되었는지,
         * 대상이 어떤 클래스인지,
         * 전달된 인수는 무엇인지,
         * 호출에 걸린 시간은 얼마인지
         *
         * 확인할 수 있다.
         */

    }
}
