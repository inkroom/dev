package cn.inkroom.web.socket.aop;

import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * Created by 墨盒 on 9:22.
 */

public class Aop implements MethodBeforeAdvice, AfterReturningAdvice {
    private long start = 0;

    public void before(Method method, Object[] args, Object o) throws Throwable {
        start = System.currentTimeMillis();
    }

    public void afterReturning(Object returnValue, Method method, Object[] args, Object o1) throws Throwable {
        if (returnValue != null)
            System.out.println(method.toGenericString() + "  执行时间为" + (System.currentTimeMillis() - start) + "  返回值为" + returnValue.toString());
        else
            System.out.println(method.toGenericString() + "  执行时间为" + (System.currentTimeMillis() - start));
    }
}
