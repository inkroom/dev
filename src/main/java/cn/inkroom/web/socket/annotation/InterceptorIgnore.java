package cn.inkroom.web.socket.annotation;

import org.springframework.web.servlet.HandlerInterceptor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.ArrayList;

/**
 * 指定是否被指定拦截器拦截
 * <p>可用于Controller和映射方法</p>
 * Created by 墨盒 on 16:20.
 */

@Target({ElementType.METHOD, ElementType.TYPE})
//必须有这个，否则无法获取到注解信息
@Retention(RetentionPolicy.RUNTIME)
public @interface InterceptorIgnore {

    Class<? extends HandlerInterceptor>[] values();
}
