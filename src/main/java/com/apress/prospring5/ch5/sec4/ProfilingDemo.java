package com.apress.prospring5.ch5.sec4;

import org.springframework.aop.framework.ProxyFactory;

/**
 * 5.4.9 어라운드 어드바이스 만들기.
 * 예제 5-17. doSomeWork() 메서드를 프로파일링하는 ProfilingDemo 클래스
 *
 * 어라운드 어드바이스는 비포 어드바이스와 애프터 어드바이스를 조합한 것과 비슷하나 한가지 큰 차이점이 존재.
 * - 반환값을 변경할 수 있다는 점!
 * - 메서드가 실행되지 않게 할수 있다는 점!
 * 즉, 어라운드 어드바이스를 사용하면 메서드 전체 구현을 새로운 코드로 바꿀 수 있다.
 */
public class ProfilingDemo {

    private static WorkerBean getWorkerBean(){
        WorkerBean target = new WorkerBean();

        ProxyFactory factory = new ProxyFactory();
        factory.setTarget(target);
        factory.addAdvice(new ProfilingInterceptor());

        WorkerBean proxy = (WorkerBean) factory.getProxy();
        return proxy;
    }

    public static void main(String[] args) {
        WorkerBean bean = getWorkerBean();
        bean.doSomeWork(10000000);
    }
}
