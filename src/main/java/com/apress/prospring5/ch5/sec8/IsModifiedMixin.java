package com.apress.prospring5.ch5.sec8;

import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.support.DelegatingIntroductionInterceptor;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * 5.8.2.2 믹스인 생성하기.
 *
 * 이 구현체는 객체에 인트로듀스 된다. 이 구현체를 믹스인(mixin) 이라고 한다.
 * IntroductionInterceptor 인터페이스를 직접 구현하는 것 보다
 * DelegatingIntroductionInterceptor를 상속하는 것이 믹스인을 생성하기에 훨씬 간단하다.
 * (IntroductionInterceptor의 기본구현체 : DelegatingIntroductionInterceptor)
 *
 * 어드바이스가 적용된 각 객체가 각기 다른 인트로덕션 인스턴스를 갖고 있어야 하므로
 * 어드바이스를 책임지는 DefaultIntroductionAdvisor의 하위 클래스를 생성해야 한다.
 */
public class IsModifiedMixin extends DelegatingIntroductionInterceptor implements IsModified{

    private boolean isModified = false;

    private Map<Method, Method> methodCache = new HashMap<>();


    /**
     * 어드바이스가 적용된 객체마다 하나의 믹스인 인스턴스가 있어야 하는 이유를 보여준다.
     * 믹스인은 객체에 대한 메서드뿐만 아니라 상태도 인트로듀스 한다.
     */
    @Override
    public boolean isModified() {
        return isModified;
    }


    /**
     * 실제로 믹스인에서 invoke()를 구현할 필요는 없지만,
     * 이 예제에서는 메서드를 구현하여 수정이 일어나면 자동으로 감지할 수 있다.
     * 먼저 객체가 아직 수정되지 않았는지만 검사하고, 이미 객체가 수정됐다는걸 알고 있을 때는 굳이 객체가 수정됐는지 검사할 필요가 없다.
     *
     * DelegatingIntroductionInterceptor를 사용할 때는 invoked()를 오버라이드 할 때,
     * super.invoke()를 호출해야 한다.
     * 호출을 올바른 위치(어드바이스가 적용된 객체나 믹스인 자체)로 전달하기 때문이다.
     */
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        if(!isModified){
            if((invocation.getMethod().getName().startsWith("set")) && (invocation.getArguments().length==1)){
                Method getter = getGetter(invocation.getMethod());

                if(getter != null){
                    Object newVal = invocation.getArguments()[0];
                    Object oldVal = getter.invoke(invocation.getThis());
                    if((newVal == null) && (oldVal == null)){
                        isModified = false;
                    }else if((newVal != null) && (oldVal == null)){
                        isModified = true;
                    }else if((newVal == null) && (oldVal != null)){
                        isModified = true;
                    }else{
                        isModified = !newVal.equals(oldVal);
                    }
                }
            }
        }
        return super.invoke(invocation);
    }


    /**
     * 메서드가 수정자(setter) 인지를 확인하고, 수정자 메서드이면 대응되는 접근자(getter) 메서드를 조회한다.
     * 나중에 더 빠른 조회를 위해 접근자/수정자 쌍을 캐시한다.
     * 마지막으로 접근자가 반환한 값과 수정자로 전달한 값을 비교하여 수정이 일어났는지 확인한다.
     * 가능한 null의 여러 조합을 확인하고 수정여부를 적절하게 설정한다.
     */
    private Method getGetter(Method setter){
        Method getter = methodCache.get(setter);

        if(getter != null){
            return getter;
        }

        String getterName = setter.getName().replaceFirst("set","get");
        try{
            getter = setter.getDeclaringClass().getMethod(getterName);
            synchronized (methodCache){
                methodCache.put(setter,getter);
            }
            return getter;
        } catch (NoSuchMethodException ex){
            return null;
        }

    }
}
