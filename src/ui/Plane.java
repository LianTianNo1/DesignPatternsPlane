package ui;

public class Plane {
    public FlyObject fly;

    private Plane(){
        fly = new FlyObject();
        fly.setImage("hero.png");
        fly.setX(200);
        fly.setY(500);
    }

    private static class HolderClass{
        private static final Plane instance = new Plane();
    }

    public static Plane getInstance(){
        return HolderClass.instance;
    }
}
