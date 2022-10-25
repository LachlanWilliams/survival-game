package Engine.main;

import Engine.main.Objects.GameObject;
import Engine.main.Objects.Player;

import java.awt.*;
import java.util.LinkedList;

public class Handler {

    public LinkedList<GameObject> objects = new LinkedList<GameObject>();
    private Game game;

    public Handler(Game game){
        this.game = game;
    }

    public void tick(){
        for(int i = 0; i < objects.size(); i++){
            GameObject tempObject = objects.get(i);

            tempObject.tick();
            //tick all
        }

    }
    public void render(Graphics g){
        for(int i = 0; i < objects.size(); i++){
            GameObject tempObject = objects.get(i);

            tempObject.render(g);
            //tick all
        }
    }

    public void addObject(GameObject object){
        this.objects.add(object);
    }

    public void removeObject(GameObject object){
        this.objects.remove(object);
    }

    public void clearObjects(){
        objects.clear();
    }

    public void clearEnemys(){
        for(int i = 0; i < objects.size(); i++){
            GameObject tempObject = objects.get(i);

            if(objects.get(i).getId() == ID.player){
                objects.clear();
                if(game.gameState != Game.STATE.End){
                    addObject(new Player((int) tempObject.getX(),(int) tempObject.getY(), ID.player,this));

                }
            }
        }
    }
}
