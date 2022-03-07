package ui;

import java.util.Random;

public class EnemyPlane {
    FlyObject fly;

    public EnemyPlane(){
        fly = new FlyObject();
        Random random = new Random();
        int index = random.nextInt(15) + 1;
        String path = index<10 ? "0"+index : ""+index;
        String name = "ep" + path + ".png";
        fly.setImage(name);
        fly.setX(random.nextInt(512-fly.getImage().getWidth()));
        fly.setY(0);
    }
}
