package Engine.main.Objects.PowerUps;

import Engine.main.Game;
import Engine.main.Handler;
import Engine.main.ID;
import Engine.main.Objects.GameObject;
import Engine.main.SpriteSheet;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Bomb extends GameObject {

    Handler handler;
    private BufferedImage bomb_image;

    public Bomb(float x, float y, Handler handler) {
        super(x, y, ID.bomb);

        this.handler = handler;

        SpriteSheet ss = new SpriteSheet(Game.sprite_sheet);
        bomb_image = ss.getImage(3,3,32,32);

    }

    @Override
    public void tick() {
        collision();
    }

    @Override
    public void render(Graphics g) {
        //g.setColor(Color.orange);
        //g.fillRect((int)x,(int)y,16,16);
        g.drawImage(bomb_image,(int)x,(int)y,null);

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
