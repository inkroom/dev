package cn.inkroom.web.teach.filters;

import cn.inkroom.web.teach.controllers.VideoController;
import cn.inkroom.web.teach.interceptors.Inc;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author 墨盒
 * @version 1.0
 * @Date 2017/9/20
 * @Time 21:36
 * @Descorption
 */
public class PathFilter implements Filter {
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        String file = s;
//        HttpServletRequest request = (HttpServletRequest) servletRequest;
//        System.out.println("pathFilter = " + request.getRequestURL().toString() + "  " + request.getRequestURI());
//        String path = Inc.getRealPath(request.getParameter("file"),
//                servletRequest.getParameter("hidden") == null ? false : Boolean.valueOf(servletRequest.getParameter("hidden")));
//        servletRequest.setAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE, path.replace(VideoController.BASE_PATH, ""));
//        System.out.println("PathFilter = " + servletRequest.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE));
        filterChain.doFilter(servletRequest, servletResponse);
    }

    public void destroy() {

    }
}
