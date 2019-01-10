package cn.inkroom.web.socket.interceptors;

import cn.inkroom.web.socket.annotation.InterceptorIgnore;
import cn.inkroom.web.socket.config.Contains;
import cn.inkroom.web.socket.config.KeyConfig;
import cn.inkroom.web.socket.util.InterceptorUtil;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.annotation.Annotation;

/**
 * Created by 墨盒 on 16:20.
 */
public class LoginInterceptor implements HandlerInterceptor {
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            if (InterceptorUtil.isIgnore(handlerMethod, this.getClass())) {
                return true;
            }
        }
        if (httpServletRequest.getSession().getAttribute(KeyConfig.KEY_LOGIN) == null) {
            httpServletResponse.sendRedirect(Contains.URL_LOGIN);
            return false;
        }
        return true;
    }

    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
