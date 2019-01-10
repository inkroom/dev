package cn.inkroom.web.teach.interceptors;

import cn.inkroom.web.teach.controllers.VideoController;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * @author 墨盒
 * @version 1.0
 * @Date 2017/9/16
 * @Time 13:17
 * @Descorption
 */
public class Inc implements HandlerInterceptor {
    private Resource temp = null;

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        if (o instanceof ResourceHttpRequestHandler) {
            System.out.println("ResourceHttpRequestHandler   url = " + URLDecoder.decode(request.getRequestURI(),"utf-8") );
//            String realPath = getRealPath(URLDecoder.decode(request.getRequestURI(),"utf-8")
//                            .replace("/video/", "").replace("/hidden/", ""),
//                    request.getParameter("hidden") == null ? false : Boolean.valueOf(request.getParameter("hidden")));
            System.out.println("preHandle    " + o);
            ResourceHttpRequestHandler handler = (ResourceHttpRequestHandler) o;
//            handler.getResourceResolvers().get(0).resolveResource()
//            handler.getResourceResolvers().get(0).resolveUrlPath()
            System.out.println(request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE) + "    locations = " + handler.getLocations());
            System.out.println(handler.getLocations().get(0).getClass());
            temp = handler.getLocations().get(0);
//            handler.getLocations().clear();
//            handler.getLocations().add(new UrlResource("file:" + new File(realPath).getAbsolutePath()));
            System.out.println("preHandle    " + o + "    " + handler.getResourceTransformers());
//            System.out.println(handler.getR);
//            handler.handleRequest(request,response);
//            System.out.println("path = " + handler.getLocations().get(0) + URLDecoder.decode(request.getRequestURI().replace("hidden/", ""), "utf-8"));
//            System.out.println(" file path = " + handler.getLocations().get(0).getFile().getAbsolutePath() + URLDecoder.decode(request.getRequestURI().replace("hidden/", ""), "utf-8"));
//            System.out.println(new File(handler.getLocations().get(0).getFile().getAbsolutePath() + URLDecoder.decode(request.getRequestURI().replace("hidden/", ""), "utf-8")).exists());
//            handler.get
        } else if (o instanceof HandlerMethod) {
            System.out.println("HandlerMethod   url = " + request.getRequestURL().toString());
        }

        return true;
    }

    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        if (o instanceof ResourceHttpRequestHandler) {
            System.out.println("afterCompletion     " + o);
            ResourceHttpRequestHandler handler = (ResourceHttpRequestHandler) o;
//            handler.getLocations().clear();
//            handler.getLocations().add(temp);
//            new FileInputStream("").
//            new FileWriter().w
//            new FileOutputStream().w
//            httpServletResponse.getOutputStream().write(new File().get);
        }
    }

    public static String getRealPath(String file, boolean hidden) {
        System.out.println(file);
        String[] ns = file.split("/");
        StringBuilder realPath = null;
//        if (hidden) {
//            realPath = new StringBuilder(VideoController.HIDDEN_PATH);
//        } else
//            realPath = new StringBuilder(VideoController.BASE_PATH);
//        for (String n : ns) {
//            System.out.println("n = " + n);
//            File temp = new File(realPath.toString());
//            realPath.append("/" + temp.listFiles()[Integer.parseInt(n)].getName());
//        }
//        return realPath.toString();
        return null;
    }
}
