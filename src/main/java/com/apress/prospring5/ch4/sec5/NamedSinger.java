package com.apress.prospring5.ch4.sec5;

import org.springframework.beans.factory.BeanNameAware;

/**
 * 4.5.1 BeanNameAware 인터페이스 사용하기.
 * 자신에게 부여된 빈 이름을 알고자 구현하는 인터페이스.
 * 빈 구성을 마친 뒤, 라이프사이클 콜백(초기화나 소멸)을 호출하기 전에 setBeanName을 호출한다.
 */
//예제 4-17. 빈이름을 얻어와 출력
public class NamedSinger implements BeanNameAware {
    private String name;

    /** @Implements {@link BeanNameAware#setBeanName(String)} */
    @Override
    public void setBeanName(String beanName) {
        this.name = beanName;
    }

    public void sing(){
        System.out.println("==========Singer name : [" + name + "] - sing()");
    }
}
