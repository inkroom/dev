package cn.inkroom.software.atm.view;

import javax.swing.*;

import cn.inkroom.software.atm.controller.Controller;

public class BaseView extends JFrame {

    private Controller core;

    public BaseView(Controller core) {
        this.core = core;
    }

    public void showMessage(String msg) {
        JOptionPane.showMessageDialog(this, msg);
    }

    protected void init() {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(400, 300);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setTitle("ATM");
    }

    public void close() {
        this.setVisible(false);
        this.dispose();
    }

    protected Controller getCore() {
        return core;
    }
}
