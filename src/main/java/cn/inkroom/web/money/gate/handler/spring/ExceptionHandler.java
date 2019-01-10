package cn.inkroom.web.money.gate.handler.spring;

import cn.inkroom.web.money.gate.dto.ctv.MessageDto;
import cn.inkroom.web.money.gate.enums.Result;
import cn.inkroom.web.money.gate.utils.http.RequestUtil;
import cn.inkroom.web.money.gate.utils.http.ResponseUtil;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ExceptionHandler extends SimpleMappingExceptionResolver {
    @Override
    protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        if (RequestUtil.isAjax(request)) {
            try {
                ResponseUtil.outJson(response, new MessageDto(Result.EXCEPTION).toString());
            } catch (Exception e) {
            }
            return null;
        } else {
            return new ModelAndView("");
        }
    }
}
