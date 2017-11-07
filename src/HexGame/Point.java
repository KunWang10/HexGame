package HexGame;

/**
 * Created by wangkun on 7/11/2017.
 */
// TODO: 7/11/2017 optimise functions and names
public class Point {

    private int value;

    public int getValue() {
        return value;
    }

    private Point parent;

    public Point getParent() {
        return parent;
    }

    public Point(int value, Point parent) {
        this.value = value;
        this.parent = parent;
    }

    public static void findparent(Point me) {
        me.value = me.getParent().getValue();
        me.parent = me.getParent().getParent();
    }
}
