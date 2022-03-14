package ui;

public class RightFire extends Fire{
    public RightFire(Plane plane) {
        super(plane);
    }

    @Override
    void move() {
        fly.setY(fly.getY()-10);
        fly.setX(fly.getX()+10);
    }
}
