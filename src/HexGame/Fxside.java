package HexGame;

import javafx.scene.shape.Polygon;

import java.util.ArrayList;

/**
 * Created by admin on 29/09/15.
 *
 * This class is for creating the sides for using in the stage
 *
 */
public class Fxside extends Polygon {

    private int num;

    public int getNum() {return num;}

    private double side;

    public double getSide() {return side;}


    //The construction of Fxside is to create the position and the id of the side in a exactly position by the given string

    public Fxside(int num){
        ArrayList<Hexagon> hexagons = new ArrayList<Hexagon>();
        Hexagon.createHexagons(hexagons);
        Hexagon hexagon = Hexagon.findHexagon(hexagons,num);
        double x = hexagon.getX();
        double y = hexagon.getY();
        this.side = 23*0.8;
        double p0 = Math.sqrt((side*side)-(side*side/4));
        double q0 = side/2;
        Double[] p = new Double[6];
        int sidenum = num / 8 - 21;
        if (num % 8 == 0){
            sidenum--;
        }
        switch (sidenum){
            case 0:
                p = new Double[]{-p0,-q0,0.0,-side,p0,-q0,0.0,0.0};
                break;
            case 1:
                p = new Double[]{0.0,-side,p0,-q0,p0,q0,0.0,0.0};
                break;
            case 2:
                p = new Double[]{p0,-q0,p0,q0,0.0,side,0.0,0.0};
                break;
            case 3:
                p = new Double[]{p0,q0,0.0,side,-p0,q0,0.0,0.0};
                break;
            case 4:
                p = new Double[]{0.0,side,-p0,q0,-p0,-q0,0.0,0.0};
                break;
            case 5:
                p = new Double[]{-p0,q0,-p0,-q0,0.0,-side,0.0,0.0};
                break;
        }
        this.getPoints().addAll(p);
        this.setLayoutX(x);
        this.setLayoutY(y);
    }


}
