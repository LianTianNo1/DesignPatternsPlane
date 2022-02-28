package ui;

import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame {
    public  Frame() throws HeadlessException{
        setSize(512,768);
        setTitle("飞机大战");
        setLocationRelativeTo(null);
        setResizable(false);//        不可拖动
        setDefaultCloseOperation(EXIT_ON_CLOSE);//        退出选项
    }
}
