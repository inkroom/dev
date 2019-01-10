package cn.inkroom.web.xu.hai.qiong;

import java.sql.*;

/**
 * Created by 许海群 on 2017/5/11.
 */
public class DataBase {
    public boolean register(String user,String password)
    {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            try {
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/inkbox?useUnicode=true&amp;characterEncoding=utf-8&amp;useSSL=true&amp;useJDBCCompliantTimezoneShift=true&amp;useLegacyDatetimeCode=false&amp;serverTimezone=UTC","inkbox","inkbox");

                Statement statement = connection.createStatement();
              int count = statement.executeUpdate("insert into users values('"+user+"','"+password+"')");
                statement.close();
                connection.close();
                return count==1;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }
    public boolean login(String user,String password)
    {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            try {
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/inkbox?useUnicode=true&amp;characterEncoding=utf-8&amp;useSSL=true&amp;useJDBCCompliantTimezoneShift=true&amp;useLegacyDatetimeCode=false&amp;serverTimezone=UTC","inkbox","inkbox");

                Statement statement = connection.createStatement();
                ResultSet count = statement.executeQuery("select * from users where userName='"+user+"'AND password='"+password+"'");
                boolean result;
                if (count.next()){
                    result=true;
                }else{
                    result=false;
                }

                statement.close();
                connection.close();
                count.close();
                return result;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }
}
