package ui;

public class Fire {
    FlyObject fly;

    public Fire(Plane plane) {
        fly = new FlyObject();
        fly.setImage("fire.png");
        fly.setX(plane.fly.getX()+plane.fly.getImage().getWidth()/2 - fly.getImage().getWidth()/4/2);
        fly.setY(plane.fly.getY() - fly.getImage().getHeight()/2);

    }
}
