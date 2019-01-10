package cn.inkroom.software.background;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;

import java.awt.*;
import java.io.File;
import java.util.Timer;

import static java.awt.Frame.ICONIFIED;

/**
 * @author
 */
public class MainForm {
    private JPanel root;
    private JTextField pathText;
    private JButton button1;
    private JSpinner timeSpinner;
    private JButton btn;

    private Timer timer = null;

    public MainForm(String dir, Long sec) {
        SpinnerNumberModel model = new SpinnerNumberModel();
        model.setValue(sec);
        model.setMaximum(3600);
        model.setMinimum(1);
        timeSpinner.setModel(model);


        pathText.setText(dir);


        btn.addActionListener(e -> {
            if (pathText.getToolTipText().equals(pathText.getText())) {
                JOptionPane.showMessageDialog(null, "请选择壁纸文件夹");
                return;
            }
            Long interval = Long.parseLong(String.valueOf(timeSpinner.getValue()));
            if (interval <= 0) {
                JOptionPane.showMessageDialog(null, "请选择正确的间隔时间");
                return;
            }
            if (timer != null) {
                timer.cancel();
                timer = null;
                btn.setText("启动");
            } else {
                if (!new File(pathText.getText()).exists()) {
                    JOptionPane.showMessageDialog(null, "文件夹不存在");
                    return;
                }
                ChangeTimer changeTimer = new ChangeTimer(pathText.getText());
                timer = new Timer();
                timer.schedule(changeTimer, 0, interval * 1000);

                btn.setText("停止");
            }
        });
        button1.addActionListener(e -> {
            JFileChooser chooser = new JFileChooser(pathText.getText(), FileSystemView.getFileSystemView());
            chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

            if (chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                pathText.setText(chooser.getSelectedFile().getAbsolutePath());
            } else {
                pathText.setText(pathText.getToolTipText());
            }
        });


        if (System.getProperty("os.name").contains("Linux")) {
            btn.doClick();
        }
    }

    public static void main(String[] args) {

        try {
            UIManager.setLookAndFeel(javax.swing.plaf.nimbus.NimbusLookAndFeel.class.getName());//还可以
//            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        JFrame frame = new JFrame("壁纸切换");

        String dir = "";
        Long sec = 5L;

        if (args != null && args.length == 2) {
            dir = args[0];
            sec = Long.parseLong(args[1]);
        }


        frame.setContentPane(new MainForm(dir, sec).root);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(new Dimension(600, 300));
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setMinimumSize(frame.getSize());


        if (System.getProperty("os.name").contains("Linux")) {
            frame.setState(ICONIFIED);
        }
    }
}
