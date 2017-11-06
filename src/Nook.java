/**
 * Created by kun on 2017/11/07.
 */
public class Nook {

    private int id;

    public int getId(){ return id; }

    private int row;

    public int getRow(){ return row; }

    private  int column;

    public int getColumn(){ return column; }

    private char Drc;

    public char getDrc(){ return Drc;}

    public Nook(int id, char Drc){
        this.id = id;
        this.row = Node.getRow(id);
        this.column = Node.getColumn(id);
        this.Drc = Drc;
    }


    public void putnook(String game,Node id){
        this.id = Node.getId(id.getRow(), id.getColumn());
        this.row = id.getRow();
        this.column = id.getColumn();
        this.Drc = Node.getDrc(game, this.id);
    }

    //judging which directions of the nook blocking
    public static boolean out(char C,int drc){
        if (C == 'A' && (drc == 3 || drc == 4 || drc == 5 )){
            return true;
        }else if (C == 'B' && (drc == 4 || drc == 5 || drc == 6 )){
            return true;
        }else if (C == 'C' && (drc == 1 || drc == 5 || drc == 6 )){
            return true;
        }else if (C == 'D' && (drc == 1 || drc == 2 || drc == 6 )){
            return true;
        }else if (C == 'E' && (drc == 1 || drc == 2 || drc == 3 )){
            return true;
        }else if (C == 'F' && (drc == 2 || drc == 3 || drc == 4 )){
            return true;
        }else {
            return false;
        }
    }

    //judging whether nooks are placed at the positions marked in red
    public static boolean legal(int id){
        if (id >= 8 && id <= 126 &&
                id != 19 && id != 37 && id != 61 && id != 91 &&
                id !=  9 && id != 22 && id != 41 && id != 66 && id != 97 &&
                id != 11 && id != 25 && id != 45 && id != 71 && id != 103 &&
                id != 13 && id != 28 &&  id != 49 && id != 76 && id != 109 &&
                id != 15 && id != 31 && id != 53 && id != 81 && id != 115 &&
                id != 17 && id != 34 && id != 57 && id != 86 && id != 121){
            return true;
        } else {
            return false;
        }
    }

    //judging whether exactly three nooks in the each of the six triangles
    public static int legalplus(int id){

        if(id == 92 || id == 93 || id == 94 || id == 95 || id == 96 ||
                id == 62 || id == 63 || id == 64 || id == 65 ||
                id == 38 || id == 39 || id == 40 ||
                id == 20 || id == 21 ||
                id == 8) {
            return 1;
        }else if(id == 98 ||
                id == 67 || id == 99 ||
                id == 42 || id == 68 || id == 100 ||
                id == 23 || id == 43 || id == 69 || id == 101 ||
                id == 10 || id == 24 || id == 44 || id == 70 || id == 102){
            return 2;
        }else if(id == 12 || id == 26 || id == 46 || id == 72 || id == 104 ||
                id == 27 || id == 47 || id == 73 || id == 105 ||
                id == 48 || id == 74 || id == 106 ||
                id == 75 || id == 107 ||
                id == 108){
            return 3;
        }else if(id == 14 ||
                id == 30 || id == 29 ||
                id == 52 || id == 51 || id == 50 ||
                id == 80 || id == 79 || id == 78 || id == 77 ||
                id == 144 || id == 113 || id == 112 || id == 111 || id == 110){
            return 4;
        }else if(id == 120 || id == 85 || id == 56 || id == 33 || id == 16 ||
                id == 119 || id == 84 || id == 55 || id == 32 ||
                id == 118 || id == 83 || id == 54 ||
                id == 117 || id == 82 ||
                id == 116){
            return 5;
        }else if(id == 126 ||
                id == 125 || id == 90 ||
                id == 124 || id == 89 || id == 60 ||
                id == 123 || id == 88 || id == 59 || id == 36 ||
                id == 122 || id == 87 || id == 58 || id == 35 || id == 18){
            return 6;
        }else
            return 0;

    }

    //judging whether is a nook or not
    public static boolean nk(String game,int id){
        int[] nks = new int[18];
        Node.createNks(game, nks);
        for (int i = 0;i < 18;i++){
            if (id == nks[i]){
                return true;
            }
        }
        return false;
    }


}
