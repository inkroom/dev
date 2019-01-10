package cn.inkroom.web.xu.hai.qiong;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by 许海群 on 2017/4/17.
 */
public class Controller  {

    public void show(){
        System.out.println(1234432);
    }



    public void register(HttpServletRequest request, HttpServletResponse response){

        String url="";

        String userName=request.getParameter("phoneNumber");
        String password=request.getParameter("password");
        String sure=request.getParameter("sure");
        if(sure.equals(password)){
         DataBase base =new DataBase();
         boolean result = base.register(userName,password);
         if(result)
         {
             url="/html/youlan_firstpage.html";

         }else{
             url="/html/ask.html";

         }
        }else{
            url="/html/ask.html";

        }
        try {
            response.sendRedirect(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void login(HttpServletRequest request, HttpServletResponse response)
    {
        String phoneNumber=request.getParameter("userName");
        String password=request.getParameter("password");
DataBase  conn=new DataBase();
       boolean res= conn.login(phoneNumber,password);
       if(res)
       {
           try {
               response.sendRedirect("/html/youlan_firstpage.html");
           } catch (IOException e) {
               e.printStackTrace();
           }
       }
       else
       {
           try {
               response.sendRedirect("/html/ask .html");
           } catch (IOException e) {
               e.printStackTrace();
           }
       }

    }
}
