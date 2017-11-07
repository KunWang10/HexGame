package HexGame;

import java.util.ArrayList;

/**
 * Created by kun on 2017/11/07.
 */
public class HexGame {


    /**
     * Construct HexGame.HexGame from a string describing game state.
     *
     * @param game The initial state of the game, described as a string according to the assignment specification.
     */
    private String game;

    public HexGame(String game) {

        if (legitimateGame(game))
            this.game = game;
        /* FIXME */
    }

    /**
     * Construct HexGame.HexGame with a random game state that complies with the assignment specification.
     */
    public HexGame() {
        /* FIXME */
        this.game = ConstructionHex.getGame();
    }

    /**
     * Determine whether a set of crannies are legal according to the assignment specification.
     *
     * @param crannies A string describing the crannies, encoded according to the assignment specification.
     * @return True if the crannies are correctly encoded and in legal positions, according to the assignment specification.
     */
    public static boolean legitimateCrannies(String crannies) {
        /* FIXME */
        if (crannies.length() != 18) {
            return false;
        }
        int[] cra = new int[6];
        int[] mrk = new int[6];
        for (int i = 0; i < 6; i++) {
            cra[i] = Integer.parseInt(crannies.substring(3 * i, 3 * i + 3));
            int m = cra[i] / 8 - 21;
            if (cra[i] % 8 == 0) {
                m--;
            }
            if (m >= 0 && m <= 5) {
                mrk[m] = 1;
            }
        }
        if (mrk[0] == 1 && mrk[1] == 1 && mrk[2] == 1 && mrk[3] == 1 && mrk[4] == 1 && mrk[5] == 1) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Determine whether a set of nooks are legal according to the assignment specification.
     *
     * @param nooks A string describing the nooks, encoded according to the assignment specification.
     * @return True if the nooks are correctly encoded and in legal positions, according to the assignment specification.
     */
    public static boolean legitimateNooks(String nooks) {
        /* FIXME */
        if (nooks.length() != 72) {
            return false;
        }
        int[] nks = new int[18];
        char[] psn = new char[18];
        for (int i = 0; i < 18; i++) {
            nks[i] = Integer.parseInt(nooks.substring(4 * i, 4 * i + 3));
            if (!Nook.legal(nks[i])) {
                return false;
            }
            psn[i] = nooks.charAt(4 * i + 3);
            if (psn[i] != 'A' && psn[i] != 'B' && psn[i] != 'C' && psn[i] != 'D' && psn[i] != 'E' && psn[i] != 'F') {
                return false;
            }
        }
        int a = 0;
        int b = 0;
        int c = 0;
        int d = 0;
        int e = 0;
        int f = 0;
        int[][] th = new int[6][18];
        for (int i = 0; i < 18; i++) {
            if (Nook.legalplus(nks[i]) == 1) {
                th[0][a] = nks[i];
                a++;
            }
            if (Nook.legalplus(nks[i]) == 2) {
                th[1][b] = nks[i];
                b++;
            }
            if (Nook.legalplus(nks[i]) == 3) {
                th[2][c] = nks[i];
                c++;
            }
            if (Nook.legalplus(nks[i]) == 4) {
                th[3][d] = nks[i];
                d++;
            }
            if (Nook.legalplus(nks[i]) == 5) {
                th[4][e] = nks[i];
                e++;
            }
            if (Nook.legalplus(nks[i]) == 6) {
                th[5][f] = nks[i];
                f++;
            }
        }
        if (a != 3 || b != 3 || c != 3 || d != 3 || e != 3 || f != 3) {
            return false;
        }
        Node[] id = new Node[217];
        Node.create(id);
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 2; j++) {
                for (int k = j + 1; k < 3; k++) {
                    int x = id[th[i][j]].getRow() - id[th[i][k]].getRow();
                    int y = id[th[i][j]].getColumn() - id[th[i][k]].getColumn();
                    if ((x == 0 && y == 1) || (x == 0 && y == -1) || (x == 1 && y == 0)
                            || (x == -1 && y == 0) || (x == 1 && y == 1) || (x == -1 && y == -1)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /**
     * Determine whether a game state is legal according to the assignment specification.
     *
     * @param game A string describing the game state, encoded according to the assignment specification.
     * @return True if the game state is correctly encoded and represents a legal game state, according to the assignment specification.
     */
    public static boolean legitimateGame(String game) {
        /* FIXME */

        //create databese
        int[] nks = new int[18];
        Node.createNks(game, nks);
        //number of pieces
        int num = (game.length() - 90) / 3;
        int[] p = new int[num];
        for (int i = 0; i < num; i++) {
            p[i] = Integer.parseInt(game.substring(90 + 3 * i, 93 + 3 * i));
            if (!Nook.nk(game, p[i])) {
                return false;
            }
        }
        for (int i = 0; i < num; i++) {
            for (int j = i + 1; j < num; j++) {
                if (p[i] == p[j]) {                          //double pieces in same HexGame.Node
                    return false;
                }
            }
        }

        game = game.substring(0, 90);

        ArrayList<Integer> dictionary = new ArrayList<Integer>();
        dictionary.add(169);

        int key = 0;
        int key1 = 0;
        int key2 = 0;

        for (int i = 0; i < dictionary.size(); i++) {
            for (int j = 1; j < 7; j++) {
                int r = Node.move(game, dictionary.get(i), j);
                int l = 0;
                for (int k = 0; k < dictionary.size(); k++) {
                    if (r == dictionary.get(k)) {
                        l = 1;
                        break;
                    }
                }
                if (l == 1) {
                    continue;
                }
                for (int k = 0; k < 18; k++) {
                    if (r == nks[k])
                        key++;
                }
                if (r == 177) {
                    key1++;
                }
                if (r == 185) {
                    key2++;
                }
                dictionary.add(r);
                if (key1 == 1 && key2 == 1 && key == 18) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * Determine whether a given step is legal according to a given game state and the assignment specification.
     *
     * @param game A string describing the game state, encoded according to the assignment specification.
     * @param from The point from which the step starts
     * @param to   The point to which step goes
     * @return True if the move is legal according to the assignment specification.
     */
    public static boolean legitimateStep(String game, int from, int to) {
        /* FIXME */

        if (!Node.isLine(from, to) || from == to) {
            return false;
        }

        String pieces = game.substring(90);
        int n = pieces.length() / 3;
        int flag = 0;
        for (int i = 0; i < n; i++) {
            if (from == Integer.parseInt(pieces.substring(3 * i, 3 * i + 3))) {
                flag = 1;
            }
        }
        if (flag == 0) {
            return false;
        }
        int drc = Node.direction(from, to);
        int k = Node.move(game, from, drc);
        return (k == to);
    }

    /**
     * Return a minimal path from start to goal.
     *
     * @param game  A string describing the game state, encoded according to the assignment specification.
     * @param start The point from which the path must start
     * @param goal  The point at which the path must end
     * @return An array of integers reflecting a minimal path from start to goal, each integer reflecting a HexGame.Node on the board, starting with the start, and ending with the goal.
     */
    public static int[] minimalPath(String game, int start, int goal) {
        /* FIXME */
        // TODO: 7/11/2017 This can be optimised (BFS)

        //Because I changed HexGame.Node.move, the string to set pieces will influnce the judgement,
        //so we cut the string "game"
        //this method is only for single pieces game now.
        game = game.substring(0, 90);

        ArrayList<ArrayList<Point>> Path = new ArrayList<ArrayList<Point>>();
        ArrayList<Point> zero = new ArrayList<Point>();
        Point Ancestry = new Point(start, null);
        zero.add(Ancestry);
        ArrayList<Point> allPoints = new ArrayList<Point>();
        allPoints.add(Ancestry);
        Path.add(zero);


        //i 表示 List 中的第几个List
        //j 表示 当前起点List的角标
        //
        //Path.get(i) 表示当前起点List
        //
        //Path.get(i＋1) 储存当前终点


        for (int i = 0; i < Path.size(); i++) {

            //this is the new list to store new points,Path.get(i＋1)
            ArrayList<Point> sons = new ArrayList<Point>();
            Path.add(sons);
            //this is the jth start-point in list i
            for (int j = 0; j < Path.get(i).size(); j++) {
                int parentValue = Path.get(i).get(j).getValue();
                for (int k = 1; k < 7; k++) {
                    int childrenValue = Node.move(game, parentValue, k);
                    int flag = 0;
                    for (int l = 0; l < allPoints.size(); l++) {
                        if (allPoints.get(l).getValue() == childrenValue) {
                            flag = 1;
                        }
                    }
                    //if the childrenvalue is a new point, add new point to Path.get(i+1) and allpoints
                    if (flag == 0) {
                        Point child = new Point(childrenValue, Path.get(i).get(j));
                        sons.add(child);
                        allPoints.add(child);
                    }
                    if (childrenValue == goal) {
                        int length = Path.size();
                        int size = Path.get(i + 1).size();
                        int[] recording = new int[length];
                        Point record = Path.get(i + 1).get(size - 1);
                        for (int l = length - 1; l >= 0; l--) {
                            recording[l] = record.getValue();
                            if (l != 0) {
                                Point.findparent(record);
                            }
                        }
                        return recording;
                    }
                }
            }
        }


        return null;
    }

    /**
     * Output the state of the game as a string, encoded according to the assignment specification
     *
     * @return A string that reflects the game state, encoded according to the assignment specification.
     */
    public String toString() {
        return this.game;
    }


}
