package HexGame;

import javafx.scene.shape.Polygon;

import java.util.ArrayList;


/**
 * Created by admin on 2015/9/24.
 *
 * This class is for creating the nooks for using in the stage
 */

public class FxNook extends Polygon {

    private int num;

    public int getNum() {return num;}

    private int row;

    public int getRow(){ return row; }

    private  int column;

    public int getColumn(){ return column; }

    private char Drc;

    public char getDrc(){ return Drc;}

    private double side;

    public double getSide() {return side;}


    //The construction of FxNook is to create the position and the id of the nook in a exactly position by the given string

    public FxNook(String game){
        int num = Integer.parseInt(game.substring(0,3));
        this.num = num;
        this.row = Node.getRow(num);
        this.column = Node.getColumn(num);
        this.Drc = game.charAt(3);
        ArrayList<Hexagon> hexagons = new ArrayList<Hexagon>();
        Hexagon.createHexagons(hexagons);
        Hexagon hexagon = Hexagon.findHexagon(hexagons,num);
        double x = hexagon.getX();
        double y = hexagon.getY();
        this.side = 23*0.8;
        double p0 = Math.sqrt((side*side)-(side*side/4));
        double q0 = side/2;
        Double[] p = new Double[8];
        switch (Drc){
            case 'A':
                p = new Double[]{p0, -q0, 0.0, -side, -p0, -q0, -p0, q0};
                break;
            case 'B':
                p = new Double[]{p0,-q0,0.0,-side,-p0,-q0,p0,q0};
                break;
            case 'C':
                p = new Double[]{p0,-q0,0.0,-side,0.0,side,p0,q0};
                break;
            case 'D':
                p = new Double[]{p0,-q0,-p0,q0,0.0,side,p0,q0};
                break;
            case 'E':
                p = new Double[]{-p0,-q0,-p0,q0,0.0,side,p0,q0};
                break;
            case 'F':
                p = new Double[]{0.0,-side,-p0,-q0,-p0,q0,0.0,side};
                break;
        }
        this.getPoints().addAll(p);
        this.setLayoutX(x);
        this.setLayoutY(y);

    }

}