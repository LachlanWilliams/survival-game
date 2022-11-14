package Engine.main;

import Engine.main.Objects.Box;
import Engine.main.Objects.Player;
import Engine.main.Objects.SmartEnemy;

import java.awt.*;

public class Map {
    private Game game;
    private Handler handler;
    private HUD hud;
    private Spawn spawner;
    private static final int WIDTH = 1000;
    private static final int HEIGHT = 750;


    public Map(Game game, Handler handler,HUD hud){
        this.game = game;
        this.handler = handler;
        this.hud = hud;

        spawner = new Spawn(handler,hud);
    }

    public void play(){
        // this method will run when changing from menu to game
        //TODO: attach game clamp to this
        // this will run when the game is initialised
        // Connect this to the game mode
        // Ajust clamp to fit the map
        // basically take care of most of the
        //handler.addObject(new Box(0,0,ID.box));
        handler.addObject(new Player(100,100,ID.player,handler));
        handler.addObject(new SmartEnemy(250,250,ID.smartEnemy,handler));
    }

    public void pause(){
        //TODO: this could augment a status that tick would check
    }

    public void hudReset(){
        hud.reset();
    }

    public void display(Graphics g){
        g.setColor(Color.GRAY);
        g.fillRect(0,0, WIDTH, HEIGHT-22);
    }

    public void tick(){
        hud.tick();
        spawner.tick();
    }

    public void render(Graphics g){
        // TODO: make the map here
        hud.render(g);
    }

    public static int getHEIGHT() {
        return HEIGHT;
    }

    public static int getWIDTH() {
        return WIDTH;
    }
}
