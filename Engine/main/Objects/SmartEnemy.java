package Engine.main.Objects;

import Engine.main.Game;
import Engine.main.Handler;
import Engine.main.ID;

import java.awt.*;

public class SmartEnemy extends GameObject {

    private Handler handler;
    private GameObject player;
    private float speed = 2;
    private int health = 3;

    public SmartEnemy(float x, float y, ID id, Handler handler) {
        super(x, y, id);

        this.handler = handler;

        for(int i = 0; i < handler.objects.size();i++){
            if(handler.objects.get(i).getId() == ID.player){
                player = handler.objects.get(i);
            }
        }

    }

    public Rectangle getBounds(){return new Rectangle((int)x,(int)y,16,16);}

    @Override
    public void tick() {
        x += velX;
        y += velY;

        float diffx = x - player.getX()-16;
        float diffy = y - player.getY()-16;
        float distance = (float) Math.sqrt((x - player.getX())*(x - player.getX()) + (y - player.getY())*(y - player.getY()));

        velX = ((-1/distance) * diffx*speed);
        velY = ((-1/distance) * diffy*speed);

        //if(y <= 0 || y >= Game.HEIGHT-32) velY *= -1;
        //if(x <= 0 || x >= Game.WIDTH-16) velX *= -1;

        handler.addObject(new Trail((int)x,(int)y,ID.trail,Color.magenta,16,16, 0.1f,handler));
    }

    public void hurt(){
        health = health-1;
        if(health == 0){
            handler.removeObject(this);
        }
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.magenta);
        g.fillRect((int)x,(int)y,16,16);

    }
}
