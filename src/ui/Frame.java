package ui;

import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame {
    private  Frame() throws HeadlessException{
        setSize(512,768);
        setTitle("飞机大战");
        setLocationRelativeTo(null);
        setResizable(false);//        不可拖动
        setDefaultCloseOperation(EXIT_ON_CLOSE);//        退出选项
    }

    private static class HolderClass{
        private static final Frame instance= new Frame();
    }

    public static Frame getInstance(){
        return  HolderClass.instance;
    }
}
