package Engine.main.Objects;

import Engine.main.Game;
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
        g.setColor(Color.GRAY);
        g.fillRect((int)x,(int)y, Game.WIDTH, Game.HEIGHT-22);

    }

    @Override
    public Rectangle getBounds() {
        return null;
    }
}
