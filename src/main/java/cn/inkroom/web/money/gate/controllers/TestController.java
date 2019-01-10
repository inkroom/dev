package cn.inkroom.web.money.gate.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class TestController {

    @Autowired
    private HttpServletRequest request;

    @RequestMapping("test")
    public String test(int p1,String p2){
        System.out.println(p1);
        System.out.println(p2);
        return "index";
    }

}
