package com.apress.prospring5.ch4.sec10.annotated;

import com.apress.prospring5.ch2.decoupled.MessageProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * ex 4-60. 메세지 제공자를 서비스 빈으로 정의.
 */
@Service("provider")
public class ConfigurableMessageProvider implements MessageProvider {

    private String message;

    public ConfigurableMessageProvider(@Value("==========Love on the weekend")String message){
        this.message = message;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
