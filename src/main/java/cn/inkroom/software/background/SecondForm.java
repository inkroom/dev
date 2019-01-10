package cn.inkroom.software.background;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.Timer;

import static java.awt.Frame.ICONIFIED;

/**
 * @author
 */
public class SecondForm implements ChangeListener {
    private JButton btn;
    private JTextField current;
    private JTextField dir;
    private JSpinner timeSpinner;
    private JPanel root;
    private JPanel top;
    private JPanel butoom;

    private Timer timer;

    public SecondForm(String path, int sec) {
        SpinnerNumberModel model = new SpinnerNumberModel();
        model.setValue(sec);
        model.setMaximum(3600);
        model.setMinimum(1);
        timeSpinner.setModel(model);


        dir.setText(path);

        dir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JFileChooser chooser = new JFileChooser(dir.getText(), FileSystemView.getFileSystemView());
                chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

                if (chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                    dir.setText(chooser.getSelectedFile().getAbsolutePath());
                } else {
                    dir.setText(dir.getToolTipText());
                }


            }
        });
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (dir.getToolTipText().equals(dir.getText())) {
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
                    if (!new File(dir.getText()).exists()) {
                        JOptionPane.showMessageDialog(null, "文件夹不存在");
                        return;
                    }
                    ChangeTimer changeTimer = new ChangeTimer(dir.getText());
                    changeTimer.setListener(SecondForm.this);
                    timer = new Timer();
                    timer.schedule(changeTimer, 0, interval * 1000);

                    btn.setText("停止");
                }
            }
        });
        dir.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JFileChooser chooser = new JFileChooser(dir.getText(), FileSystemView.getFileSystemView());
                chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

                if (chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                    dir.setText(chooser.getSelectedFile().getAbsolutePath());
                } else {
                    dir.setText(dir.getToolTipText());
                }


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

        String dir = "";
        int sec = 5;

        if (args != null && args.length == 2) {
            dir = args[0];
            sec = Integer.parseInt(args[1]);
        }

        JFrame frame = new JFrame("壁纸切换");

        frame.setContentPane(new SecondForm(dir, sec).root);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(new Dimension(400, 200));
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);


        if (System.getProperty("os.name").contains("Linux")) {
            frame.setState(ICONIFIED);
        }
    }

    @Override
    public void change(String path) {
        current.setText(path);
    }
}
