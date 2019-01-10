package cn.inkroom.software.crow.net;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.script.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Calendar;
import java.util.Scanner;

/**
 * @author 墨盒
 * @version 1.0
 * @Date 2017/11/21
 * @Time 14:16
 * @Descorption
 */
public class Http {

    private String sessionId;

    public String sendGet(String url, String values, boolean getSession) {
        StringBuilder result = new StringBuilder(url);
        result.append("\t");
        BufferedReader in = null;
        try {

            String urlNameString = url;
            if (values != null && !"".equals(values)) {
                urlNameString = url + "?" + values;
            }
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            HttpURLConnection connection = (HttpURLConnection) realUrl.openConnection();

            if (this.sessionId != null && !this.sessionId.equals("")) {//如果sessionID存在，即存在会话
                System.out.println("GET设置session= " + this.sessionId + "   url = " + url);
                connection.setRequestProperty("cookie", this.sessionId);
                connection.setRequestProperty("Cookie", sessionId);
            }
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("Charsert", "GBK"); //设置请求编码
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:57.0) Gecko/20100101 Firefox/57.0");

            connection.setRequestProperty("Referer","http://dean.nsu.edu.cn/jxjh/Stu_byfakc.aspx");

            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段

//            Map<String, List<String>> map = connection.getHeaderFields();
//            // 遍历所有的响应头字段
//            for (String key : map.keySet()) {
//                System.out.println(key + "--->" + map.get(key));
//            }
//
//            sessionId = getSessionID(connection);
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream(), "GBK"));
            String line;
            while ((line = in.readLine()) != null) {
                result.append(line);
//                result.append(new String(line.getBytes(),"GBK"));
            }
            if (getSession)
                this.sessionId = getSessionID(connection);
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
//            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result.toString();
//        try {
//            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
//
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        return "";
    }

    /**
     * 向指定 URL 发送POST方法的请求
     *
     * @param url   发送请求的 URL
     * @param param 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return 所代表远程资源的响应结果
     */
    public String sendPost(String url, String param) {
        PrintWriter out = null;
        BufferedReader in = null;
        StringBuilder result = new StringBuilder(url);
        result.append("\t");
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            HttpURLConnection conn = (HttpURLConnection) realUrl.openConnection();

            if (this.sessionId != null && !this.sessionId.equals("")) {//如果sessionID存在，即存在会话
                System.out.println("POST设置session = " + this.sessionId + "   url = " + url);
                conn.setRequestProperty("cookie", this.sessionId);
                conn.setRequestProperty("Cookie", sessionId);
            }
            //临时cookie
//            conn.setRequestProperty("Cookie", "UM_distinctid=15fbd6d46c31ea-0eb9fe92ba6f5a8-173b7740-15f900-15fbd6d46c43c3; ASP.NET_SessionId=cbdbxwieu4u1i04520uplub0");

            conn.setRequestProperty("Upgrade-Insecure-Requests", "1");

            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("Charsert", "GBK"); //设置请求编码
            conn.setRequestProperty("User-Agent",
                    "Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:57.0) Gecko/20100101 Firefox/57.0");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestProperty("Referer", url);
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);

            conn.setRequestProperty("Referer",url);

            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();


            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream(), "GBK"));
            String line;
            while ((line = in.readLine()) != null) {
                result.append(line);
//                result.append(new String(line.getBytes(),"GBK"));
            }
//            this.sessionId = getSessionID(conn);
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！" + e);
//            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result.toString();
    }

    public String getSessionID(HttpURLConnection connection) {
        String sessionID;
        try {
            String cookieValue = connection.getHeaderField("set-cookie");
            if (cookieValue != null) {
                sessionID = cookieValue.substring(0, cookieValue.indexOf(";"));
            } else {
                sessionID = "";
            }
        } catch (Exception e) {
            e.printStackTrace();
            sessionID = "";
        }
        System.out.println("session  = " + sessionID + "   url = " + connection.getURL().toString());
        return sessionID;
    }


    public String md5(String value) {
        try {
            ScriptEngineManager manager = new ScriptEngineManager();
            ScriptEngine engine = manager.getEngineByName("nashorn");
            engine.eval(new FileReader("E:\\study\\实验室\\school.js"));
            return ((Invocable) engine).invokeFunction("md5", value).toString().toLowerCase();
        } catch (ScriptException | FileNotFoundException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {

        Http h = new Http();

        String html = h.sendGet("http://dean.nsu.edu.cn/_data/login.aspx", "", true);
        Document document = Jsoup.parse(html, "http://dean.nsu.edu.cn/_data/login.aspx");
        String token = document.getElementsByAttributeValue("name", "__VIEWSTATE").val();
        System.out.println("token = " + token);

        //下载验证码
        h.download("http://dean.nsu.edu.cn/sys/ValidateCode.aspx", "");
        Scanner scanner = new Scanner(System.in);
        String code = scanner.nextLine();

        //发送登陆请求
        html = h.sendPost("http://dean.nsu.edu.cn/_data/login.aspx",
                "__VIEWSTATE=" + token + "" +
                        "&pcInfo=Mozilla/5.0+(Windows+NT+6.1;+Win64;+x64;+rv:57.0)+Gecko/20100101+Firefox/57.0Windows+NT+6.1;+Win64;+x645.0+(Windows)+SN:NULL" +
                        "&typeName=%D1%A7%C9%FA" +
                        "&dsdsdsdsdxcxdfgfg=E3683149CB04283CE89941B3849321" +
                        "&fgfggfdgtyuuyyuuckjg=" + h.md5(code).toUpperCase() +
                        "&Sel_Type=STU" +
                        "&txt_asmcdefsddsd=15310320108" +
                        "&txt_pewerwedsdfsdff=" +
                        "&txt_sdertfgsadscxcadsads=");

        System.out.println("登陆结果\n" + html);

//        System.out.println(h.sendGet("http://dean.nsu.edu.cn/MAINFRM.aspx", "", false));
        html = h.sendGet("http://dean.nsu.edu.cn/jxjh/Stu_byfakc_rpt.aspx", "", false);
        document = Jsoup.parse(html, "http://dean.nsu.edu.cn/jxjh/Stu_byfakc_rpt.aspx");
        System.out.println(html);
        System.out.println(h.sendGet("http://dean.nsu.edu.cn/MAINFRM.aspx", "", false));
//        Element table = document.getElementById("tableReportMain");
//        Calendar c = Calendar.getInstance();
//        Elements trs = table.getElementsByTag("tr");
        float score = 0;
//        for (int i = 0; i < trs.size(); i++) {
//            Element tr = trs.get(i);
//            Elements tds = tr.getElementsByTag("td");
//
//
//            //获取是哪一学期
//            String term = tds.get(1).html();
//            if (!"".equals(term)) {
//                if (term.trim().startsWith(String.valueOf(c.get(Calendar.YEAR)))) {
//                    if (term.trim().contains((c.get(Calendar.MONTH) >= 8 || c.get(Calendar.MONTH) <= 1) ? "一" : "二")) {
//                        break;
//                    }
//                }
//            }
//            //获取学分
//            score += Float.valueOf(tds.get(3).html());
//
//        }
        System.out.println("未挂科则获取学分  " + score);
    }

    public void get(String number) {
        this.sendGet("http://100.0.0.32:9090/reader/login.jsp", "", true);
        String result = (this.sendPost("http://100.0.0.32:9090/reader/login.jsp?str_kind=login", "barcode=" + number + "&fangshi=0&password=666666&x=11&y=9"));
        if (result.contains("alert")) {
            System.out.println("学号=" + number + "\t登陆失败");
            return;
        }
        String html = this.sendGet("http://100.0.0.32:9090/reader/infoList.jsp", "", false);

//        System.out.println(this.sendGet("http://100.0.0.32:9090/reader/infoList.jsp", "", false));
        //        System.out.println(this.sendGet("http://100.0.0.32:9090/reader/login.jsp?str_kind=login",""));


        Document document = Jsoup.parse(html, "http://100.0.0.32:9090/reader/infoList.jsp");
//        Elements elements = document.getElementsByTag("table");
        Elements elements = document.getElementsByClass("topictitle2");
        if (elements.size() > 0) {
            Element element = elements.get(0).nextElementSibling();
            Elements trs = element.getElementsByTag("tr");
            write("学号=" + number + "\t借书" + (trs.size() - 3) + "本");
            for (int i = 2; i < trs.size() - 1; i++) {
                Element tr = trs.get(i);
                Elements tds = tr.getElementsByTag("td");

                System.out.println("学号=" + number +
                        "        书名=" + tds.get(1).html().replaceAll("&nbsp;", "")
                        + "     应还日期=" + tds.get(4).html().replaceAll("&nbsp;", ""));
                write("书名=" + tds.get(1).html().replaceAll("&nbsp;", "")
                        , "应还日期=" + tds.get(4).html().replaceAll("&nbsp;", ""));
            }
        } else {
            System.out.println("学号=" + number + "\t借书0本");
            write("学号=" + number + "\t借书0本");
        }


    }

    public void write(String... values) {
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("D:\\5.txt", true), "utf-8"));
            for (String value : values) {
                writer.write(value.toCharArray());
//                writer.write(new String(value.getBytes(), "utf-8"));
                writer.newLine();
            }
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null)
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }

    public String download(String url, String suffix) {


        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            HttpURLConnection conn = (HttpURLConnection) realUrl.openConnection();

            if (this.sessionId != null && !this.sessionId.equals("")) {//如果sessionID存在，即存在会话
                //                System.out.println("GET设置session= " + this.sessionId + "   url = " + url);
                conn.setRequestProperty("cookie", this.sessionId);
                conn.setRequestProperty("Cookie", sessionId);
            }
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("Charsert", "GBK"); //设置请求编码
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");

            FileOutputStream out = new FileOutputStream("D:\\1.jpg");
            InputStream input = conn.getInputStream();
            int length = 0;
            byte bytes[] = new byte[2 * 1200];
            while ((length = input.read(bytes)) > 0) {
                out.write(bytes, 0, length);
            }
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    @Override
    public String toString() {


        return super.toString();
    }
}
