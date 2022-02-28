package ui;

public class Plane {
    public FlyObject fly;

    public Plane(){
        fly = new FlyObject();
//        fly.setImage("hero.png");
        fly.setImage(GetImg.getImg("hero.png"));
        fly.setX(200);
        fly.setY(500);
    }
}
