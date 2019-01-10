package cn.inkroom.software.chat.client.view;

import cn.inkroom.software.chat.client.listener.MessageListener;
import cn.inkroom.software.chat.client.helper.ClientSocketHelper;
import cn.inkroom.software.chat.common.util.Base64;
import cn.inkroom.software.chat.common.util.DefaultMessageReader;
import cn.inkroom.software.chat.common.util.MessageReader;
import cn.inkroom.software.chat.common.util.StringUtil;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.StyleConstants;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

/**
 * Created by 墨盒 on 16:08.
 * 客户端聊天界面
 */
public class ClientView extends JFrame implements MessageListener {

    private ClientSocketHelper helper;

    private JTextPane area;
    private JTextPane msg;

    private JButton sendBtn;
    private MessageReader reader;

    public ClientView() {
        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);

        area = new JTextPane();
        msg = new JTextPane();
        area.setBorder(BorderFactory.createEmptyBorder());
        msg.setBorder(BorderFactory.createLineBorder(Color.black));
        JButton addImgBtn = new JButton("添加图片");
        sendBtn = new JButton("发送");

        area.setEditable(false);
        sendBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (helper != null && !msg.getText().equals("")) {
                    ArrayList<String> pic = new ArrayList<>();
                    for (int i = 0; i < msg.getStyledDocument().getRootElements()[0].getElement(0).getElementCount(); i++) {
                        if (msg.getStyledDocument().getCharacterElement(i).getName().equals("icon")) {
                            Icon icon = StyleConstants.getIcon(msg.getStyledDocument().getCharacterElement(i).getAttributes());
                            if (icon != null) {
                                pic.add(icon.toString());
                            }
                        }
                    }
                    int k = 0;
                    StringBuilder builder = new StringBuilder();
                    for (int i = 0; i < msg.getText().length(); i++) {
                        if (msg.getStyledDocument().getCharacterElement(i).getName().equals("icon")) {
                            builder.append(" <img>");
                            builder.append(Base64.getImageStr(pic.get(k++)));
                            builder.append("</img>");
//                            System.out.println(i + "  插入图==" + msg.getStyledDocument().getCharacterElement(i));
//                            System.out.println(StyleConstants.getIcon(msg.getStyledDocument().getCharacterElement(i).getAttributes()).toString());
                        } else {
                            try {
                                builder.append(" <text>");
//                                msg.getStyledDocument().get
                                builder.append(msg.getStyledDocument().getText(i, 1));
                                builder.append("</text>");
                            } catch (BadLocationException e1) {
                                e1.printStackTrace();
                            }
//                            System.out.println(msg.getStyledDocument().getCharacterElement(i));
//                            builder.append(msg.getStyledDocument().getCharacterElement(i).getAttributes())
                        }
                    }
                    System.out.println(msg.getText());
                    System.out.println(builder.toString().replaceAll("</text> <text>", ""));
//                    System.out.println(msg.getText());
//                                          System.out.println(msg.getStyledDocument().getRootElements()[0].getElement(0));
//                                          System.out.println(msg.getStyledDocument().getRootElements()[0].getElement(0).getElement(0).getAttributes());
                    helper.sendMessage(builder.toString().replaceAll("</text> <text>", ""),0);
                    msg.setText("");
                }
            }
        });

        addImgBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser("E:\\娱乐\\图片");
                fileChooser.setMultiSelectionEnabled(true);
                if (fileChooser.showOpenDialog(ClientView.this) == JFileChooser.APPROVE_OPTION) {
                    File imgs[] = fileChooser.getSelectedFiles();
                    for (int i = 0; i < imgs.length; i++) {
                        msg.setCaretPosition(msg.getDocument().getLength());
                        msg.insertIcon(new ImageIcon(imgs[i].getAbsolutePath()));
                    }
                }
            }
        });

//        JScrollPane scrollPane = new JScrollPane(area);

        JPanel bottomPanel = new JPanel(new BorderLayout());
        JPanel contentPanel = new JPanel(new BorderLayout());

        JPanel buttonGroup = new JPanel();
        buttonGroup.add(addImgBtn);
        buttonGroup.add(sendBtn);

        JScrollPane scrollPane = new JScrollPane(msg);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        bottomPanel.add(scrollPane);
        bottomPanel.add(buttonGroup, BorderLayout.SOUTH);
//        bottomPanel.add(addImgBtn);
//        bottomPanel.add(sendBtn);


        splitPane.add(new JScrollPane(area));
        splitPane.add(bottomPanel);
//        splitPane.add(new JScrollPane(bottomPanel));

//        contentPanel.add(scrollPane);
//        contentPanel.add(bottomPanel, BorderLayout.SOUTH);

        contentPanel.add(splitPane);

        this.setContentPane(contentPanel);
        this.setSize(800, 500);
        this.setLocationRelativeTo(null);
        this.setTitle("聊天室");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        ClientView view = new ClientView();
        view.setVisible(true);
        view.setReader(new DefaultMessageReader());
        ClientSocketHelper helper = new ClientSocketHelper("127.0.0.1", 25524, view);
//        helper.setMessageListener(view);

        view.setHelper(helper);
    }

    public void setReader(MessageReader reader) {
        this.reader = reader;
    }

    @Override
    public void message(String value) {
//        area.append(value + "\n");
//        if (reader != null) {
//            Document document = area.getDocument();
//            try {
//                document.insertString(document.getLength(), reader.getMessage() + "\n", null);
//            } catch (BadLocationException e) {
//                e.printStackTrace();
//            }
////            area.append(reader.getMessage() + "\n");
//        }
        if (reader != null) {
            reader.init(value);
            if (reader.getType() == 0) {//一般消息
                try {
                    String s[] = StringUtil.getAllElement(reader.getMessage());
                    area.getStyledDocument().insertString(area.getStyledDocument().getLength(), reader.getSender() + " 发送消息：\n\t", null);
                    for (String str : s) {
                        ImageIcon icon;
                        if ((icon = Base64.isBase64(str)) != null) {
                            area.setCaretPosition(area.getDocument().getLength());
                            area.insertIcon(icon);
                        } else {
                            area.getStyledDocument().insertString(area.getStyledDocument().getLength(), str, null);
                        }
                    }
                    area.getStyledDocument().insertString(area.getStyledDocument().getLength(), "\n", null);
                } catch (BadLocationException e) {
                    e.printStackTrace();
                }
            } else if (reader.getType() == 1) {//退出消息
                helper.stop();
                sendBtn.setEnabled(false);
                JOptionPane.showMessageDialog(this, "服务器要求掉线！");
            }

        }
    }

    @Override
    public void sendFail(String value) {
        JOptionPane.showMessageDialog(this, "消息发送失败！");
    }

    @Override
    public void sendSuccess(String value) {
        Document document = area.getDocument();
        try {
            document.insertString(document.getLength(), "我：" + value + "\n", null);
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void disconnect(Throwable e) {
        if (e.getMessage().equals("Connection refused: connect")) {
            JOptionPane.showMessageDialog(this, "连接服务器失败！");
            sendBtn.setEnabled(false);
            this.setTitle("聊天室——未连接");
        } else {
            JOptionPane.showMessageDialog(this, "与服务器连接断开！");
            sendBtn.setEnabled(false);
            this.setTitle("聊天室——连接断开");
        }
        System.out.println("这里是客户端，断开连接 " + e.toString());
    }


    public void setHelper(ClientSocketHelper helper) {
        this.helper = helper;
    }
}
