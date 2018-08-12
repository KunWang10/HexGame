package HexGame;

import javafx.scene.paint.Color;

/**
 * Created by admin on 2015/9/26.
 *
 * This is creating a array of the colors for random choosing the color
 */
public class Colors {


   private static Color[] colors={Color.RED,Color.BLUE,Color.YELLOW,Color.GREEN,
            Color.PINK,Color.SILVER,Color.BROWN,Color.GOLD,
            Color.LAVENDER,Color.ORANGE
    };


    public static Color getColors() {
        int i=(int)(Math.random() * 10);
        return colors[i];
    }
}
