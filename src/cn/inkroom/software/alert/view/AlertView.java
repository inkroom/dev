package cn.inkroom.software.alert.view;

import javax.swing.*;
import java.util.*;
import java.util.Timer;

/**
 * Created by 墨盒 on 17:11.
 */
public class AlertView extends JFrame {
    private AlertPanel panel;

    public AlertView() {

        panel = new AlertPanel();

        this.setContentPane(panel);


        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setSize(400, 300);
//        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setTitle("时钟");
    }

    public void notifyTime() {
        panel.repaint();
    }


    public static void main(String[] args) {
        AlertView alertView = new AlertView();
        alertView.setVisible(true);
        java.util.Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                alertView.notifyTime();
            }
        }, 0, 1000);
    }
}
