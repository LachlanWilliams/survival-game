package Engine.main;

import Engine.main.Objects.Box;
import Engine.main.Objects.Enemy;
import Engine.main.Objects.Player;
import Engine.main.KeyInput;
import Engine.main.Objects.SmartEnemy;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class Game extends Canvas implements Runnable {

    private static final long serialVersionUID = 1L;

    public static final int WIDTH = 640, HEIGHT = WIDTH / 12 * 9;

    private Thread thread;
    private boolean running = false;

    public static boolean paused = false;

    private final Handler handler;
    private HUD hud;
    private Spawn spawner;
    private Menu menu;
    private Camera cam;

    public enum STATE {
        Menu,
        Help,
        Game,
        End
    }

    public STATE gameState = STATE.Menu;

    public Game(){
        System.out.println("Note: game created");

        handler = new Handler(this);
        hud = new HUD();
        spawner = new Spawn(handler,hud);
        menu = new Menu(handler,this,hud);
        this.addKeyListener(new KeyInput(handler,this));
        this.addMouseListener(menu);
        cam = new Camera(0,0,handler);

        //AudioPlayer.load();

        new Window(WIDTH,HEIGHT,"RANDOM CUBE GAME",this);

        if(gameState == STATE.Game){
            handler.addObject(new Box(0,0,ID.box));
            handler.addObject(new Player(100,100,ID.player,handler));
            handler.addObject(new SmartEnemy(250,250,ID.smartEnemy,handler));
            handler.addObject(new SmartEnemy(250,300,ID.smartEnemy,handler));
        }
    }

    public synchronized void start(){
        System.out.println("Note: game.start()");

        thread = new Thread(this);
        running = true;
        thread.start();
    }
    public synchronized void stop(){
        try{
            thread.join();
            running = false;

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void run() {
        System.out.println("Note: game.run()");

        this.requestFocus();

        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while(running){
            long now = System.nanoTime();
            delta += (now-lastTime) / ns;
            lastTime = now;
            while(delta >= 1){
                tick();
                delta--;
            }
            if(running){
                render();
            }
            frames++;

            if(System.currentTimeMillis() - timer > 1000){
                timer += 1000;
                //System.out.println("FPS: " + frames);
                frames = 0;
            }
        }
        stop();
    }

    private void tick(){
        if (gameState == STATE.Game) {
            if(!paused){
                handler.tick();
                hud.tick();
                spawner.tick();
                cam.tick();
                if(hud.HEALTH<=0){
                    HUD.HEALTH=100;
                    gameState = STATE.End;
                    handler.clearEnemys();
                    cam.reset();
                }
            }
        }else if(gameState == STATE.Help||gameState == STATE.Menu||gameState == STATE.End){
            handler.tick();
            menu.tick();
        }

    }

    private void render(){
        //System.out.println("Is render");
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null){
            this.createBufferStrategy(2);
            return;
        }

        Graphics g = bs.getDrawGraphics();
        Graphics2D g2d = (Graphics2D) g;

        //TODO: remove box and change general background
        g.setColor(Color.BLACK);
        g.fillRect(0,0,WIDTH,HEIGHT);

        g2d.translate( -cam.getX(), -cam.getY());

        handler.render(g);

        g2d.translate( cam.getX(), cam.getY());

        if(paused){
            g.setColor(Color.RED);
            g.drawString("Paused", 100, 100);
        }

        if (gameState == STATE.Game){
            hud.render(g);
        }else if(gameState == STATE.Menu || gameState == STATE.Help|| gameState == STATE.End){
            menu.render(g);
        }

        g.dispose();
        bs.show();

    }

    public static float clamp(float var, float min, float max){
        if(var >= max) return var = max;
        else if(var <= min) return  var = min;
        else return var;
    }

    public static void main(String[] args) {
        new Game();
    }
}
