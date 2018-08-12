package HexGame;

import javafx.scene.shape.Polygon;

import java.util.ArrayList;

/**
 * Created by wangkun on 28/09/15.
 *
 * This class is for creating the cranny for using in the stage
 */
public class FxCranny extends Polygon {

    private int num;

    public int getNum() {return num;}

    private int row;

    public int getRow(){ return row; }

    private  int column;

    public int getColumn(){ return column; }

    private double side;


    //The construction of Fxcranny is to create the position and the id of the cranny in a exactly position by the given string
    public FxCranny(String game){
        int num = Integer.parseInt(game.substring(0,3));
        this.num = num;
        this.row = Node.getRow(num);
        this.column = Node.getColumn(num);
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
                p = new Double[]{0.0,0.0,p0,q0,p0,-q0};
                break;
            case 1:
                p = new Double[]{0.0,0.0,0.0,side,p0,q0};
                break;
            case 2:
                p = new Double[]{0.0,0.0,0.0,side,-p0,q0};
                break;
            case 3:
                p = new Double[]{0.0,0.0,-p0,q0,-p0,-q0};
                break;
            case 4:
                p = new Double[]{0.0,0.0,0.0,-side,-p0,-q0};
                break;
            case 5:
                p = new Double[]{0.0,0.0,0.0,-side,p0,-q0};
                break;
        }
        this.getPoints().addAll(p);
        this.setLayoutX(x);
        this.setLayoutY(y);
    }



}
