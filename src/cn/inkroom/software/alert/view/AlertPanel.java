package cn.inkroom.software.alert.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Calendar;

/**
 * Created by 墨盒 on 17:11.
 */
public class AlertPanel extends JPanel {


    private int OX = 0;
    private int OY = 0;
    private int radius = 200;

    private int length = radius / 3;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.draw3DRect(0,0,getWidth()-2,getHeight()-2,true);
//        radius = Math.min(getWidth(), getHeight()) / 2 - 10;

        int originX = (getWidth() - radius) / 2;
        int originY = (getHeight() - radius) / 2;

        OX = originX + radius / 2;
        OY = originY + radius / 2;

//        this.addMouseListener(new MouseAdapter() {
//            public void mouseClicked(MouseEvent e) {
//                System.out.println("x = " + e.getX() + "  y=" + e.getY());
//            }
//        });
        g.drawOval(originX, originY, radius, radius);

//        System.out.println("OX = " + OX + "  OY = " + OY + "  originX = " + originX + "  originY = " + originY);
        int sec = Calendar.getInstance().get(Calendar.SECOND);
//        System.out.println("x = " + (Math.sin((Math.PI / 30) * sec) * length + OX));
//        System.out.println("y= " + (Math.cos((Math.PI / 30) * sec) * length + OY));
//
//        System.out.println("sec = " + sec + "   sin = " + Math.sin((Math.PI / 30) * sec) + "   cos = " + Math.cos((Math.PI / 30) * sec));

//        g.translate(OX,OY);

//        int length = 30;
        g.drawLine(OX, OY, (int) (Math.sin((Math.PI / 30) * sec) * length + OX), (int) (-Math.cos((Math.PI / 30) * sec) * length + OY));
    }


}
