package HexGame;

import javafx.application.Application;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polyline;
import javafx.stage.Stage;

/**
 * Created by admin on 2015/9/26.
 *
 * This is just the template for testing how to drag the piece to move
 */
public class FxMoves extends Application {

    double mousex,mousey,piecex,piecey;
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Move");

        Group root=new Group();
        Scene scene=new Scene(root,500,500);
        primaryStage.setScene(scene);

        Color color=Colors.getColors();

        //piece 值 暂时赋值为静态
        Piece piece=new Piece(20,color,0);

        piece.setOnMousePressed(event -> {
            mousex = event.getSceneX();
            mousey = event.getSceneY();
            piecex = piece.getLayoutX();
            piecey = piece.getLayoutY();

        });

        Polyline polyline=new Polyline();
        polyline.setStroke(color);

        piece.setOnMousePressed(event -> {
            piecex = piece.getLayoutX();
            piecey = piece.getLayoutY();

            polyline.getPoints().add(piecex);
            polyline.getPoints().add(piecey);
        });

        piece.setOnMouseReleased(event -> {
            piecex = piece.getLayoutX();
            piecey = piece.getLayoutY();

            polyline.getPoints().add(piecex);
            polyline.getPoints().add(piecey);
        });


        piece.setOnMouseDragged(event -> {

            piecex = piece.getLayoutX();
            piecey = piece.getLayoutY();

            double mouseOffsetx = event.getSceneX() - mousex;
            double mouseOffsety = event.getSceneY() - mousey;
            piecex += mouseOffsetx;
            piecey += mouseOffsety;
            piece.setLayoutX(piecex);
            piece.setLayoutY(piecey);

            mousex = event.getSceneX();
            mousey = event.getSceneY();
            event.consume();

        });


        piece.setOnMouseEntered(event -> {
            scene.setCursor(Cursor.HAND);
        });

        piece.setOnMouseExited(event -> {
            scene.setCursor(Cursor.DEFAULT);
        });





        root.getChildren().add(piece);

        root.getChildren().add(polyline);

        System.out.println(polyline);
        primaryStage.show();

    }



    public static void main(String[] args) {
        launch(args);
    }
}
