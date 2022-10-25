package Engine.main;

import Engine.main.Objects.GameObject;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.security.Key;

public class KeyInput extends KeyAdapter {
    private Handler handler;
    private int currentPressed;
    private boolean[] keyDown = new boolean[4];
    private Game game;

    public KeyInput(Handler handler, Game game){
        this.handler = handler;
        this.game = game;
        keyDown[0] = false;
        keyDown[1] = false;
        keyDown[2] = false;
        keyDown[3] = false;

    }

    @Override
    public void keyPressed(KeyEvent e){
        int key = e.getKeyCode();
        currentPressed = key;

        for(int i = 0; i < handler.objects.size(); i++){
            GameObject tempObject = handler.objects.get(i);

            if(tempObject.getId() == ID.player){
                if(key == KeyEvent.VK_W){ tempObject.setVelY(-5); keyDown[0] = true;}
                if(key == KeyEvent.VK_S){ tempObject.setVelY(5); keyDown[1] = true;}
                if(key == KeyEvent.VK_A){ tempObject.setVelX(-5); keyDown[2] = true;}
                if(key == KeyEvent.VK_D){ tempObject.setVelX(5); keyDown[3] = true;}
            }
        }

        if(key == KeyEvent.VK_P){
            if(game.gameState == Game.STATE.Game){
                if(Game.paused) Game.paused = false;
                else Game.paused = true;
            }
        }
        if(key == KeyEvent.VK_ESCAPE) System.exit(1);

    }

    public void keyReleased(KeyEvent e){
        int key = e.getKeyCode();

        for(int i = 0; i < handler.objects.size(); i++){
            GameObject tempObject = handler.objects.get(i);

            if(tempObject.getId() == ID.player){
                //moves player object
                if(key == KeyEvent.VK_W) keyDown[0] = false;//tempObject.setVelY(0);
                if(key == KeyEvent.VK_S) keyDown[1] = false;//tempObject.setVelY(0);
                if(key == KeyEvent.VK_A) keyDown[2] = false;//tempObject.setVelX(0);
                if(key == KeyEvent.VK_D) keyDown[3] = false;//tempObject.setVelX(0);

                //vertical movement
                if(!keyDown[0] && !keyDown[1]) tempObject.setVelY(0);
                //horizontal movement
                if(!keyDown[2] && !keyDown[3]) tempObject.setVelX(0);


                if(currentPressed == key && key == KeyEvent.VK_UP)tempObject.setY(tempObject.getY()-50);
                if(currentPressed == key && key == KeyEvent.VK_DOWN)tempObject.setY(tempObject.getY()+50);
                if(currentPressed == key && key == KeyEvent.VK_RIGHT)tempObject.setX(tempObject.getX()+50);
                if(currentPressed == key && key == KeyEvent.VK_LEFT)tempObject.setX(tempObject.getX()-50);
                }
            }
        }
    }