import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
public class Spritesheet {
    public static BufferedImage sprite_Sheet,tille_wall;
    public static BufferedImage[] player_front;

    public Spritesheet() {
        try {
            sprite_Sheet = ImageIO.read(getClass().getResource("./res/sprites/spritesheet.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        player_front = new BufferedImage[2];
        player_front[0] = Spritesheet.getSprite(0,11,16,16);
        player_front[1] = Spritesheet.getSprite(16,11,16,16);
        tille_wall = Spritesheet.getSprite(261,241,16,16);

    }

    public static BufferedImage getSprite(int x, int y, int widht, int height) {
        return sprite_Sheet.getSubimage(x, y, widht, height);
    }
}
