import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;

public class World {

    public static List<Blocks> blocos = new ArrayList<Blocks>();

    public World() {
        for (int xx = 0;xx < 20; xx++) {
            blocos.add(new Blocks(xx*32,0));

        }

        for (int xx = 0;xx < 20; xx++) {
            blocos.add(new Blocks(xx*32,480-32));
        }

        for (int yy = 0;yy < 15; yy++) {
            blocos.add(new Blocks(0,yy*32));
        }

        for (int yy = 0;yy < 15; yy++) {
            blocos.add(new Blocks(640-32,yy*32));
        }

        int rand = (int) Math.ceil(Math.random()*640-32 + 100);
        int rand2 = (int) Math.ceil(Math.random()*480-32 + 100);
        for (int i = 0;i < 10;i++){
             //List<Blocks> blocos = new ArrayList<Blocks>();
            blocos.add(new Blocks(rand,rand2));
        }

    }

    public static boolean isFree(int x,int y) {
        for (int i = 0; i < blocos.size(); i++) {
            Blocks blocoAtual = blocos.get(i);
            if (blocoAtual.intersects(new Rectangle(x,y,32,32))){
                return false;
            }
        }
        return true;
    }

    public void render(Graphics g) {
        for (int i = 0; i < blocos.size(); i++) {
            blocos.get(i).render(g);
        }
    }
    
    
}
