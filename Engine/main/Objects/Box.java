package Engine.main.Objects;

import Engine.main.ID;

import java.awt.*;

public class Box extends GameObject{

    public Box(float x, float y, ID id) {
        super(x, y, id);
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.ORANGE);
        g.fillRect((int)x,(int)y, 64, 64);

    }

    @Override
    public Rectangle getBounds() {
        return null;
    }
}
