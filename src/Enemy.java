import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Enemy extends Rectangle{

    public int spd = 4;
    public int right = 1,up = 0,down = 0,left = 0;
    public boolean shoot = false;
    public int anim = 0,frames = 0,target_Frames = 15;

    public static List<Bullet> bullets = new ArrayList<Bullet>();
    public int enemy_direction = 1;

    public Enemy(int x, int y){
        super(x,y,32,32);
    }

    public void tick(){
        boolean move = true;
        if (right == 1 ){
            x++;
        }

        if (move) {
            frames++;
        if (frames == target_Frames) {
            frames = 0;
            anim++;
            if (anim == Spritesheet.enemy_sheet_front.length){
                anim = 0;
            }
         }
        }

        if(shoot){
            shoot = false;
            bullets.add(new Bullet(x,y,enemy_direction));
        }
        for (int i = 0; i < bullets.size(); i++){
            bullets.get(i).tick();
        }

    }

    public void render(Graphics g){
         g.drawImage(Spritesheet.enemy_sheet_front[anim],x,y,32,32,null);
        for (int i = 0; i < bullets.size(); i++){
            bullets.get(i).render(g);
        }
    }
}