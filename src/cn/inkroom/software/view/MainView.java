package cn.inkroom.software.view;

import cn.inkroom.software.connect.FtpConnect;
import cn.inkroom.software.entity.FtpFile;
import sun.applet.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URLEncoder;
import java.util.ArrayList;

/**
 * Created by 墨盒 on 16:11.
 */
public class MainView extends JFrame {
    private JList<String> list;
    private JTextField pathField;
    private ArrayList<FtpFile> files;

    public MainView() {
        list = new JList<>();
//        list.setBackground(Color.black);
//        listModel = new DefaultListModel<>();

//        list.setModel(listModel);
        list.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    if (files != null) {
                        FtpFile ftpFile = files.get(list.getSelectedIndex());
                        if (ftpFile.isFile()) {
                            int result = JOptionPane.showConfirmDialog(MainView.this, "是否下载文件" + ftpFile.getName());
                            if (result == JOptionPane.OK_OPTION) {
                                JFileChooser fileChooser = new JFileChooser();
                                result = fileChooser.showSaveDialog(MainView.this);
                                if (result == JOptionPane.OK_OPTION) {
                                    FtpConnect connect = new FtpConnect(ftpFile.getPath());
                                    connect.download(fileChooser.getSelectedFile().getAbsolutePath());
                                }
                            }
                        } else {//目录
                            init(ftpFile.getPath());
                        }
                    }
                }
            }
        });

        JPanel topPanel = new JPanel(new BorderLayout());
        pathField = new JTextField();
        topPanel.add(pathField, BorderLayout.CENTER);
        JButton back = new JButton("返回");
        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!pathField.getText().equals("ftp://ftp.cs.nsu.edu.cn")) {
                    init(pathField.getText().substring(0, pathField.getText().lastIndexOf("/")));
                }
            }
        });
        topPanel.add(back, BorderLayout.WEST);
//        JScrollPane scrollPane = new JScrollPane(list);
        init("ftp://ftp.cs.nsu.edu.cn//2014项目管理报名");
        JPanel content = new JPanel(new BorderLayout());
        content.add(new JScrollPane(list), BorderLayout.CENTER);
        content.add(topPanel, BorderLayout.NORTH);
        this.setContentPane(content);
        this.setTitle("FTP");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setSize(600, 400);
    }

    public void init(String path) {
        files = new FtpConnect(path).getFileList();
        DefaultListModel<String> listModel = new DefaultListModel<>();
        for (FtpFile file : files) {
            listModel.addElement(file.getName());
        }
        list.setModel(listModel);
        pathField.setText(path);
//        System.out.println("size = "+listModel.size());
    }

    public static void main(String[] args) {
        MainView mainView = new MainView();
        mainView.setVisible(true);
//        mainView.init();
    }
}
