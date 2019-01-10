package cn.inkroom.software.atm.view;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import cn.inkroom.software.atm.controller.Controller;
import cn.inkroom.software.atm.util.StringUtil;

import javax.swing.*;
public class MainView extends BaseView implements ActionListener {
    public MainView(Controller core) {
        super(core);
        JPanel content = new JPanel(null) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(new ImageIcon("E:\\娱乐\\图片\\k\\k1.png").getImage(), 0, 0, null);
            }
        };

        JButton[] buttons = new JButton[6];
        int i = 0;
        buttons[i++] = new JButton("取款");
        buttons[i++] = new JButton("余额");
        buttons[i++] = new JButton("存款");
        buttons[i++] = new JButton("转账");
        buttons[i++] = new JButton("取卡");
        buttons[i++] = new JButton("退出");
        for (i = 0; i < buttons.length; i++) {
            buttons[i].setActionCommand(buttons[i].getText());
            buttons[i].addActionListener(this);
            if (i < buttons.length / 2) {
                buttons[i].setBounds(30, i * 40 + 20, 80, 30);
            } else {
                //		System.out.println(i+"  sdds  "+i/buttons.l);
                buttons[i].setBounds(240, (i - buttons.length / 2) * 40 + 20, 80, 30);
            }
            content.add(buttons[i]);
        }
        this.setContentPane(content);
        init();
        this.setSize(getWidth() - 50, 200);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "存款":
                String value = JOptionPane.showInputDialog(this, "请输入存款金额");
                if (value != null) {
                    Float f_value = StringUtil.parseFloat(value);
                    if (f_value != null) {
                        getCore().save(f_value);
                    }
                }
                break;
            case "取款":
                value = JOptionPane.showInputDialog(this, "请输入取款金额");
                if (value != null) {
                    Float f_value = StringUtil.parseFloat(value);
                    if (f_value != null) {
                        getCore().withdraw(f_value);
                    }
                }
                break;
            case "余额":
                showMessage("余额为：" + getCore().showMoney());
                break;
            case "转账":
                String account = JOptionPane.showInputDialog(this, "请输入转账账号");
                if (account != null) {
                    String money = JOptionPane.showInputDialog(this, "请输入转账金额");
                    if (money != null) {
                        Float f_money = StringUtil.parseFloat(money);
                        if (f_money != null) {
                            getCore().transfer(account, f_money);
                        } else {
                            showMessage("输入错误！");
                        }
                    }
                }
                break;
            case "取卡":
                break;
            case "退出":
                break;
            default:
                showMessage(e.getActionCommand());
                break;
        }
    }

}
