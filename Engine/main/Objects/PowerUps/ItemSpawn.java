package Engine.main.Objects.PowerUps;

import Engine.main.Game;
import Engine.main.HUD;
import Engine.main.Handler;
import Engine.main.Objects.SmartEnemy;

import java.awt.*;
import java.util.Random;

public class ItemSpawn {
    private Handler handler;
    private HUD hud;
    private Random r = new Random();

    private float levelTrack = 10;
    private int rInterval = r.nextInt(5);

    public ItemSpawn(Handler handler, HUD hud){
        this.handler = handler;
        this.hud = hud;

    }

    public void tick(){
        int level = (int) hud.getLevel();

        if ((rInterval+levelTrack)==level){
            System.out.println(hud.getLevel());
            handler.addObject(new Bomb(r.nextInt(Game.mapWIDTH),r.nextInt(Game.mapHEIGHT),handler));

            rInterval = r.nextInt(5);
            levelTrack += 10;

        }

    }
    public void render(Graphics g){

    }

}
