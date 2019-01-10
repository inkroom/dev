package cn.inkroom.software.chat.server.menu;

import javax.swing.*;
import java.awt.event.ActionListener;

/**
 * Created by 墨盒 on 15:12.
 */
public class Popmenu extends JPopupMenu {
    public Popmenu(ActionListener listener) {
        JMenuItem item = new JMenuItem("掉线");
        item.setActionCommand(item.getText());
        item.addActionListener(listener);
        this.add(item);
    }
}
