package ui;

public class MiddleFire extends Fire{
    public MiddleFire(Plane plane) {
        super(plane);
    }

    @Override
    void move() {
        fly.setY(fly.getY()-10);
    }
}
