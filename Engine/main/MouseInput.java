package Engine.main;

import Engine.main.Objects.Bullet;
import Engine.main.Objects.GameObject;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class MouseInput extends MouseAdapter implements MouseMotionListener {
    private Handler handler;
    private Game game;
    private Map map;
    private GameObject player;

    private int mx;
    private int my;

    private boolean isMousePressed = false;
    private Thread thread;

    public MouseInput(Handler handler, Game game, Map map){
        game.addMouseMotionListener(this);
        this.handler = handler;
        this.game = game;
        this.map = map;

    }

    public void mousePressed(MouseEvent e){
        isMousePressed = true;

        mx = e.getX();
        my = e.getY();

        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (isMousePressed) {
                    // call your function repeatedly here


                    // all buttons for menu
                    if (game.gameState == Game.STATE.Game) {
                        findPlayer();
                        try {
                            thread.sleep(200); // delay for 1000 milliseconds (1 second)
                        } catch (InterruptedException e) {
                            thread.currentThread().interrupt(); // preserve the interrupted status
                            e.printStackTrace();
                        }
                        handler.addObject(new Bullet(player.getX() + 13, player.getY() + 12, handler, mx, my));

                        //handler.addObject(new Bullet());
                    }
                    if (game.gameState == Game.STATE.Menu) {
                        // play button
                        if (mouseOver(mx, my, 210, 150, 200, 64)) {
                            game.gameState = Game.STATE.Game;
                            map.play();
                        }
                        // help button
                        if (mouseOver(mx, my, 210, 250, 200, 64)) {
                            game.gameState = Game.STATE.Help;
                        }
                        // Quit button
                        if (mouseOver(mx, my, 210, 350, 200, 64)) {
                            System.exit(1);

                        }

                    }

                    // all buttons for help
                    if (game.gameState == Game.STATE.Help) {
                        if (mouseOver(mx, my, 210, 350, 200, 64)) {
                            game.gameState = Game.STATE.Menu;
                            return;
                        }
                    }

                    if (game.gameState == Game.STATE.End) {
                        if (mouseOver(mx, my, 210, 350, 200, 64)) {
                            game.gameState = Game.STATE.Menu;
                            map.hudReset();
                            return;
                        }
                    }
                }
            }
        });

        thread.start();

    }

    public void mouseReleased(MouseEvent e){
        isMousePressed = false;
        thread.currentThread().interrupt();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        mx = e.getX();
        my = e.getY();
        // do something with x and y
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
