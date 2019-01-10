package cn.inkroom.software.atm.operater;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Administrator on 17:31.
 * 连接数据库
 */
public class BaseConnect {

    public Connection getConnect() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank?useUnicode=true&amp;characterEncoding=utf-8&amp;useSSL=true", "inkbox", "inkbox");
            return connection;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
