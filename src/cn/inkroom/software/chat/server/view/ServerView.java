package cn.inkroom.software.chat.server.view;

import cn.inkroom.software.chat.common.util.Base64;
import cn.inkroom.software.chat.common.util.DefaultMessageReader;
import cn.inkroom.software.chat.common.util.MessageReader;
import cn.inkroom.software.chat.common.util.StringUtil;
import cn.inkroom.software.chat.server.helper.ServerSocketHelper;
import cn.inkroom.software.chat.server.listener.ClientListener;
import cn.inkroom.software.chat.server.menu.Popmenu;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;

/**
 * Created by 墨盒 on 16:29.
 * 客户端界面
 */
public class ServerView extends JFrame implements ClientListener, ActionListener {
    private JTextPane area;
    private JList<String> list;
    private DefaultListModel<String> model;
    private MessageReader reader;

    private ServerSocketHelper helper;

    public ServerView() {
        area = new JTextPane();
        model = new DefaultListModel<>();
        list = new JList<>(model);

        list.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON3 && list.getSelectedIndex() != -1) {
                    Popmenu popmenu = new Popmenu(ServerView.this);
                    popmenu.show(list, e.getX(), e.getY());
                }
            }
        });

        area.setEditable(false);

        JScrollPane left = new JScrollPane(area);
        JScrollPane right = new JScrollPane(list);

        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.add(left);
        contentPanel.add(right, BorderLayout.EAST);

        this.setContentPane(contentPanel);
        this.setSize(800, 500);
        this.setLocationRelativeTo(null);
        this.setTitle("聊天室服务器端");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        ServerView view = new ServerView();
        view.setReader(new DefaultMessageReader());
        view.setVisible(true);
        ServerSocketHelper helper = new ServerSocketHelper(25524);
        helper.setListener(view);

        view.setHelper(helper);
    }

    @Override
    public void connect(String ip) {
        try {
            area.getStyledDocument().insertString(area.getStyledDocument().getLength(), ip + " 加入聊天室\n", null);
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
        model.addElement(ip);
        list.setModel(model);
    }

    public void setHelper(ServerSocketHelper helper) {
        this.helper = helper;
    }

    @Override
    public void disconnect(String ip) {
        try {
            area.getStyledDocument().insertString(area.getStyledDocument().getLength(), ip + " 退出聊天室\n", null);
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
        model.removeElement(ip);
        list.setModel(model);
    }

    @Override
    public void message(String value) {
        helper.sendMessage(value);
        if (reader != null) {
            reader.init(value);
            String s[] = StringUtil.getAllElement(reader.getMessage());
            appendText(reader.getSender() + " 发送消息：\n\t");
            for (String str : s) {
                ImageIcon icon;
                int length = area.getStyledDocument().getLength();
                if ((icon = Base64.isBase64(str)) != null) {
                    area.setCaretPosition(length);
                    area.insertIcon(icon);
                } else {
                    appendText(str);
//                        area.getStyledDocument().insertString(area.getStyledDocument().getLength(), str, null);
                }
            }
            appendText("\n");
//                area.getStyledDocument().insertString(area.getStyledDocument().getLength(), "\n", null);
        }
    }

    public void setReader(MessageReader reader) {
        this.reader = reader;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("掉线")) {
            if (helper != null) {
                int index = list.getSelectedIndex();
                if (helper.close(model.get(index))) {
                    JOptionPane.showMessageDialog(this, "关闭连接成功！");

                    appendText(model.get(index) + " 被强制离线");
                    model.remove(index);
                    list.setModel(model);
                }

            }
        }
    }

    private void appendText(String value) {
        StyledDocument document = area.getStyledDocument();
        try {
            document.insertString(document.getLength(), value, null);
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
    }
}
