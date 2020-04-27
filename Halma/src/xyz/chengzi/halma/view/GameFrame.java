package xyz.chengzi.halma.view;

import xyz.chengzi.halma.controller.GameController;
import xyz.chengzi.halma.model.ChessBoard;

import javax.swing.*;

public class GameFrame extends JFrame {
    public GameFrame() {
        setTitle("11班2组project");
        setSize(776, 810);
        setLocationRelativeTo(null); // Center the window.窗口在屏幕中间
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);//关闭窗口后停止运行程序
        setLayout(null);

        JLabel statusLabel = new JLabel("版本号：4m21a");
        statusLabel.setLocation(0, 760);
        statusLabel.setSize(200, 10);
        add(statusLabel);

        JButton button = new JButton("我是一个在右下角，宽300高12的按钮");
        button.addActionListener((e) -> JOptionPane.showMessageDialog(
                this, "按钮被单击了！"));
        button.setLocation(460, 760);
        button.setSize(300, 12);
        add(button);
    }
}
