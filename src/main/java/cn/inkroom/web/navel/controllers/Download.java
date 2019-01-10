package cn.inkroom.web.navel.controllers;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author 墨盒
 * @version 1.0
 * @Date 2017/9/12
 * @Time 15:22
 * @Descorption
 */
public class Download implements Runnable {
    private int index;
    private BufferedWriter writer;
    private File file;
    private String url, content, next, title;

    public void setUrl(String url) {
        this.url = url;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public void run() {
        try {
            if (writer == null) {
                writer = new BufferedWriter(new FileWriter(file));
            }

            while (true) {
                index++;
                Document document;
                try {
                    document = Jsoup.connect(url).get();
                } catch (Exception e) {
                    break;
                }

                String t = document.getElementById(title).html();
                String n = document.getElementById(content).html()
                        .replaceAll("\\(请搜索八&nbsp;一&nbsp;中&nbsp;文&nbsp;网，更&nbsp;新&nbsp;最&nbsp;快的&nbsp;小说网站\\!\\)","")
                        .replaceAll("&nbsp;", " ").replaceAll("<br */><br */>","\n");

                writer.write("第" + index + "章 " + t);
                writer.newLine();
                writer.write(n);
                writer.newLine();
                url = document.getElementById(next).absUrl("href");
                if (url == null)
                    break;
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
