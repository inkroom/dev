package cn.inkroom.web.teach.services;

import org.springframework.stereotype.Service;

/**
 * Created by 墨盒 on 17:54.
 */
@Service
public class TestService {

    public String test() {
        System.out.println("我是 Service 层");
        return "service层的数据";
    }
}
