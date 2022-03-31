import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.*;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;


public class Game extends Canvas implements Runnable, KeyListener {


    public static int WIDHT = 640, HEIGHT = 480;
    public int result = WIDHT / 32;
    public static int SCALE = 3;
    public static Player player;
    public World world;
    private static JMenuItem exit;
    private static Game game;
    private static JFrame frame;
    private static JMenuBar menu_bar;
    private static JMenu menu;
    public List<Enemy> enemies = new ArrayList<Enemy>();

    public Game(){
        this.addKeyListener(this);
        this.setPreferredSize(new Dimension(WIDHT, HEIGHT));
        new Spritesheet();
        enemies.add(new Enemy(32,32));
        player = new Player(32,32);
        world = new World();
    }


    public void tick() {
        player.tick();

        for (int i = 0; i < enemies.size(); i++) {
            enemies.get(i).tick();
        }
    }

    public void render(){
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(3);   
            return;
        }
        Graphics g = bs.getDrawGraphics();

        g.setColor(Color.green);
        g.fillRect(0, 0, WIDHT * SCALE,HEIGHT * SCALE);

        for (int i = 0; i < enemies.size(); i++) {
            enemies.get(i).render(g);
        }
        player.render(g);
        world.render(g);

        bs.show();
    }

    public static void main(String[] args){
        game = new Game();
        frame = new JFrame();
        menu_bar = new JMenuBar();
        menu = new JMenu("Options");
        exit = new JMenuItem("Exit");
        menu.add(exit);
        menu_bar.add(menu);
        frame.setJMenuBar(menu_bar);

        /*Exit Game*/
        exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0));
        exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        /*Exit Game*/

        frame.add(game);
        frame.setTitle("Mini Zelda");
        frame.setResizable(false);
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

    //dont use but this.exist = true :)
    @Override
    public void keyTyped(KeyEvent e) {}

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
        if(e.getKeyCode() == KeyEvent.VK_J){
            player.shoot = true;
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