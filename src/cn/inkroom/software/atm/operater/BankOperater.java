package cn.inkroom.software.atm.operater;

import cn.inkroom.software.atm.entity.Account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by Administrator on 17:37.
 * 账户操作
 */
public class BankOperater {

    private Connection connection;
    private String error;

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public Boolean withdraw(float value, Account account) {
        if (account.getMoney() > value) {
            return newValue(account.getMoney() - value, account);
        } else {
            return false;
        }
    }

    public Boolean save(float value, Account account) {
        if (value > 0) {
            return newValue(account.getMoney() + value, account);
        } else {
            return false;
        }
    }

    private Boolean newValue(float value, Account account) {
        try {
            PreparedStatement ps = connection.prepareStatement("UPDATE account SET rest = ? WHERE account.id = ?");
            ps.setFloat(1, value);
            ps.setInt(2, account.getId());
            int count = ps.executeUpdate();
            ps.close();
            return count == 1;
        } catch (SQLException e) {
//                e.printStackTrace();
            setError(e.toString().substring(e.toString().indexOf(":") + 1));
            return null;
        }
    }

    public Boolean transfer(String get, float value, Account account) {
        try {
            connection.setAutoCommit(false);
            PreparedStatement ps = connection.prepareStatement("UPDATE account SET rest = account.rest + ? WHERE account = ?");
            ps.setFloat(1, value);
            ps.setString(2, get);
            int count = ps.executeUpdate();
//            ps.executeUpdate("UPDATE account SET rest = account.rest+" + value + " WHERE account = " + get);
            if (count == 1) {
                ps = connection.prepareStatement("UPDATE account SET rest = account.rest - ? WHERE account = ?");
                ps.setFloat(1, value);
                ps.setString(2, account.getAccount());
                count = ps.executeUpdate();
                if (count != 1) {
                    connection.rollback();
                    ps.close();
                    return false;
                }
                ps.close();
                return true;
            } else {
                connection.rollback();
                ps.close();
                return false;
            }


//            ps.executeUpdate("UPDATE account SET rest = rest - " + value + " WHERE account = " + account.getAccount());
//            Statement("UPDATE account SET rest = account.rest+? WHERE account = ?;" +
//                    "UPDATE account SET rest = rest - ? WHERE account = ?");

//            ps.setFloat(1, value);
//            ps.setString(2, get);
//            ps.setFloat(3, value);
//            ps.setString(4, account.getAccount());
//            int count = ps.executeUpdate();


//            System.out.println("受影响行数：" + count);
//            connection.commit();
//            connection.setAutoCommit(true);
//            ps.close();
//            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
                return false;
            } catch (SQLException e1) {
                setError(e1.toString().substring(e.toString().indexOf(":") + 1));
                return null;
            }
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
//        return true;
    }

    public String getError() {
        return error;
    }

    private void setError(String error) {
        this.error = error;
    }
}
