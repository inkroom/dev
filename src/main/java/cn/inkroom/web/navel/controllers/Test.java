package cn.inkroom.web.navel.controllers;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by 墨盒 on 16:59.
 */
@Controller
public class Test {

    @RequestMapping("/navel")
    public String index(String url, HttpServletRequest request, String content, String prev, String next, String title) {
        try {
            Document document = Jsoup.connect(url).get();
            request.setAttribute("title", document.getElementById(title).html());
            request.setAttribute("content", document.getElementById(content).html());

            request.setAttribute("prev", document.getElementById(prev).absUrl("href"));
            request.setAttribute("next", document.getElementById(next).absUrl("href"));

            request.setAttribute("prevId", prev);
            request.setAttribute("nextId", next);
            request.setAttribute("contentId", content);
            request.setAttribute("titleId", title);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "index";
    }

    @RequestMapping("/download")
    public String download(final String url, HttpServletRequest request, final String content, String next, final String title) {
        final File file = new File(request.getServletContext().getRealPath("/"), title + ".txt");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Download download = new Download();
        download.setFile(file);
        download.setContent(content);
        download.setTitle(title);
        download.setNext(next);
        download.setUrl(url);
        new Thread(download).start();
        return "message";
    }
}
