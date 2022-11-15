package Engine.main.Objects;

import Engine.main.Handler;
import Engine.main.ID;

import java.awt.*;

public class Bullet extends GameObject{

    private Handler handler;

    public Bullet(float x, float y, ID id, Handler handler) {
        super(x, y, id);

        velX = 10;
        velY = 10;

        this.handler = handler;
    }

    @Override
    public void tick() {

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
