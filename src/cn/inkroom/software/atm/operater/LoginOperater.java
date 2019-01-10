package cn.inkroom.software.atm.operater;

import cn.inkroom.software.atm.entity.Account;

import java.sql.*;

/**
 * Created by Administrator on 17:36.
 * 登录操作
 */
public class LoginOperater {

    public Account login(String account, String password, Connection connection) {
        try {
            PreparedStatement ps = connection.prepareStatement("select * from bank.account WHERE account =? AND pwd =? limit 1;");
            ps.setString(1, account);
            ps.setString(2, password);

            ResultSet set = ps.executeQuery();
            if (set.next()) {
                Account ac = new Account();
                ac.setId(set.getInt("id"));
                ac.setAccount(set.getString("account"));
                ac.setPassword(set.getString("pwd"));
                ac.setMoney(set.getFloat("rest"));

                set.close();
                ps.close();
                return ac;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
