package Engine.main.Objects;

import Engine.main.*;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Bullet extends GameObject{

    private Handler handler;
    private float speed = 10;
    private int objectWidth = 20, objectHeight = 20;
    private BufferedImage bullet_image;


    public Bullet(float x, float y, Handler handler, int mxf, int myf) {
        super(x, y, ID.bullet);

        float mx = x-332+mxf;
        float my = y-252+myf;

        float diffx = mx - x;
        float diffy = my - y;
        float distance = (float) Math.sqrt(diffx*diffx + diffy*diffy);

        velX = ((1/distance) * diffx*speed);
        velY = ((1/distance) * diffy*speed);

        this.handler = handler;
        this.id = id;

        SpriteSheet ss = new SpriteSheet(Game.sprite_sheet);
        bullet_image = ss.getImage(5,1,objectWidth,objectHeight);
    }

    @Override
    public void tick() {
        x += velX;
        y += velY;

        if(y <= 0 || y >= Game.mapHEIGHT-10) handler.removeObject(this);
        if(x <= 0 || x >= Game.mapWIDTH-5) handler.removeObject(this);

        //handler.addObject(new Trail((int)x,(int)y,Color.yellow,width,height, 0.1f,handler));

        collision();
    }

    @Override
    public void render(Graphics g) {
        //g.setColor(Color.yellow);
        //g.fillRect((int)x,(int)y,width,height);
        g.drawImage(bullet_image,(int)x,(int)y,null);
    }

    @Override
    public void hurt() {

    }

    @Override
    public Rectangle getBounds(){return new Rectangle((int)x,(int)y,objectWidth,objectHeight);}

    private void collision(){
        for(int i = 0; i < handler.objects.size(); i++){
            GameObject tempObject = handler.objects.get(i);

            if(tempObject.getId() == ID.smartEnemy){
                if(getBounds().intersects(tempObject.getBounds())){
                    tempObject.hurt();
                    handler.removeObject(this);
                    //handler.removeObject(tempObject);

                }
            }
        }
    }

}
