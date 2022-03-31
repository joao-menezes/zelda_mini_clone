import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Enemy extends Rectangle{

    public int spd = 3;
    public int right = 1;
    public boolean shoot = false,move = true;
    public int anim = 0,frames = 0,target_Frames = 15;

    public static List<Bullet> bullets = new ArrayList<Bullet>();
    public int enemy_direction = 1;

    public Enemy(int x, int y){
        super(x,y,32,32);
    }

    public void tick(){

        if (right == 1 && World.isFree(x + 1, y)){
            x++;
        }

        follow_player();
        enemy_move();

        if(shoot){
            shoot = false;
            bullets.add(new Bullet(x,y,enemy_direction));
        }
        for (int i = 0; i < bullets.size(); i++){
            bullets.get(i).tick();
        }

    }

    private void follow_player() {
        Player player = Game.player;
        if (x < player.x && World.isFree(x + spd,y)){
            if (new Random().nextInt(100) < 50)
                x +=spd;

        }else if(x > player.x && World.isFree(x - spd,y)){
            if (new Random().nextInt(100) < 50)
                x -= spd;
        }
        if (y < player.y && World.isFree(x,y +spd)){
            if (new Random().nextInt(100) < 50)
            y += spd;
        }else if(y > player.y && World.isFree(x,y - spd)){
            if (new Random().nextInt(100) < 50)
            y -= spd;
        }
    }

    public void enemy_move(){
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
    }

    public void render(Graphics g){
         g.drawImage(Spritesheet.enemy_sheet_front[anim],x,y,32,32,null);
        for (int i = 0; i < bullets.size(); i++){
            bullets.get(i).render(g);
        }
    }
}