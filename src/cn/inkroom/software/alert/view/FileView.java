package cn.inkroom.software.alert.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

/**
 * Created by 墨盒 on 16:59.
 */
public class FileView extends JFrame implements ActionListener {

    private JTextArea area;

    public FileView() {


        area = new JTextArea();

        JScrollPane scrollPane = new JScrollPane(area);

        JPanel jPanel = new JPanel(new BorderLayout());
        jPanel.add(scrollPane);

        JButton openFile = new JButton("打开文件");
        JButton saveFile = new JButton("保存文件");
        openFile.setActionCommand("open");
        saveFile.setActionCommand("save");
        openFile.addActionListener(this);
        saveFile.addActionListener(this);

        JPanel group = new JPanel();
        group.add(openFile);
        group.add(saveFile);

        jPanel.add(group, BorderLayout.SOUTH);

        this.setContentPane(jPanel);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setSize(400, 300);
//        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setTitle("文本编辑");
    }

    public static void main(String[] args) {
        new FileView().setVisible(true);
    }

    public void readFile(File path) {

        try {
            BufferedReader reader = new BufferedReader(new FileReader(path));

            String temp;
            while ((temp = reader.readLine()) != null) {
                area.append(temp + "\n");
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeFile(File path) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(path));
            writer.write(area.getText().replaceAll("\n", "\r\n"));
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setMultiSelectionEnabled(false);
        int result;
        switch (e.getActionCommand()) {
            case "open":
                result = fileChooser.showOpenDialog(this);
                if (result == JFileChooser.APPROVE_OPTION) {
                    readFile(fileChooser.getSelectedFile());
                }
                break;
            case "save":
                result = fileChooser.showSaveDialog(this);
                if (result == JFileChooser.APPROVE_OPTION) {
                    writeFile(fileChooser.getSelectedFile());
                }
                break;
        }
    }
}
