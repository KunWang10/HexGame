import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * Created by Kun on 2017/11/07.
 * <p>
 * Construct HexGame from a string describing game state.
 * The initial state of the game, described as a string according to the assignment specification.
 * To get the right construction of hexgame, it should get the right nooks,crannies,pieces
 */

// TODO: 7/11/2017 optimise the structure of Game constructure
// TODO: 7/11/2017 do some renames
// TODO: 7/11/2017 There are some untouched functions
public class ConstructionHex {

    private static String nooks = "";
    private static String crannies = "";
    private static String piece = "";
    private static String game = "";

    //This construction is just for making the only one piece of the game
    public static String getGame() {
        do {
            nooks = "";
            crannies = "";
            game = "";
            piece = "";
            game += getRightCranny() + getRightNook() + getPiece();
        } while (!HexGame.legitimateGame(game));
        return game;

    }

    //This construction is for making the different piece of the game by the giving argument i
    public static String getGame(int i) {
        do {
            nooks = "";
            crannies = "";
            game = "";
            piece = "";
            game += getRightCranny() + getRightNook() + getPiece(i);
        } while (!HexGame.legitimateGame(game));
        return game;

    }

    //This method is for getting the right number,right position and right direction of the nooks
    public static String getRightNook() {

        //String nooks="";

        String[] a = {"008", "020", "021", "038", "039", "040", "062", "063", "064", "065", "092", "093", "094", "095", "096"};
        String[] b = {"098", "067", "099", "042", "068", "100", "023", "043", "069", "101", "010", "024", "044", "070", "102"};
        String[] c = {"012", "026", "046", "072", "104", "027", "047", "073", "105", "048", "074", "106", "075", "107", "108"};
        String[] d = {"014", "030", "029", "052", "051", "050", "080", "079", "078", "077", "114", "113", "112", "111", "110"};
        String[] e = {"120", "085", "056", "033", "016", "119", "084", "055", "032", "118", "083", "054", "117", "082", "116"};
        String[] f = {"126", "125", "090", "124", "089", "060", "123", "088", "059", "036", "122", "087", "058", "035", "018"};
        String[] s = {"A", "B", "C", "D", "E", "F"};


//        Node[] id = new Node[217];
//        Node.create(id);

        Random r = new Random();
        boolean flaga = true;
        boolean flagb = true;
        boolean flagc = true;
        boolean flagd = true;
        boolean flage = true;
        boolean flagf = true;


        do {
            String index1 = a[r.nextInt(a.length)];
            String index2 = a[r.nextInt(a.length)];
            String index3 = a[r.nextInt(a.length)];
            String s1 = s[r.nextInt(s.length)];
            String s2 = s[r.nextInt(s.length)];
            String s3 = s[r.nextInt(s.length)];
            if (adjacent(index1, index2) && adjacent(index1, index3) && adjacent(index2, index3)) {
                flaga = false;
                nooks += "" + index1 + s1 + index2 + s2 + index3 + s3;

            }

        } while (flaga);

        do {
            String index1 = b[r.nextInt(a.length)];
            String index2 = b[r.nextInt(a.length)];
            String index3 = b[r.nextInt(a.length)];

            String s1 = s[r.nextInt(s.length)];
            String s2 = s[r.nextInt(s.length)];
            String s3 = s[r.nextInt(s.length)];

            if (adjacent(index1, index2) && adjacent(index1, index3) && adjacent(index2, index3)) {
                flagb = false;
                nooks += "" + index1 + s1 + index2 + s2 + index3 + s3;

            }

        } while (flagb);

        do {
            String index1 = c[r.nextInt(a.length)];
            String index2 = c[r.nextInt(a.length)];
            String index3 = c[r.nextInt(a.length)];

            String s1 = s[r.nextInt(s.length)];
            String s2 = s[r.nextInt(s.length)];
            String s3 = s[r.nextInt(s.length)];

            if (adjacent(index1, index2) && adjacent(index1, index3) && adjacent(index2, index3)) {
                flagc = false;
                nooks += "" + index1 + s1 + index2 + s2 + index3 + s3;

            }

        } while (flagc);

        do {
            String index1 = d[r.nextInt(a.length)];
            String index2 = d[r.nextInt(a.length)];
            String index3 = d[r.nextInt(a.length)];

            String s1 = s[r.nextInt(s.length)];
            String s2 = s[r.nextInt(s.length)];
            String s3 = s[r.nextInt(s.length)];

            if (adjacent(index1, index2) && adjacent(index1, index3) && adjacent(index2, index3)) {
                flagd = false;
                nooks += "" + index1 + s1 + index2 + s2 + index3 + s3;

            }

        } while (flagd);

        do {
            String index1 = e[r.nextInt(a.length)];
            String index2 = e[r.nextInt(a.length)];
            String index3 = e[r.nextInt(a.length)];

            String s1 = s[r.nextInt(s.length)];
            String s2 = s[r.nextInt(s.length)];
            String s3 = s[r.nextInt(s.length)];

            if (adjacent(index1, index2) && adjacent(index1, index3) && adjacent(index2, index3)) {
                flage = false;
                nooks += "" + index1 + s1 + index2 + s2 + index3 + s3;

            }

        } while (flage);

        do {
            String index1 = f[r.nextInt(a.length)];
            String index2 = f[r.nextInt(a.length)];
            String index3 = f[r.nextInt(a.length)];

            String s1 = s[r.nextInt(s.length)];
            String s2 = s[r.nextInt(s.length)];
            String s3 = s[r.nextInt(s.length)];

            if (adjacent(index1, index2) && adjacent(index1, index3) && adjacent(index2, index3)) {
                flagf = false;
                nooks += "" + index1 + s1 + index2 + s2 + index3 + s3;

            }

        } while (flagf);


        //System.out.println(nooks);

        return nooks;

    }

    //This method is for distinguish whether the nooks are adjacent or not
    public static boolean adjacent(String a, String b) {

        Node[] id = new Node[217];
        Node.create(id);

        int a0 = Integer.parseInt(a);
        int b0 = Integer.parseInt(b);

        if (a0 == b0) {
            return false;
        }

        int x = id[a0].getRow() - id[b0].getRow();
        int y = id[a0].getColumn() - id[b0].getColumn();
        if ((x == 0 && y == 1) || (x == 0 && y == -1) || (x == 1 && y == 0)
                || (x == -1 && y == 0) || (x == 1 && y == 1) || (x == -1 && y == -1)) {
            return false;
        }

        return true;
    }


    //This method is for getting the right number,right position of the crannies
    public static String getRightCranny() {

        //  String crannies="";
        int[] a = {169, 170, 171, 172, 173, 174, 175, 176};
        int[] b = {177, 178, 179, 180, 181, 182, 183, 184};
        int[] c = {185, 186, 187, 188, 189, 190, 191, 192};
        int[] d = {193, 194, 195, 196, 197, 198, 199, 200};
        int[] e = {201, 202, 203, 204, 205, 206, 207, 208};
        int[] f = {209, 210, 211, 212, 213, 214, 215, 216};

        Random r = new Random();
        crannies += a[r.nextInt(a.length)];
        crannies += b[r.nextInt(a.length)];
        crannies += c[r.nextInt(a.length)];
        crannies += d[r.nextInt(a.length)];
        crannies += e[r.nextInt(a.length)];
        crannies += f[r.nextInt(a.length)];


        return crannies;
    }


    //This method is for getting the right one piece
    public static String getPiece() {
        // String piece="";
        // String nooks=getRightNook();
        String[] nks = new String[18];
        for (int i = 0; i < 18; i++) {
            nks[i] = nooks.substring(4 * i, 4 * i + 3);
        }
        Random random = new Random();
        piece = "" + nks[random.nextInt(nks.length)];

        return piece;

    }

    //This method is for getting the right number of pieces by giving the arguments k
    public static String getPiece(int k) {
        // String piece="";

        String[] nks = new String[18];
        for (int i = 0; i < 18; i++) {
            nks[i] = nooks.substring(4 * i, 4 * i + 3);
        }

        Set<String> set = new HashSet<>(k);
        do {
            Random r = new Random();
            set.add(nks[r.nextInt(nks.length)]);

        } while (set.size() != k);

        for (String p : set) {
            piece = piece + p;

        }


        return piece;
    }


}
