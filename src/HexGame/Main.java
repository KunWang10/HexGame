package HexGame;

import javafx.animation.Interpolator;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;


/**
 * Created by wangkun on 7/11/2017.
 */
public class Main extends Application {


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("HexGame");
        Group root = new Group();
        Scene scene = new Scene(root, 700, 700);
        primaryStage.setScene(scene);
        //scene.getStylesheets().add("comp1110/ass2/Fx");
        Rectangle rw = new Rectangle(2000, 2000);
        rw.setFill(Color.LIGHTBLUE);
        rw.setOpacity(0.2);
        root.getChildren().add(rw);


/**+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++*/


        Image image = new Image("HexGame/../img/background.jpg");
        ImageView imageView = new ImageView(image);
        root.getChildren().add(imageView);
        root.setClip(new Rectangle(2000, 2000));


        String message =
                "    Rules of game:this game consists of one or more rounds, where each round consists of the player" +
                        "/s trying to find a particular path. Each subsequent round starts with the pieces " +
                        "in the positions they were left in according to the winning solution in the preceding round. " +
                        "When there is more than one player, the players will be given a fixed time, " +
                        "and as soon as a player finds a solution, they announce to the other players " +
                        "how many moves their solution will take (their announcement may be wrong, but it will be tested). " +
                        "The round continues until the time has run out. If anyone finds a shorter solution, " +
                        "they announce how many moves they can solve it in, and this continues until the time expires. " +
                        "Once the time has expired, the player with who announced the smallest number of moves " +
                        "must demonstrate their solution (using the mouse to indicate the path). " +
                        "If correct, that player wins. If not, the pieces are put back and the player " +
                        "with the next best number of moves provides their solution, and if correct, they win, and so on.";

        Text welcome = new Text();
        welcome.setText(message);
        welcome.setLayoutX(50);
        welcome.setLayoutY(100);
        welcome.wrappingWidthProperty().set(600);
        welcome.setFill(Color.rgb(187, 195, 107));


//        welcome.textOriginProperty().set(VPos.TOP);
//        welcome.textAlignmentProperty().set(TextAlignment.JUSTIFY);
        welcome.setFont(Font.font("Verdana", FontWeight.BOLD, 24));

        TranslateTransition translateTransition = new TranslateTransition(new Duration(20000), welcome);
        translateTransition.toYProperty().set(-820);
        translateTransition.setInterpolator(Interpolator.LINEAR);
        translateTransition.setCycleCount(Timeline.INDEFINITE);


        translateTransition.play();


        root.getChildren().add(welcome);


        Button pause = new Button("Pause");
        pause.setLayoutX(150);
        pause.setLayoutY(650);
        Button play = new Button("Play");
        play.setLayoutX(150);
        play.setLayoutY(650);
        Button entryGame = new Button("Play the Game!!!");
        entryGame.setLayoutX(250);
        entryGame.setLayoutY(650);
        root.getChildren().add(pause);


        root.getChildren().add(entryGame);


        pause.setOnMouseClicked(event -> {
            translateTransition.pause();
            root.getChildren().add(play);
            root.getChildren().remove(pause);
        });

        play.setOnMouseClicked(event -> {
            translateTransition.play();
            root.getChildren().add(pause);
            root.getChildren().remove(play);
        });

        Button singlePiece = new Button("Single Piece Game");
        singlePiece.setLayoutX(200);
        singlePiece.setLayoutY(500);
        Button multiplePiece = new Button("Multiple Piece Game");
        multiplePiece.setLayoutX(400);
        multiplePiece.setLayoutY(500);

        entryGame.setOnMouseClicked(event -> {
            root.getChildren().remove(welcome);
            root.getChildren().remove(play);
            root.getChildren().remove(pause);
            root.getChildren().remove(entryGame);

            root.getChildren().add(singlePiece);
            root.getChildren().add(multiplePiece);
            //entryGame.setLayoutX(-1000);
        });

        Text active = new Text(" ");

        singlePiece.setOnMouseClicked(event -> {
            root.getChildren().remove(entryGame);
            root.getChildren().remove(singlePiece);
            root.getChildren().remove(multiplePiece);
            root.getChildren().add(active);
//            singlePiece.setLayoutX(-1000);
        });
        multiplePiece.setOnMouseClicked(event -> {
            root.getChildren().remove(entryGame);
            root.getChildren().remove(singlePiece);
            root.getChildren().remove(multiplePiece);
            root.getChildren().add(active);

//            multiplePiece.setLayoutX(-1000);
        });




/**+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++*/
        primaryStage.show();
        System.out.println("Window is set");


    }
}
