package Engine.main.Objects;

import Engine.main.Game;
import Engine.main.Handler;
import Engine.main.ID;

import java.awt.*;

public class Bullet extends GameObject{

    private Handler handler;
    private float speed = 10;

    public Bullet(float x, float y, ID id, Handler handler, int mx, int my) {
        super(x, y, id);

        float diffx = mx - x;
        float diffy = my - y;
        float distance = (float) Math.sqrt((mx - x)*(mx - x) + (my - y)*(my - y));

        velX = ((1/distance) * diffx*speed);
        velY = ((1/distance) * diffy*speed);

        this.handler = handler;
    }//    velX = ((-1/distance) * diffx*speed);

    @Override
    public void tick() {
        x += velX;
        y += velY;

        if(y <= 0 || y >= Game.mapHEIGHT-10) handler.removeObject(this);
        if(x <= 0 || x >= Game.mapWIDTH-5) handler.removeObject(this);

        handler.addObject(new Trail((int)x,(int)y,ID.trail,Color.yellow,5,5, 0.1f,handler));

    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.yellow);
        g.fillRect((int)x,(int)y,5,5);
    }

    @Override
    public Rectangle getBounds() {
        return null;
    }
}
