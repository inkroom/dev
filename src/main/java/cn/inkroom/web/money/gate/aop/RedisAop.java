package cn.inkroom.web.money.gate.aop;

import cn.inkroom.web.money.gate.daos.redis.RedisPool;
import org.springframework.aop.AfterReturningAdvice;

import java.lang.reflect.Method;

/**
 * redis连接池关闭aop
 */

public class RedisAop implements AfterReturningAdvice {



    /**
     * 进行redis资源的关闭操作
     * @param org 运行的bean
     * @param method
     * @param objects
     * @param returnValue
     * @throws Throwable
     */
    public void afterReturning(Object org, Method method, Object[] objects, Object returnValue) throws Throwable {

        if (org instanceof RedisPool && !method.getName().contains("close")) {
            RedisPool pool = (RedisPool) org;
            pool.close();
        }

    }
}
