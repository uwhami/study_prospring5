package com.apress.prospring5.ch5.sec4;

import org.springframework.aop.framework.ProxyFactory;

/**
 * 예제 5-14. WeakKeyCheckAdvice 클래스 테스트하기.
 *
 * 애프터 리터닝 어드바이스를 사용하여 리턴된 결과값에 따라서 예외를 던진다.
 */
public class AfterAdviceDemo {

    private static KeyGenerator getKeyGenerator(){
        KeyGenerator target = new KeyGenerator();

        ProxyFactory pf = new ProxyFactory();
        pf.addAdvice(new WeakKeyCheckAdvice());
        pf.setTarget(target);

        KeyGenerator proxy = (KeyGenerator)pf.getProxy();
        return proxy;
    }

    public static void main(String[] args) {
        KeyGenerator keyGen = getKeyGenerator();

        for(int x=0; x<10; x++){
            try{
                long key = keyGen.getKey();
                System.out.println("==========Key: " + key);
            }catch(SecurityException ex){
                System.out.println("==========Created WEAK_KEY!");
            }
        }
    }
}
