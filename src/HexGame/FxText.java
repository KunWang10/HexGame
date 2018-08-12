package HexGame;
/**
 * Created by admin on 2015/10/14.
 */

import javafx.application.Application;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.effect.InnerShadow;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class FxText extends Application {

    String s;
    String s1;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Group root = new Group();
        Scene scene = new Scene(root, 500, 500);
        InnerShadow is = new InnerShadow();
        is.setOffsetX(4.0f);
        is.setOffsetY(4.0f);



        Text t = new Text();

        Text ttext = new Text();
        TextField textField = new TextField();
        textField.setText("name");
        textField.setPrefColumnCount(4);

        ttext.setEffect(is);


        ttext.setFont(Font.font(null, FontWeight.BOLD, 40));

        ttext.textProperty().bind(t.textProperty());

        ttext.setY(250);

        ttext.textOriginProperty().set(VPos.TOP);
        ttext.textAlignmentProperty().set(TextAlignment.JUSTIFY);
        ttext.wrappingWidthProperty().set(400);

        ttext.setEffect(is);
        ttext.setFill(Color.ALICEBLUE);
        ttext.setFont(Font.font(null, FontWeight.BOLD, 25));

        textField.setOnKeyPressed(event -> {

            if (event.getCode() == KeyCode.ENTER) {

                if (s == null || s.equals("name")) {
                    s = "";
                }
                s = s + "\n" + textField.getText();
                textField.setText("");
                t.setText(s);
            }
        });

//=========================================================

        Text t1 = new Text();

        Text ttext1 = new Text();
        TextField textField1 = new TextField();
        textField1.setText("steps");
        textField1.setLayoutX(80);
        textField1.setPrefColumnCount(4);

        ttext1.setEffect(is);

        ttext1.setFont(Font.font(null, FontWeight.BOLD, 40));

        ttext1.textProperty().bind(t1.textProperty());

        ttext1.setY(250);
        ttext1.setX(50);

        ttext1.textOriginProperty().set(VPos.TOP);
        ttext1.textAlignmentProperty().set(TextAlignment.JUSTIFY);
        ttext1.wrappingWidthProperty().set(400);

        ttext1.setEffect(is);
        ttext1.setFill(Color.ALICEBLUE);
        ttext1.setFont(Font.font(null, FontWeight.BOLD, 25));

        textField1.setOnKeyPressed(event -> {

            if (event.getCode() == KeyCode.ENTER) {

                if (s1 == null || s1.equals("steps")) {
                    s1 = "";
                }
                s1 = s1 + "\n" + textField1.getText();
                textField1.setText("");
                t1.setText(s1);
            }
        });


        root.getChildren().addAll(ttext, textField);
        root.getChildren().addAll(ttext1,textField1);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}



