package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class Panel extends JPanel {
    BufferedImage bg;
    Plane plane;

    public Panel(Frame frame) {
        bg = GetImg.getImg("bg5.jpg");
        plane = new Plane();

//        鼠标的事件
        MouseAdapter mouseAdapter = new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                super.mouseMoved(e);
//                把鼠标的坐标给飞机
                plane.fly.setX(e.getX() - plane.fly.getImage().getWidth() / 2);
                plane.fly.setY(e.getY() - plane.fly.getImage().getHeight() / 2);
//                重复的画
                repaint();
            }
        };
//        监听鼠标事件
        addMouseListener(mouseAdapter);
        addMouseMotionListener(mouseAdapter);

//        键盘监听事件
        KeyAdapter keyAdapter = new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
//                监听左
                if(e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_LEFT){
                    plane.fly.setX(plane.fly.getX() - 10);
//                    边界判断
                    if(plane.fly.getX() < 0){
                        plane.fly.setX(0);
                    }
                } else if(e.getKeyCode() == KeyEvent.VK_D || e.getKeyCode() == KeyEvent.VK_RIGHT){
                    plane.fly.setX(plane.fly.getX() + 10);
//                    边界判断
                    if(plane.fly.getX() > bg.getWidth()){
                        plane.fly.setX(bg.getWidth() - plane.fly.getImage().getWidth()/2);
                    }
                }else if(e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_UP){
                    plane.fly.setY(plane.fly.getY() - 10);
//                    边界判断
                    if(plane.fly.getY() < 0){
                        plane.fly.setY(0);
                    }
                }else if(e.getKeyCode() == KeyEvent.VK_S || e.getKeyCode() == KeyEvent.VK_DOWN){
                    plane.fly.setY(plane.fly.getY() + 10);
//                    边界判断
                    if(plane.fly.getY() > bg.getHeight()){
                        plane.fly.setY(bg.getHeight() - plane.fly.getImage().getHeight()/2);
                    }
                }
//                重绘
                repaint();
            }
        };
        frame.addKeyListener(keyAdapter);
    }

    @Override
    public void print(Graphics g) {
        super.print(g);
        g.drawImage(bg, 0, 0, null); // 画背景
        g.drawImage(plane.fly.getImage(), plane.fly.getX(), plane.fly.getY(), null); //画我方飞机
    }
}
