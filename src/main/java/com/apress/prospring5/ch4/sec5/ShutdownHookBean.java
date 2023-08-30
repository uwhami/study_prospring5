package com.apress.prospring5.ch4.sec5;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.GenericApplicationContext;

/**
 * 4.5.2 ApplicationContextAware 인터페이스 사용하기.
 *
 * ApplicationContextAware 인터페이스를 사용하면 ApplicationContext에서 빈이 구성될 때 셧다운 후크를 자동으로 생성하고 구성할 수 있다.
 */
public class ShutdownHookBean implements ApplicationContextAware {

    private ApplicationContext ctx;

    /** @Implements {@link ApplicationContextAware#setApplicationContext(ApplicationContext)}  }*/

    @Override
    public void setApplicationContext(ApplicationContext ctx) throws BeansException {
        if(ctx instanceof GenericApplicationContext){
            ((GenericApplicationContext) ctx).registerShutdownHook();
        }
    }
}
