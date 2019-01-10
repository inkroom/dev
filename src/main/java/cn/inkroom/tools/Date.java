package cn.inkroom.tools;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.*;

public class Date {
    public static void main(String[] args) {


        try {
            BufferedReader reader = new BufferedReader(new FileReader(new File("/media/inkbox/study/three.js")));
            BufferedWriter writer = new BufferedWriter(new FileWriter(new File("/media/inkbox/study/three.min.js"),true));

            String line;
            while ((line = reader.readLine())!=null){
                line = line.trim();
                if (line.equals("\n")||line.contains("//")){
                    continue;
                }
                writer.write(line);
            }
            reader.close();
            writer.close();


        } catch (IOException e) {
            e.printStackTrace();
        }


//        try {
//            Document document = Jsoup.connect("https://wannianrili.51240.com/").get();
//
//
//            JsonArray array = new JsonArray();
//            Elements elements= document.select(".wnrl_k_zuo>div");
//            for (int i = 0; i < elements.size(); i++) {
//                //上个月或下个月的空白日历
//                if (elements.eq(i).hasClass("wnrl_kongbai")){
//                    JsonObject temp = new JsonObject();
//                    array.add(temp);
//                }else if (elements.eq(i).hasClass("wnrl_riqi")){
//                    JsonObject temp = new JsonObject();
//                    temp.addProperty("day",elements.eq(i).select(".wnrl_td_gl").get(0).html());
//                    temp.addProperty("small",elements.eq(i).select(".wnrl_td_bzl").get(0).html());
//
//                    array.add(temp);
//                }
//            }
//            System.out.println(array.toString());
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}
