package HexGame;

import java.util.Arrays;
import java.util.List;

/**
 * Created by admin on 2015/8/31.
 *
 * This class is creating the right cranny for using in the backstage
 */
public class Cranny {

    private  int row;

    public int getRow(){ return row; }

    private  int column;

    public int getColumn(){ return column; }

    public Cranny(int row, int column){
        this.row = row;
        this.column = column;
    }

    //judging whether id is a cranny or not
    public static boolean cra(String game,int id){
        int [] cras=new int[6];
        Node.createCra(game, cras);
        for(int i=0;i<6;i++){
            if(id==cras[i]){
                return true;
            }
        }
        return false;
    }
    //create crannies
    public static void createCra(String game,int[] cra){
        for (int i = 0; i < 6; i++) {
            cra[i] = Integer.parseInt(game.substring(3 * i, 3 * i + 3));
        }
    }

    public static List<Integer> createCra(String game){
        Integer cra[]=new Integer[6];
        for (int i = 0; i < 6; i++) {
            cra[i] = Integer.parseInt(game.substring(3 * i, 3 * i + 3));
        }
        return Arrays.asList(cra);

    }



    // given a direction and id to judge whether cranny can get out of the cranny or not
    public static boolean cout(String game,int id,int drc){
        int[] cra = new int[6];
        Node.createCra(game, cra);

        for (int i = 0;i < 6; i++){
            for (int j = i+1;j < 6;j++){
                if (cra[i] > cra[j]){
                    int k = cra[i];
                    cra[i] = cra[j];
                    cra[j] = k;
                }
            }
        }

        if (id == cra[0] && (drc == 4 || drc == 5 || drc == 6)){
            return true;
        }else if (id == cra[0] + 1 && (drc == 3 || drc == 4 || drc == 5)){
            return true;
        }else if (id == cra[1] && (drc == 1 || drc == 5 || drc == 6)){
            return true;
        }else if (id == cra[1] + 1 && (drc == 4 || drc == 5 || drc == 6)){
            return true;
        }else if (id == cra[2] && (drc == 1 || drc == 2 || drc == 6)){
            return true;
        }else if (id == cra[2] + 1 && (drc == 1 || drc == 5 || drc == 6)){
            return true;
        }else if (id == cra[3] && (drc == 1 || drc == 2 || drc == 3)){
            return true;
        }else if (id == cra[3] + 1 && (drc == 1 || drc == 2 || drc == 6)){
            return true;
        }else if (id == cra[4] && (drc == 2 || drc == 3 || drc == 4)){
            return true;
        }else if (id == cra[4] + 1 && (drc == 1 || drc == 2 || drc == 3)){
            return true;
        }else if (id == cra[5] && (drc == 3 || drc == 4 || drc == 5)){
            return true;
        }else if (id == cra[5] + 1 && (drc == 2 || drc == 3 || drc == 4)){
            return true;
        }else {
            return false;
        }
    }


    //through id to judge whether its encounter is a cranny or not
    public static boolean c(String game,int id){
        int[] cra = new int[6];
        Node.createCra(game, cra);
        for (int i = 0;i < 6;i++){
            if (id == cra[i] || id == cra[i] + 1)
                return true;
        }
        return false;
    }
}
