package cn.inkroom.web.teach.controllers;

import cn.inkroom.web.teach.bean.Person;
import cn.inkroom.web.teach.services.TestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;

/**
 * Created by 墨盒 on 17:14.
 */
@Controller
public class First {

    Logger log = LoggerFactory.getLogger(getClass());


    @Autowired
    private HttpServletRequest request;
    @Autowired
    private HttpServletResponse response;
    @Autowired
    private TestService service;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(@RequestParam(value = "one", required = false, defaultValue = "-1") int show,
                        @RequestParam(defaultValue = "two") String two) {
        System.out.println("单个Url，单个method");
        System.out.println("one = " + show + "  two = " + two);

        System.out.println("协议为：" + request.getProtocol());
        System.out.println("header : = " + response.getHeader("Content-Type"));

        System.out.println(service.test());
        return "first";
    }

    @RequestMapping(value = "/valid", method = RequestMethod.GET)
    public String index(@Valid Person person, BindingResult result) {
        System.out.println(person);
        System.out.println(result);
        request.setAttribute("result", result);
        return "first";
    }

    @RequestMapping(value = "test")
    @ResponseBody
    public String json() {
        return "{\"test\":\"中文and English\"}";
    }

    @RequestMapping("shutdown")
    @ResponseBody
    public String shutdown() {
        try {
            Runtime.getRuntime().exec("cmd shutdown -p");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}
