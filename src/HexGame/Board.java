package HexGame;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;



/**
 * Created by wangkun on 26/09/15.
 *
 * This class is for creating the black border of the hexagon
 *
 */
public class Board extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {

        // primaryStage.setTitle("Nook");
        Group root=new Group();
        Scene scene=new Scene(root,1000,1000);
        primaryStage.setScene(scene);

        FxCranny cranny = new FxCranny("171");
        FxNook nook=new FxNook("108E");

        //root.getChildren().add(cranny);

        //root.getChildren().add(nook);

        String[] str = {"169A","177B","185C","193D","201E","209F"};
        for (int i = 169;i < 217;i++){
            if (i % 8 == 1){
                FxNook fxNook = new FxNook(str[i/8 - 21]);
                fxNook.setFill(Color.BLACK);
                root.getChildren().add(fxNook);
            } else {
                Fxside fxside = new Fxside(i);
                fxside.setFill(Color.BLACK);
                root.getChildren().add(fxside);
            }
        }

        primaryStage.show();

    }
}

