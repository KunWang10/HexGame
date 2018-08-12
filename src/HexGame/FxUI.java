package HexGame;

/**
 * Created by u5780527 on 15/10/15.
 */
import javafx.animation.Interpolator;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.geometry.VPos;
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
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * Created by admin on 2015/10/15.
 *
 * This class is the template for the beginning state of the game
 */
public class FxUI extends Application {



    @Override
    public void start(Stage primaryStage) throws Exception {
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

        Text text1=new Text();
        text1.setLayoutY(100);
        text1.textOriginProperty().set(VPos.TOP);
        text1.textAlignmentProperty().set(TextAlignment.JUSTIFY);
        text1.wrappingWidthProperty().set(700);
        text1.setText(message);
        text1.setFill(Color.rgb(187, 195, 107));
        text1.setFont(Font.font("SansSerif", FontWeight.BOLD, 24));

        TranslateTransition translateTransition=new TranslateTransition(new Duration(20000),text1);
        translateTransition.toYProperty().set(-820);
        translateTransition.setInterpolator(Interpolator.LINEAR);
        translateTransition.setCycleCount(Timeline.INDEFINITE);
        translateTransition.play();

        Rectangle rect=new Rectangle(700,700,Color.ROYALBLUE);

        // rect.setFill();

        Group root=new Group();
        Scene scene=new Scene(root,700,700);

        Button start0=new Button("Start");
        start0.setLayoutX(350);
        start0.setLayoutY(600);
        start0.setOnMouseClicked(event -> {

            primaryStage.setScene(scene);
        });

        Image image=new Image("comp1110/ass2/cosmetic.jpg");
        ImageView imageView=new ImageView(image);
        Group root0=new Group();
        root0.getChildren().add(rect);
        root0.getChildren().add(imageView);
        root0.getChildren().add(text1);
        root0.getChildren().add(start0);
        root0.setClip(new Rectangle(700, 700));
        Scene scene0=new Scene(root0,700,700);
        //scene0.getStylesheets().add("Assign/try");
        scene0.getStylesheets().add("comp1110/ass2/Fx");

        primaryStage.setScene(scene0);
        primaryStage.show();


    }

    public static void main(String[] args) {
        launch(args);
    }
}
