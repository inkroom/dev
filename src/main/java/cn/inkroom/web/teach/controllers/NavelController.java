package cn.inkroom.web.teach.controllers;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Controller
public class NavelController {

    @RequestMapping("read")
    public String read(String content, String prev, String next, String title, String url, HttpServletRequest request) {


        ServletContext context = request.getServletContext();

        if (content == null || prev == null || next == null || title == null || url == null) {
            try {
                return "redirect:read?content=" + URLEncoder.encode(context.getAttribute("content").toString(), "utf-8") + "&prev="
                        + URLEncoder.encode(context.getAttribute("prev").toString(), "UTF-8")
                        + "&next=" + URLEncoder.encode(context.getAttribute("next").toString(), "UTF-8")
                        + "&url=" + context.getAttribute("url")
                        + "&title=" + URLEncoder.encode(context.getAttribute("title").toString(), "utf-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }

        String c = null;
        String pUrl = null;
        String nUrl = null;
        String nTitle = null;
        try {

            Document document = Jsoup.connect(url).get();


            context.setAttribute("content", content);
            context.setAttribute("prev", prev);
            context.setAttribute("next", next);
            context.setAttribute("title", title);
            context.setAttribute("url", url);
//            System.out.println("html = "+document.html());


            //获取文章内容
            c = document.select(content).get(0).html();
            //获取上一章url
            pUrl = document.select(prev).get(0).absUrl("href");
            //获取下一章url
            nUrl = document.select(next).get(0).absUrl("href");
            //获取文章标题
            nTitle = document.select(title).get(0).html();
        } catch (IOException e) {
            e.printStackTrace();
        }


        request.setAttribute("title", nTitle);
        request.setAttribute("content", c);
        request.setAttribute("pUrl", pUrl);
        request.setAttribute("nUrl", nUrl);
//        request.setAttribute("");

        return "navel";

    }


    @RequestMapping("src")
    public String src(String url, HttpServletRequest request) {


        try {
            Document document = Jsoup.connect(url).get();

            request.setAttribute("src", document.html());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "src";


    }

}
