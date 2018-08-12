package HexGame;

import javafx.scene.shape.Polygon;

import java.util.ArrayList;

/**
 * Created by wangkun on 26/09/15.
 *
 * This class is for creating the hexgon for using in the stage
 */
public class Hexagon extends Polygon {

    private int num;

    public int getNum() {return num;}

    private double x;

    public double getX() {return x;}

    private double y;

    public double getY() {return y;}

    private double side;

    public double getSide() {return side;}


    //By the givn position x and y and the side of the hexgon to create a new hexagon in the right position
    public Hexagon(double x, double y, double side){
        this.x = x;
        this.y = y;
        this.side = side;
        double p0 = Math.sqrt((side*side)-(side*side/4));
        double q0 = side/2;
        Double[] p = {p0,-q0,0.0,-side,-p0,-q0,-p0,q0,0.0,side,p0,q0};
        this.getPoints().addAll(p);
        this.setLayoutX(x);
        this.setLayoutY(y);
    }

    public void setNum(int num){
        this.num = num;
    }


    //creating 217 numbers of the hexagons and give them right positions and ids
    public static void createHexagons(ArrayList<Hexagon> hexagons){
        double key = 20.0;
        double side = 0.8 * key;
        double dx = Math.sqrt(3) * Math.sqrt((key * key)-(key * key/4));
        double dy = 1.5 * Math.sqrt((key * key)-(key * key/4));
        double x0 = 300.0;
        double y0 = 50.0;
        double x = x0 - dx/2;
        double y = y0;


        int count = 1;
        int line = 1;
        int num;
        int n = 10;
        for (int i = 0;i < 217;i++){
            Hexagon t = new Hexagon(x,y,side);
            if (line < 9){
                num = Node.getId(line,count);
            } else {
                num = Node.getId(line,count + line - 9);
            }
            t.setNum(num);
            hexagons.add(t);
            x += dx;
            count++;
            if (line < 9){
                if (count % n == 0){
                    line++;
                    count = 1;
                    n++;
                    x = x0 - line * dx/2;
                    y += dy;
                }
            } else {
                if (count % n == 0){
                    line++;
                    count = 1;
                    n--;
                    x = x0 - 9 * dx + line * dx/2;
                    y += dy;
                }
            }

        }

    }

    //This method is for by giving the list of the hexagons and the id of the hexgon to find right hexgon
    public static Hexagon findHexagon(ArrayList<Hexagon> hexagons,int num){
        for (Hexagon hexagon : hexagons){
            if (hexagon.getNum() == num){
                return hexagon;
            }
        }
        return null;
    }

}
