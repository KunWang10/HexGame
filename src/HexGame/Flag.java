package HexGame;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

import java.util.ArrayList;

/**
 * Created by wangkun on 15/10/15.
 */
public class Flag extends Polygon {
    int num;

    public Flag(int num){
        double size = 12;
        Double[] p = new Double[]{-1.0,size,-1.0,0.0,-size*1.5,0.0,-1.0,-size,1.0,-size,1.0,size};
        this.getPoints().addAll(p);
        ArrayList<Hexagon> hexagons = new ArrayList<Hexagon>();
        Hexagon.createHexagons(hexagons);
        Hexagon hexagon = Hexagon.findHexagon(hexagons,num);
        double x = hexagon.getX();
        double y = hexagon.getY();
        this.setLayoutX(x+3);
        this.setLayoutY(y);
        this.setFill(Color.RED);
    }
}
