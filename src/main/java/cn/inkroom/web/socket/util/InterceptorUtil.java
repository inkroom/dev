package cn.inkroom.web.socket.util;

import cn.inkroom.web.socket.annotation.InterceptorIgnore;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;


/**
 * 拦截器工具类
 * Created by 墨盒 on 16:55.
 */
public class InterceptorUtil {
    /**
     * 判断是否应该被指定拦截器拦截
     *
     * @param method 反射
     * @return true则表示应该被拦截器忽略，否则被拦截器拦截处理
     */
    public static boolean isIgnore(HandlerMethod method, Class<? extends HandlerInterceptor> interceptor) {
        Class<? extends HandlerInterceptor> classes[];
        //判断类是否被修饰
        if (method.getBeanType().getAnnotation(InterceptorIgnore.class) != null) {
            classes = method.getBeanType().getAnnotation(InterceptorIgnore.class).values();
            for (Class<? extends HandlerInterceptor> item : classes)
                if (item.equals(interceptor))
                    return true;
        }
        //判断方法是否被修饰
        if (method.getMethodAnnotation(InterceptorIgnore.class) != null) {
            classes = method.getMethodAnnotation(InterceptorIgnore.class).values();
            for (Class<? extends HandlerInterceptor> item : classes)
                if (item.equals(interceptor))
                    return true;
        }
        return false;
    }
}
