package com.apress.prospring5.ch5.sec8.introduction;

import com.apress.prospring5.ch5.sec8.IsModifiedMixin;
import org.springframework.aop.support.DefaultIntroductionAdvisor;

/**
 * 5.8.2.3 Advisor 생성하기.
 *
 * DefaultIntroductionAdvisor를 상속해 IsModifiedAdvisor를 만들었다.
 * 이 단계는 선택사항이지만 새 믹스인 인스턴스가 각 어드바이스가 적용된 객체에 사용될 수 있도록 도화준다.
 */
public class IsModifiedAdvisor extends DefaultIntroductionAdvisor {

    public IsModifiedAdvisor(){
        super(new IsModifiedMixin());
    }
}
