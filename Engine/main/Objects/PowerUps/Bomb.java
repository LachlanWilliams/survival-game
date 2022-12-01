package Engine.main.Objects.PowerUps;

import Engine.main.HUD;
import Engine.main.Handler;
import Engine.main.ID;
import Engine.main.Objects.GameObject;

import java.awt.*;

public class Bomb extends GameObject {

    Handler handler;

    public Bomb(float x, float y, Handler handler) {
        super(x, y, ID.bomb);

        this.handler = handler;

    }

    @Override
    public void tick() {
        collision();
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.orange);
        g.fillRect((int)x,(int)y,16,16);

    }

    @Override
    public void hurt() {

    }

    @Override
    public Rectangle getBounds(){return new Rectangle((int)x,(int)y,16,16);}

    private void collision(){
        for(int i = 0; i < handler.objects.size(); i++){
            GameObject tempObject = handler.objects.get(i);

            if(tempObject.getId() == ID.player){
                if(getBounds().intersects(tempObject.getBounds())){
                    handler.bomb();
                    handler.removeObject(this);

                }
            }
        }
    }
}
