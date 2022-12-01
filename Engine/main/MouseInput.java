package Engine.main;

import Engine.main.Objects.Bullet;
import Engine.main.Objects.GameObject;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseInput extends MouseAdapter {
    private Handler handler;
    private Game game;
    private Map map;
    private GameObject player;

    public MouseInput(Handler handler, Game game, Map map){
        this.handler = handler;
        this.game = game;
        this.map = map;

    }

    public void mousePressed(MouseEvent e){
        int mx = e.getX();
        int my = e.getY();

        // all buttons for menu
        if(game.gameState == Game.STATE.Game){
            findPlayer();
            handler.addObject(new Bullet(player.getX()+13, player.getY()+12,handler, mx, my));

            //handler.addObject(new Bullet());
        }
        if(game.gameState == Game.STATE.Menu){
            // play button
            if(mouseOver(mx, my, 210, 150, 200, 64)){
                game.gameState = Game.STATE.Game;
                map.play();
            }
            // help button
            if (mouseOver(mx, my, 210, 250, 200, 64)){
                game.gameState = Game.STATE.Help;
            }
            // Quit button
            if(mouseOver(mx, my, 210, 350, 200, 64)){
                System.exit(1);

            }

        }

        // all buttons for help
        if(game.gameState == Game.STATE.Help){
            if(mouseOver(mx, my, 210, 350, 200, 64)){
                game.gameState = Game.STATE.Menu;
                return;
            }
        }

        if(game.gameState == Game.STATE.End){
            if(mouseOver(mx, my, 210, 350, 200, 64)){
                game.gameState = Game.STATE.Menu;
                map.hudReset();
                return;
            }
        }

    }

    public void mouseReleased(MouseEvent e){

    }

    public boolean mouseOver(int mx, int my, int x , int y, int width, int height){
        if(mx > x && mx < x + width){
            if(my > y && my < y + height){
                return true;
            }else return false;
        }else return false;
    }

    public void findPlayer(){
        for(int i = 0; i < handler.objects.size();i++){
            if(handler.objects.get(i).getId() == ID.player){
                player = handler.objects.get(i);
            }
        }
    }
}
