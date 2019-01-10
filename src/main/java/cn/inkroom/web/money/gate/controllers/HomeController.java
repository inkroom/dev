package cn.inkroom.web.money.gate.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class HomeController {

    protected Logger log = LoggerFactory.getLogger(getClass());
    @Autowired
    private HttpServletRequest request;

    @RequestMapping("test")
    public String test(int p1,String p2){
        log.info(request.getClass().toString());
        log.info(p1+"");
        log.info(p2);
        return "index";
    }


    @RequestMapping("home")
    public String home(){
        return "home";
    }

    @RequestMapping("under")
    public String under(){
        return "under";
    }
}
