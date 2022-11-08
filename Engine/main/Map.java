package Engine.main;

import java.awt.*;

public class Map {
    private Game game;
    private Handler handler;
    private HUD hud;

    public Map(Game game, Handler handler, HUD hud){
        this.game = game;
        this.handler = handler;
        this.hud = hud;
    }

    public void play(){
        // this method will run when changing from menu to game
        //TODO: attach game clamp to this
    }

    public void pause(){
        //TODO: this could augment a status that tick would check
    }

    public void tick(){
        hud.tick();
    }
    public void render(Graphics g){
        // TODO: make the map here
        hud.render(g);
    }
}
