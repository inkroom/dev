package cn.inkroom.software.atm.controller;

import cn.inkroom.software.atm.entity.Account;
import cn.inkroom.software.atm.operater.BankOperater;
import cn.inkroom.software.atm.operater.BaseConnect;
import cn.inkroom.software.atm.operater.LoginOperater;
import cn.inkroom.software.atm.view.BaseView;
import cn.inkroom.software.atm.view.LoginView;
import cn.inkroom.software.atm.view.MainView;

import java.sql.Connection;
import java.sql.SQLException;

public class Controller {

    private Connection connection;//数据库连接

    private BaseView view;
    private Account account;

    private BankOperater bankOperater;

    public void login(String account, String password) {
        if (view != null) {
            connection = new BaseConnect().getConnect();
            if (connection == null) {
                view.showMessage("数据库异常！");
                return;
            }
            LoginOperater loginOperater = new LoginOperater();
            Account ac = loginOperater.login(account, password, connection);
            if (ac != null) {
                this.account = ac;
                view.close();

                MainView mainView = new MainView(this);
                mainView.setVisible(true);
                view = mainView;
            } else {
                view.showMessage("账号或密码错误！");
            }
        }
    }

    public void setView(BaseView view) {
        this.view = view;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }


    public void withdraw(float value) {
        if (bankOperater == null) {
            bankOperater = new BankOperater();
        }
        bankOperater.setConnection(this.connection);

        Boolean result = bankOperater.withdraw(value, account);
        if (result != null) {
            view.showMessage("取款" + (result ? "成功" : "失败") + "！");
            if (result)
                getAccount().setMoney(getAccount().getMoney() - value);
        } else {
            view.showMessage(bankOperater.getError());
        }
    }

    public void save(float value) {
        if (bankOperater == null) {
            bankOperater = new BankOperater();
            bankOperater.setConnection(this.connection);
        }

        Boolean result = bankOperater.save(value, account);
        if (result != null) {
            view.showMessage("存款" + (result ? "成功" : "失败") + "！");
            if (result)
                getAccount().setMoney(getAccount().getMoney() + value);
        } else {
            view.showMessage(bankOperater.getError());
        }
    }

    public float showMoney() {
        return account.getMoney();
    }

    public void transfer(String account, float value) {
        if (bankOperater == null) {
            bankOperater = new BankOperater();
            bankOperater.setConnection(this.connection);
        }

        Boolean result = bankOperater.transfer(account, value, getAccount());
        if (result != null) {
            view.showMessage("转账" + (result ? "成功" : "失败") + "！");
            if (result)
                getAccount().setMoney(getAccount().getMoney() - value);

        } else {
            view.showMessage(bankOperater.getError());
        }
    }

    public void give() {
        account = null;
        LoginView loginView = new LoginView(this);
        this.view.close();
        loginView.setVisible(true);
        setView(loginView);
    }
    public void exit(){
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.exit(0);
    }
}
