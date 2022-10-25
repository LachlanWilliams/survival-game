package Engine.main;

import java.awt.*;

public class HUD {

    public static float HEALTH = 100;

    private float score = 0;
    private float level = 0;

    public void tick(){

        HEALTH = Game.clamp(HEALTH, 0.0f, 100.0f);
        score++;
        if(score%100==0){
            level +=1;
        }

    }
    public void render(Graphics g){
        g.setColor(Color.BLACK);
        g.fillRect(15,15,200,32);
        g.setColor(Color.GREEN);
        g.fillRect(15,15,(int)HEALTH*2,32);
        g.drawRect(15,15,200,32);

        g.drawString("Score: " + score, 15,64);
        g.drawString("Level: " + level, 15,80);

    }

    public void score(float score){
        this.score = score;
    }

    public float getScore(){
        return score;
    }

    public float getLevel(){
        return level;
    }

    public void reset(){
        score = 0;
        level = 0;
    }
}
