import java.awt.*;

public class Bullet extends Rectangle {

    public int dir = 1,bullet_speed = 8;
    public int sec = 0;

    public Bullet(int x,int y, int dir){
        super(x + 16,y + 16,12,12);
        this.dir = dir;
    }

    public void tick(){
        x+=bullet_speed*dir;
        sec++;
        if (sec == 120){
            Player.bullets.remove(this);
            return;
        }
    }

    public void render(Graphics g){
        g.setColor(Color.yellow);
        g.fillOval(x,y,width,height);
    }
}
