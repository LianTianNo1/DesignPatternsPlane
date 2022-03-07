package ui;

import java.awt.*;
import java.awt.image.BufferedImage;

public class FlyObject {
    private int x;
    private int y;
    private BufferedImage image;

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = GetImg.getImg(image);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
