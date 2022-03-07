package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class Panel extends JPanel {
    BufferedImage bg;
    Plane plane;
    List<EnemyPlane> enemyPlanes = new ArrayList<EnemyPlane>();
    List<Fire> fires = new ArrayList<Fire>();


    //启动一个线程不停地创建一个飞机
    public void action() {
        new Thread(() -> {
            while (true) {
                // 我方子弹和敌机创建
                enCreate();
                fiCreate();
                // 我方子弹和敌机移动
                enMove();
                fiMove();
                // 碰撞检测
                isHit();


                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //休息一段时间重画
                repaint();
            }
        }).start();
    }

    // 碰撞检测
    private void isHit() {
        for (int i = 0; i < enemyPlanes.size(); i++) {
            EnemyPlane enemyPlane = enemyPlanes.get(i);
            for (int j = 0; j < fires.size(); j++) {
                Fire fire = fires.get(j);
                if (fire.fly.getX() + fire.fly.getImage().getWidth() / 4 > enemyPlane.fly.getX() &&
                        enemyPlane.fly.getX() + enemyPlane.fly.getImage().getWidth() > fire.fly.getX() &&
                        fire.fly.getY() < enemyPlane.fly.getY() + enemyPlane.fly.getImage().getHeight()) {
                    enemyPlanes.remove(i);
                    fires.remove(j);
                }
            }
        }
    }

    //创建敌机
    int flag = 0;

    private void enCreate() {
        flag++;
        if (flag >= 20) {
            EnemyPlane enemyPlane = new EnemyPlane();
            enemyPlanes.add(enemyPlane);
            flag = 0;
        }
    }
    int fiFlag = 0;
    private void fiCreate() {
        fiFlag++;
        if (fiFlag >= 5) {
            Fire fire = new Fire(plane);
            fires.add(fire);
            fiFlag = 0;
        }
    }

    private void enMove() {
        //敌机移动
        for (int i = 0; i < enemyPlanes.size(); i++) {
            EnemyPlane enemyPlane = enemyPlanes.get(i);
            enemyPlane.fly.setY(enemyPlane.fly.getY() + 5);
            // 销毁处理
            if (enemyPlane.fly.getY() > 768) {
                enemyPlanes.remove((enemyPlane));
            }
        }
    }
    private void fiMove() {
        // 子弹的移动
        for (int i = 0; i < fires.size(); i++) {
            Fire fire = fires.get(i);
            fire.fly.setY(fire.fly.getY() - 15);
            // 销毁处理
            if (fire.fly.getY() < 0) {
                fires.remove(fire);
            }
        }
    }

    public Panel(Frame frame) {
        bg = GetImg.getImg("bg5.jpg");
        plane = Plane.getInstance();

        // 鼠标的事件
        MouseAdapter mouseAdapter = new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                super.mouseMoved(e);
                // 把鼠标的坐标给飞机
                plane.fly.setX(e.getX() - plane.fly.getImage().getWidth() / 2);
                plane.fly.setY(e.getY() - plane.fly.getImage().getHeight() / 2);
                // 重复的画
                repaint();
            }
        };
        // 监听鼠标事件
        addMouseListener(mouseAdapter);
        addMouseMotionListener(mouseAdapter);

        // 键盘监听事件
        KeyAdapter keyAdapter = new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                // 监听左
                if (e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_LEFT) {
                    plane.fly.setX(plane.fly.getX() - 10);
                    // 边界判断
                    if (plane.fly.getX() - plane.fly.getImage().getWidth() / 2 < 0) {
                        plane.fly.setX(0);
                    }
                } else if (e.getKeyCode() == KeyEvent.VK_D || e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    plane.fly.setX(plane.fly.getX() + 10);
                    if (plane.fly.getX() > bg.getWidth()) {
                        plane.fly.setX(bg.getWidth() - plane.fly.getImage().getWidth());
                    }
                } else if (e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_UP) {
                    plane.fly.setY(plane.fly.getY() - 10);
                    if (plane.fly.getY() < 0) {
                        plane.fly.setY(0);
                    }
                } else if (e.getKeyCode() == KeyEvent.VK_S || e.getKeyCode() == KeyEvent.VK_DOWN) {
                    plane.fly.setY(plane.fly.getY() + 10);
                    if (plane.fly.getY() > bg.getHeight()) {
                        plane.fly.setY(bg.getHeight() - plane.fly.getImage().getHeight());
                    }
                }
                //重绘
                repaint();
            }
        };
        frame.addKeyListener(keyAdapter);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(bg, 0, 0, null); // 画背景
        g.drawImage(plane.fly.getImage(), plane.fly.getX(), plane.fly.getY(), null); //画我方飞机
        //循环画敌方飞机
        for (int i = 0; i < enemyPlanes.size(); i++) {
            EnemyPlane enemyPlane = enemyPlanes.get(i);
            g.drawImage(enemyPlane.fly.getImage(), enemyPlane.fly.getX(), enemyPlane.fly.getY(), null); //画我方飞机
        }
        for (int i = 0; i < fires.size(); i++) {
            Fire fire = fires.get(i);
            g.drawImage(fire.fly.getImage(), fire.fly.getX(), fire.fly.getY(), fire.fly.getImage().getWidth() / 4, fire.fly.getImage().getHeight() / 4, null); //画子弹
        }
    }
}
