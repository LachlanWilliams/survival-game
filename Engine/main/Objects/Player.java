package Engine.main.Objects;

import Engine.main.*;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends GameObject {

    Handler handler;
    private BufferedImage player_image;
    private int objectWidth = 64, objectHeight = 64;

    public Player(int x, int y, Handler handler) {
        super(x, y,ID.player);
        this.handler = handler;

        SpriteSheet ss = new SpriteSheet(Game.sprite_sheet);
        player_image = ss.getImage(1,1,objectWidth,objectHeight);
    }

    public Rectangle getBounds(){return new Rectangle((int)x,(int)y,objectWidth,objectHeight);}

    @Override
    public void tick() {
        x += velX;
        y += velY;

        x = Game.clamp(x,0,Game.mapWIDTH-objectWidth);
        y = Game.clamp(y,0,Game.mapHEIGHT-(20+objectHeight));

        collision();
    }

    private void collision(){
        for(int i = 0; i < handler.objects.size(); i++){
            GameObject tempObject = handler.objects.get(i);

            if(tempObject.getId() == ID.enemy || tempObject.getId() == ID.smartEnemy){
                if(getBounds().intersects(tempObject.getBounds())){
                    HUD.HEALTH -= 2;

                }
            }
        }
    }

    @Override
    public void render(Graphics g) {
        //g.setColor(Color.blue);
        //g.fillRect((int)x,(int)y,32,32);
        g.drawImage(player_image,(int)x,(int)y,null);

    }

    @Override
    public void hurt() {

    }
}
