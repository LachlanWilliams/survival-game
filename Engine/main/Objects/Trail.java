package Engine.main.Objects;

import Engine.main.Game;
import Engine.main.HUD;
import Engine.main.Handler;
import Engine.main.ID;

import java.awt.*;

public class Trail extends GameObject {

    private float alpha = 1;

    Handler handler;
    private Color color;

    private float width;
    private float height;
    private float life;

    public Trail(float x, float y, ID id, Color color, float width, float height, float life, Handler handler) {
        super(x, y, id);
        this.handler = handler;
        this.color = color;
        this.height = height;
        this.width = width;
        this.life = life;
    }

    private AlphaComposite makeTransparent(float alpha){
        int type = AlphaComposite.SRC_OVER;
        return(AlphaComposite.getInstance(type,alpha));
    }

    public Rectangle getBounds(){return null;}

    @Override
    public void tick() {
        if(alpha > life){
            alpha -= life - 0.01;
        }else handler.removeObject(this);

    }

    private void collision(){
        for(int i = 0; i < handler.objects.size(); i++){
            GameObject tempObject = handler.objects.get(i);

            if(tempObject.getId() == ID.enemy){
                if(getBounds().intersects(tempObject.getBounds())){
                    HUD.HEALTH -= 2;

                }
            }
        }
    }

    @Override
    public void render(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        g2d.setComposite(makeTransparent(alpha));

        g.setColor(color);
        g.fillRect((int)x,(int)y,16,16);

        g2d.setComposite(makeTransparent(1));

    }
}
