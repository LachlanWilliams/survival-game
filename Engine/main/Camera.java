package Engine.main;

import Engine.main.Objects.GameObject;

public class Camera {

    private  int x,y;
    private Handler handler;
    private GameObject tempPlayer = null;

    public Camera(int x, int y, Handler handler){
        this.x = x;
        this.y = y;
        this.handler = handler;

        findPlayer();
    }

    public void findPlayer(){
        for(int i = 0; i < handler.objects.size(); i++){
            if(handler.objects.get(i).getId() == ID.player){
                tempPlayer = handler.objects.get(i);
                break;
            }
        }
    }

    public void tick() {
        if(tempPlayer != null){
            x = (int) tempPlayer.getX() - Game.WIDTH/2;
            y = (int) tempPlayer.getY() - Game.HEIGHT/2;
        }else findPlayer();
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }
}
