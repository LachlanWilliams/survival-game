package Engine.main.Objects;

import Engine.main.Game;
import Engine.main.Handler;
import Engine.main.ID;

import java.awt.*;

public class Enemy extends GameObject {

    private Handler handler;

    public Enemy(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        velX = 5;
        velY = 5;

        this.handler = handler;
    }

    public Rectangle getBounds(){return new Rectangle((int)x,(int)y,16,16);}

    @Override
    public void tick() {
        x += velX;
        y += velY;

        if(y <= 0 || y >= Game.HEIGHT-32) velY *= -1;
        if(x <= 0 || x >= Game.WIDTH-16) velX *= -1;

        handler.addObject(new Trail((int)x,(int)y,ID.trail,Color.red,16,16, 0.1f,handler));
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.red);
        g.fillRect((int)x,(int)y,16,16);

    }
}
