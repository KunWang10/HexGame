package HexGame;

import javafx.animation.FillTransition;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * Created by admin on 2015/9/26.
 *
 *  This is just the template for testing how to give the player a hint for the next step

 *
 */
public class FxHints extends Application{

    private double piecex,piecey;
    @Override
    public void start(Stage primaryStage) throws Exception {

        class Dot extends Circle {

            private int num;

            public int getNum(){ return num; }

            private int row;

            public int getRow(){ return row; }

            private  int column;

            public int getColumn(){ return column; }

            Dot(int num,int row,int column){

                this.num=num;
                this.row=row;
                this.column=column;

                this.setFill(null);
                this.setRadius(5);
                this.setCenterX(10);
                this.setCenterY(10);
            }

        }

        primaryStage.setTitle("button");
        Group root=new Group();
        Scene scene=new Scene(root,500,500);

        primaryStage.setScene(scene);


        Piece piece=new Piece(3, Color.ROYALBLUE,0);
        piece.setLayoutX(33);
        piece.setLayoutY(55);

        Button button=new Button("Hint");
        button.setLayoutX(450);
        button.setLayoutY(50);

        Dot dot=new Dot(3,333,222);

        dot.setLayoutX(dot.getRow());
        dot.setLayoutY(dot.getColumn());

        button.setOnMousePressed(event -> {
            piecex = piece.getRow();
            piecey = piece.getColumn();

            FillTransition ft = new FillTransition(Duration.millis(400),
                    dot, Color.OLIVE, Color.LIGHTBLUE);

            ft.setCycleCount(4);
            ft.setAutoReverse(true);
            ft.play();


        });

        root.getChildren().add(button);
        root.getChildren().add(piece);
        root.getChildren().add(dot);

        primaryStage.show();



    }

    public static void main(String[] args) {
        launch(args);
    }


}
