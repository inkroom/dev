package cn.inkroom.software.atm.view;

import cn.inkroom.software.atm.controller.Controller;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class LoginView extends BaseView {

    private JTextField account;
    private JPasswordField password;


    public LoginView(Controller core) {
        super(core);

        JPanel content = new JPanel(new GridLayout(3, 3, 10, 10));

        account = new JTextField();
        password = new JPasswordField();

        JLabel accountLabel = new JLabel("账号：");
        accountLabel.setHorizontalAlignment(JLabel.CENTER);

        JLabel passwordLabel = new JLabel("密码：");
        passwordLabel.setHorizontalAlignment(JLabel.CENTER);

        JButton login = new JButton("登录");
        JButton exit = new JButton("退出");

        login.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String s_account = account.getText();
                String s_password = new String(password.getPassword());
                if (s_account.equals("") || s_password.equals("")) {
                    showMessage("账号或密码不能为空！");
                    return;
                }
                if (core != null)
                    core.login(s_account, s_password);
            }
        });
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (core != null) {
                    core.exit();
                }
            }
        });

        content.add(accountLabel);
        content.add(account);
        content.add(passwordLabel);
        content.add(password);
        content.add(login);
        content.add(exit);

        this.setContentPane(content);
        init();
        this.setSize(240, 130);
    }


}
