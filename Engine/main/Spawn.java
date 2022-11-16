package Engine.main;

import Engine.main.Objects.Enemy;
import Engine.main.Objects.SmartEnemy;

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
            handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH),r.nextInt(Game.HEIGHT),ID.smartEnemy,handler));

        }

    }
    public void render(Graphics g){

    }

}
