package HexGame;

/**
 * Created by wangkun on 15/10/15.
 *
 * This class is for recording 4 pieces about their relationship
 */
public class Recording {

    private String pieces;

    public String getPieces() {
        return pieces;
    }

    public String getPieces(int k) {

        return pieces.substring(3*k,3*k+3);
    }

    private Point point;

    public Point getPoint() {return point;}

    private int who;

    public int getWho() {return who;}

    private Recording parent;

    public Recording getParent() {return parent;}

    public Recording(String pieces, Point point, int who, Recording parent){
        this.pieces = pieces;
        this.point = point;
        this.who = who;
        this.parent = parent;
    }

    public static void findparent (Recording me){
        me.pieces = me.getParent().getPieces();
        me.point = me.getParent().getPoint();
        me.who = me.getParent().getWho();
        me.parent = me.getParent().getParent();
    }

}
