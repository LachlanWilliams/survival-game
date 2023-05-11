package Engine.main.Objects;

import Engine.main.*;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SmartEnemy extends GameObject {

    private Handler handler;
    private GameObject player;
    private int objectWidth = 32, objectHeight = 32;
    private float speed = 2;
    private int health = 2;
    private BufferedImage smartEnemy_image;

    public SmartEnemy(float x, float y, Handler handler) {
        super(x, y, ID.smartEnemy);

        this.handler = handler;

        SpriteSheet ss = new SpriteSheet(Game.sprite_sheet);
        smartEnemy_image = ss.getImage(1,3,objectWidth,objectHeight);

        for(int i = 0; i < handler.objects.size();i++){
            if(handler.objects.get(i).getId() == ID.player){
                player = handler.objects.get(i);
            }
        }

    }

    public Rectangle getBounds(){return new Rectangle((int)x,(int)y,objectWidth,objectHeight);}

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

        //handler.addObject(new Trail((int)x,(int)y,Color.magenta,16,16, 0.1f,handler));
    }

    public void hurt(){

        health = health-1;
        if(health == 0){
            handler.removeObject(this);
        }
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
        //g.setColor(Color.magenta);
        //g.fillRect((int)x,(int)y,16,16);
        g.drawImage(smartEnemy_image,(int)x,(int)y,null);


    }
}
