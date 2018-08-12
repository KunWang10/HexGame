package HexGame;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.ArrayList;


/**
 * Created by admin on 25/09/15.
 * <p>
 * This class is for creating the piece for using in the stage
 */
public class Piece extends Circle {

    private int num;

    public int getNum() {
        return num;
    }

    private int order;

    public int getOrder() {
        return order;
    }

    //This method is for getting the right id of the piece in stage
    public String getNumString() {
        String string = String.valueOf(this.getNum());
        for (int i = 0; i < 2; i++) {
            if (string.length() < 3) {
                string = 0 + string;
            }
        }
        return string;
    }

    private int row;

    public int getRow() {
        return row;
    }

    private int column;

    public int getColumn() {
        return column;
    }

    //The construction of the piece is to construct the right piece in the right position in nook
    public Piece(int num, Color color, int order) {
        this.num = num;
        this.row = Node.getRow(num);
        this.column = Node.getColumn(num);
        this.setFill(color);
        this.order = order;
        this.setRadius(8);
        ArrayList<Hexagon> hexagons = new ArrayList<Hexagon>();
        Hexagon.createHexagons(hexagons);
        Hexagon hexagon = Hexagon.findHexagon(hexagons, num);
        this.setLayoutX(hexagon.getX());
        this.setLayoutY(hexagon.getY());
    }

    // This method is by giving the right id to obtain a piece
    public void refresh(int num) {
        this.num = num;
        this.row = Node.getRow(num);
        this.column = Node.getColumn(num);
        ArrayList<Hexagon> hexagons = new ArrayList<Hexagon>();
        Hexagon.createHexagons(hexagons);
        Hexagon hexagon = Hexagon.findHexagon(hexagons, num);
        this.setLayoutX(hexagon.getX());
        this.setLayoutY(hexagon.getY());
    }

    //This method is finding the id of the piece
    public int findnode() {
        int rtn = -1;
        double x = this.getLayoutX();
        double y = this.getLayoutY();
        ArrayList<Hexagon> hexagons = new ArrayList<Hexagon>();
        Hexagon.createHexagons(hexagons);
        double limit = 10000;
        for (Hexagon hexagon : hexagons) {
            double x0 = hexagon.getX();
            double y0 = hexagon.getY();
            double distance = Math.sqrt((x - x0) * (x - x0) + (y - y0) * (y - y0));
            if (distance < limit) {
                rtn = hexagon.getNum();
                limit = distance;
            }
        }
        return rtn;
    }

}
