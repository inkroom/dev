package cn.inkroom.web.money.gate.aop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

public class ControllerAop implements AfterReturningAdvice {
    private Logger log = LoggerFactory.getLogger(getClass());

    @Override
    public void afterReturning(Object returnValue, Method method, Object[] objects, Object org) throws Throwable {


        String url = getUrl(org);
        StringBuilder builder = new StringBuilder("url:");
        if (url != null) {
            builder.append(url);
        } else {
            builder.append("无法获取");
        }

        builder.append(",参数为：{");

        Parameter[] parameterTypes = method.getParameters();

//        method.getParameters()[0].getName()
        for (int i = 0; i < objects.length; i++) {
//            builder.append(parameterTypes[i].get);
//            builder.append(":");
//            builder.append(objects[i]);


            builder.append("\"");
            builder.append(objects[i]);
            builder.append("\"");
            if (i != objects.length - 1)
                builder.append(",");
        }
        builder.append("}");
        if (returnValue != null) {
            builder.append(",返回值:");
            builder.append(returnValue.toString());
//            builder.append(returnValue.getClass());
        }
        log.info(builder.toString());
    }


    private String getUrl(Object org) {
        //获取所有成员变量
        try {
            Field fields[] = org.getClass().getFields();
            for (Field field : fields) {
                if (field.getDeclaringClass().equals(HttpServletRequest.class)) {
                    return ((HttpServletRequest) field.get(org)).getRequestURL().toString();
                }
            }

            fields = org.getClass().getDeclaredFields();
            for (Field field : fields) {
                if (field.getDeclaringClass().equals(HttpServletRequest.class)) {
                    return ((HttpServletRequest) field.get(org)).getRequestURL().toString();
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;

    }
}
