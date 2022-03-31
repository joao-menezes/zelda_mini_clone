import java.awt.Rectangle;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public class Player extends Rectangle{

    public int spd = 4;
    public boolean right,up,down,left,shoot = false;

    public int anim = 0,frames = 0,target_Frames = 15;

    public static List<Bullet> bullets = new ArrayList<Bullet>();
    public int player_direction = 1;

    public Player(int x,int y){
        super(x,y,32,32);
    }

    public void tick(){
        boolean move = false;
        if (right && World.isFree(x+spd,y)) {
            x+=spd;
            move = true;
            player_direction = 1;
        }else if (left && World.isFree(x-spd,y)) {
            x-=spd;
            move = true;
            player_direction = -1;
        }

        if (up  && World.isFree(x,y-spd)) {
            y-=spd;
            move = true;
        }else if (down && World.isFree(x,y+spd)) {
            y+=spd;
            move = true;
        }

        if (move) {
            frames++;
        if (frames == target_Frames) {
            frames = 0;
            anim++;
            if (anim == Spritesheet.player_front.length){
                anim = 0;
            }
         }
        }

        if(shoot){
            shoot = false;
            bullets.add(new Bullet(x,y,player_direction));
        }
        for (int i = 0; i < bullets.size(); i++){
            bullets.get(i).tick();
        }

    }

    public void render(Graphics g){
         g.drawImage(Spritesheet.player_front[anim],x,y,32,32,null);
        for (int i = 0; i < bullets.size(); i++){
            bullets.get(i).render(g);
        }
    }
}