import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;


public class Game extends Canvas implements Runnable, KeyListener {


    public static int WIDHT = 480, HEIGHT = 480;
    public Player player;
    public World world;

    public Game(){
        this.addKeyListener(this);
        this.setPreferredSize(new Dimension(WIDHT, HEIGHT)); 
        player = new Player(32,32);
        world = new World();
    }


    public void tick() {
        player.tick();
    }

    public void render(){
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(3);   
            return;
        }
        Graphics g = bs.getDrawGraphics();

        g.setColor(Color.black);
        g.fillRect(0, 0, WIDHT,HEIGHT);

        player.render(g);
        world.render(g);

        bs.show();
    }

    public static void main(String[] args){
        Game game = new Game();
        JFrame frame = new JFrame();

        frame.add(game);
        frame.setTitle("Mini Zelda");
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(3);
        frame.setVisible(true);

        new Thread(game).start();

    }

    @Override
    public void run() {
       while (true) {
           tick();
           render();
        try {
            Thread.sleep(1000/60);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }   
       }
        
    }


    @Override
    public void keyTyped(KeyEvent e) {

        
    }


    @Override
    public void keyPressed(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) {
            player.right = true;
        }else if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A){
            player.left = true;
        }

        if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W) {
            player.up = true;
        }else if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S){
            player.down = true;
        }

        if (e.getKeyCode() == KeyEvent.VK_SHIFT) {
            player.spd = 8;
        }
        
    }


    @Override
    public void keyReleased(KeyEvent e) {
 
        if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) {
            player.right = false;
        }else if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A){
            player.left = false;
        }

        if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W) {
            player.up = false;
        }else if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S){
            player.down = false;
        }

        
        if (e.getKeyCode() == KeyEvent.VK_SHIFT) {
            player.spd = 4;
        }
        
    }


  
  
}
