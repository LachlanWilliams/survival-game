package Engine.main;

import Engine.main.Objects.Enemy;

import java.awt.*;
import java.util.Random;

public class Spawn {

    private Handler handler;
    private HUD hud;
    private Random r = new Random();

    private float levelTrack = 0;

    public Spawn(Handler handler, HUD hud){
        this.handler = handler;
        this.hud = hud;

    }

    public void tick(){
        if (levelTrack != hud.getLevel()){
            levelTrack = hud.getLevel();
            handler.addObject(new Enemy(r.nextInt(Game.WIDTH),r.nextInt(Game.HEIGHT),ID.enemy,handler));

        }

    }
    public void render(Graphics g){

    }

}
