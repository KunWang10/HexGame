package HexGame;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * Created by admin on 2015/10/1.
 *
 * This class is a template of popup window
 *
 * if you click the button or do other event it will using the popup window tell the players how they are doing
 */
public class FXPopUpWindow extends Application {

    @Override
    public void start(Stage primaryStage) {

        final Button b2 = new Button("  O K  ");

        b2.setOnAction(new EventHandler<ActionEvent>() {//�����
            public void handle(ActionEvent e) {
                Stage stage = new Stage();
                Label l = new Label("");
                Scene s = new Scene(l, 150, 170);
                s.getStylesheets().add("comp1110/ass2/Fx");
                stage.setScene(s);
                stage.show();
            }
        });
        StackPane root = new StackPane();
        root.getChildren().add(b2);


        Scene scene = new Scene(root, 500, 350);


        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
