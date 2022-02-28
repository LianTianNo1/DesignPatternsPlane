package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Panel extends JPanel {
    BufferedImage bg;
    public Panel(){
        bg = GetImg.getImg("bg5.jpg");
    }

    @Override
    public void print(Graphics g) {
        super.print(g);
        g.drawImage(bg,0,0,null);
    }
}
