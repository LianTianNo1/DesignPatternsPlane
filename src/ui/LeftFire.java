package ui;

public class LeftFire extends Fire{
    public LeftFire(Plane plane) {
        super(plane);
    }

    @Override
    void move() {
        fly.setY(fly.getY()-10);
        fly.setX(fly.getX()-10);
    }
}
