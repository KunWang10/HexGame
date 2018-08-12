package HexGame;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.VPos;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.media.Media;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polyline;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.net.URL;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by admin on 22/09/15.
 */
public class Main extends Application {

    int before, after, start, target, steps, buttonnumbers, minimalcount;
    double mousex, mousey, piecex, piecey;
    String game, crannys, nooks, pieces, recording;
    String s, s1;
    Text pathss;


    Text mysteps;
    Polyline polyline, polyline1;
    ArrayList<Point> points = new ArrayList<Point>();
    ArrayList<ArrayList<Point>> mpoints = new ArrayList<ArrayList<Point>>();


    //+++++++++++++++++++++++++++++++++++


    double[] mmousex = new double[4];
    double[] mmousey = new double[4];
    double[] mpiecex = new double[4];
    double[] mpiecey = new double[4];
    int[] starts = new int[4];
    int[] befores = new int[4];
    int[] afters = new int[4];
    int[] stepps = new int[4];

    //++++++++++++++++++++++++++++


    int flag = 0;

    private Timeline timeline;
    private Label timerLabel = new Label();
    private DoubleProperty timeSeconds = new SimpleDoubleProperty();
    private Duration time = Duration.seconds(60.0);

    public static void main(String[] args) {
        launch(args);
    }

    public static String ToThreeString(int n) {
        String string = "" + n;
        for (int i = 0; i < 2; i++) {
            if (string.length() < 3) {
                string = 0 + string;
            }
        }
        return string;
    }

    public static void CreatePieces(String pieces, Piece[] piecess, Group root, int[] starts) {
        int piecenum = pieces.length() / 3;
        Color[] colors = {Color.DEEPSKYBLUE, Color.VIOLET, Color.LIGHTSEAGREEN, Color.YELLOW};
        for (int i = 0; i < piecenum; i++) {
            int num = Integer.parseInt(pieces.substring(3 * i, 3 * i + 3));
            starts[i] = num;
            piecess[i] = new Piece(num, colors[i], i);
        }
        root.getChildren().addAll(piecess);
    }

    public static void CreatePolilines(Polyline[] polylines, Group root) {
        Color[] colors = {Color.DEEPSKYBLUE, Color.VIOLET, Color.LIGHTSEAGREEN, Color.YELLOW};
        for (int i = 0; i < 4; i++) {
            polylines[i] = new Polyline();
            polylines[i].setStrokeWidth(4);
            polylines[i].setStroke(colors[i]);
            polylines[i].setOpacity(0.5);
        }
        root.getChildren().addAll(polylines);
    }
    public static void CreateCrannys(FxCranny[] fxCrannies,String crannys, Group root){
        for (int i = 0;i < 6;i++){
            String str = crannys.substring(3*i,3*i+3);
            fxCrannies[i] = new FxCranny(str);
            fxCrannies[i].setFill(Color.BLACK);
        }
        root.getChildren().addAll(fxCrannies);
    }
    public static void CreateNooks(FxNook[] fxNooks,String nooks, Group root){
        for (int i = 0;i < 18;i++){
            String str = nooks.substring(4*i,4*i+4);
            fxNooks[i] = new FxNook(str);
            fxNooks[i].setFill(Color.BLACK);
            root.getChildren().add(fxNooks[i]);
        }
    }
    public static void CreateNodes(ArrayList<Hexagon> hexagons, ArrayList<Text> texts,Group root){
        for (Hexagon hexagon : hexagons){
            String nums =String.valueOf(hexagon.getNum());
            Text text = new Text(nums);
            text.setLayoutX(hexagon.getLayoutX());
            text.setLayoutY(hexagon.getLayoutY());
            text.setFont(Font.font("Tahoma", FontWeight.NORMAL, 12));
            text.setFill(Color.BLACK);
            texts.add(text);
            hexagon.setFill(Color.ALICEBLUE);
            hexagon.setOnMouseEntered(event -> {
                hexagon.setFill(Color.LIGHTGREEN);
            });
            hexagon.setOnMouseExited(event -> {
                hexagon.setFill(Color.ALICEBLUE);
            });
        }
        root.getChildren().addAll(hexagons);
        //root.getChildren().addAll(texts);
    }

    private static int MakeMinimalpath(String game, int start, int target) {
        int[] minimalPath = HexGame.minimalPath(game, start, target);
        return (minimalPath.length - 1);
    }

    private static Text MakeMinimalpath(String game, int start, int target, Polyline polyline1, Piece answer) {
        int[] minimalPath = HexGame.minimalPath(game, start, target);
        for (int i : minimalPath) {

            answer.refresh(i);

            polyline1.getPoints().add(answer.getLayoutX());
            polyline1.getPoints().add(answer.getLayoutY());
        }
        Text path = new Text("The minimal path has " + (minimalPath.length - 1) + " steps");
        path.setFont(Font.font("Tahoma", FontWeight.NORMAL, 16));
        path.setLayoutX(450);
        path.setLayoutY(525);
        return path;
    }

    public static void CreateSides(Group root){
        //draw sides
        String[] ang = {"169A","177B","185C","193D","201E","209F"};
        for (int i = 169;i < 217;i++){
            if (i % 8 == 1){
                FxNook fxNook = new FxNook(ang[i/8 - 21]);
                fxNook.setFill(Color.BLACK);
                root.getChildren().add(fxNook);
            } else {
                Fxside fxside = new Fxside(i);
                fxside.setFill(Color.BLACK);
                root.getChildren().add(fxside);
            }
        }
    }

    private static void CreateNodes(ArrayList<Hexagon> hexagons, ArrayList<Text> texts, int target, Group root) {
        for (Hexagon hexagon : hexagons) {
            String nums = String.valueOf(hexagon.getNum());
            Text text = new Text(nums);
            text.setLayoutX(hexagon.getLayoutX());
            text.setLayoutY(hexagon.getLayoutY());
            text.setFont(Font.font("Tahoma", FontWeight.NORMAL, 12));
            text.setFill(Color.BLACK);
            texts.add(text);
            hexagon.setFill(Color.ALICEBLUE);
            hexagon.setOnMouseEntered(event -> {
                hexagon.setFill(Color.LIGHTGREEN);
            });
            hexagon.setOnMouseExited(event -> {
                hexagon.setFill(Color.ALICEBLUE);
            });
            root.getChildren().add(hexagon);
        }
    }

    private static int setTarget(String nooks, String pieces) {
        int r, s;
        Random random = new Random();
        int start = Integer.parseInt(pieces);
        do {
            r = random.nextInt(18);
            s = Integer.parseInt(nooks.substring(4 * r, 4 * r + 3));
        } while (s == start);
        for (int i = 0; i < 18; i++) {
            String str = nooks.substring(4 * i, 4 * i + 4);
            FxNook fxNook = new FxNook(str);
            if (i == r) {
                return fxNook.getNum();
            }
        }
        return -1;
    }


    private static void CreateSide(Group root) {
        String[] ang = {"169A", "177B", "185C", "193D", "201E", "209F"};
        for (int i = 169; i < 217; i++) {
            if (i % 8 == 1) {
                FxNook fxNook = new FxNook(ang[i / 8 - 21]);
                fxNook.setFill(Color.BLACK);
                root.getChildren().add(fxNook);
            } else {
                Fxside fxside = new Fxside(i);
                fxside.setFill(Color.BLACK);
                root.getChildren().add(fxside);
            }
        }
    }

    public static String MinimalPath(String game, int[] start, int target) {
        String kun = "";
        String pieces = game.substring(90);
        game = game.substring(0, 90);

        String g, p;

        ArrayList<ArrayList<Recording>> Path = new ArrayList<ArrayList<Recording>>();
        ArrayList<Recording> rzero = new ArrayList<Recording>();
        Point[] Ancestry = new Point[4];
        ArrayList<Point> allpoints = new ArrayList<Point>();

        for (int i = 0; i < 4; i++) {
            Ancestry[i] = new Point(start[i], null);
            rzero.add(new Recording(pieces, Ancestry[i], i, null));
            allpoints.add(Ancestry[i]);
        }

        Path.add(rzero);


        for (int i = 0; i < Path.size(); i++) {

            //this is the new list to store new points,Path.get(i＋1)
            ArrayList<Recording> sons = new ArrayList<Recording>();
            Path.add(sons);
            //this is the jth start-point in list i
            //每一个ij储存一个状态，四个球各动一次

            for (int j = 0; j < Path.get(i).size(); j++) {
                g = game + Path.get(i).get(j).getPieces();
                for (int k = 0; k < 4; k++) {
                    int parentvalue = Integer.parseInt(Path.get(i).get(j).getPieces(k));//第i步第j个点第k个piece位置
                    for (int d = 1; d < 7; d++) {
                        int childrenvalue = Node.move(g, parentvalue, d);
                        boolean flag = true;
                        for (int l = 0; l < allpoints.size(); l++) {
                            if (allpoints.get(l).getValue() == childrenvalue) {
                                flag = false;
                            }
                        }
                        if (flag) {


                            Point child = new Point(childrenvalue, Path.get(i).get(j).getPoint());
                            p = "";
                            for (int l = 0; l < 4; l++) {
                                if (l == k) {
                                    p += Main.ToThreeString(childrenvalue);
                                } else {
                                    p += Path.get(i).get(j).getPieces(l);
                                }
                            }

                            Recording kid = new Recording(p, child, k, Path.get(i).get(j));
                            sons.add(kid);
                            allpoints.add(child);
                        }
                        if (childrenvalue == target) {
                            int length = Path.size();
                            int size = Path.get(i + 1).size();
                            Recording[] recordings = new Recording[length];
                            Recording recording = Path.get(i + 1).get(size - 1);
                            for (int l = length - 1; l >= 0; l--) {
                                recordings[l] = recording;
                                kun += recordings[l].getWho();
                                kun += Main.ToThreeString(recordings[l].getPoint().getValue());
                                if (l != 0) {
                                    Recording.findparent(recording);
                                }

                            }


                            return kun;
                        }
                    }
                }
            }
        }

        return kun;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        //set window
        //primaryStage.setAlwaysOnTop(true);
        primaryStage.setTitle("HexGame");
        Group root = new Group();
        Scene scene = new Scene(root, 700, 700);
        primaryStage.setScene(scene);
        scene.getStylesheets().add("HexGame/Fx");
        Rectangle rw = new Rectangle(2000, 2000);
        rw.setFill(Color.LIGHTBLUE);
        rw.setOpacity(0.2);
        root.getChildren().add(rw);


        //+++++++++++++++++++++++++++++++

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

        Text text1 = new Text();
        text1.setLayoutY(100);
        text1.textOriginProperty().set(VPos.TOP);
        text1.textAlignmentProperty().set(TextAlignment.JUSTIFY);
        text1.wrappingWidthProperty().set(700);
        text1.setText(message);
        text1.setFill(Color.rgb(187, 195, 107));
        text1.setFont(Font.font("SansSerif", FontWeight.BOLD, 24));

        TranslateTransition translateTransition = new TranslateTransition(new Duration(20000), text1);
        translateTransition.toYProperty().set(-820);
        translateTransition.setInterpolator(Interpolator.LINEAR);
        translateTransition.setCycleCount(Timeline.INDEFINITE);
        translateTransition.play();


        Image image = new Image("/HexGame/cosmetic.jpeg");
        ImageView imageView = new ImageView(image);
        root.getChildren().add(imageView);
        root.getChildren().add(text1);
        root.setClip(new Rectangle(2000, 2000));


        //+++++++++++++++++++++++++++++++


        Button singlepiece = new Button("Single-Piece");
        singlepiece.setLayoutX(150);
        singlepiece.setLayoutY(550);

        Button multipiece = new Button("Multi-Pieces");
        multipiece.setLayoutX(350);
        multipiece.setLayoutY(550);


        singlepiece.setOnMouseClicked(event1 -> {

            root.getChildren().remove(singlepiece);
            root.getChildren().remove(multipiece);


            root.getChildren().remove(imageView);
            root.getChildren().remove(text1);


            //+++++++++++++++++++++++++++++++++++++++++++++++++++++


            InnerShadow is = new InnerShadow();
            is.setOffsetX(4.0f);
            is.setOffsetY(4.0f);


            ArrayList<String> names = new ArrayList<String>();
            Text t = new Text();

            Text ttext = new Text();
            TextField textField = new TextField();
            textField.setText("name");
            textField.setPrefColumnCount(4);

            ttext.setEffect(is);


            ttext.setFont(Font.font(null, FontWeight.BOLD, 40));

            ttext.textProperty().bind(t.textProperty());

            ttext.setLayoutY(150);

            ttext.textOriginProperty().set(VPos.TOP);
            ttext.textAlignmentProperty().set(TextAlignment.JUSTIFY);
            ttext.wrappingWidthProperty().set(400);

            ttext.setEffect(is);
            ttext.setFill(Color.ALICEBLUE);
            ttext.setFont(Font.font(null, FontWeight.BOLD, 16));

            textField.setOnKeyPressed(event -> {

                if (event.getCode() == KeyCode.ENTER) {

                    if (s == null || s.equals("name")) {
                        s = "";
                    }
                    s = s + "\n" + textField.getText();
                    textField.setText("");
                    t.setText(s);
                    names.add(s);
                }
            });

//=========================================================

            ArrayList<Integer> stps = new ArrayList<Integer>();
            Text t1 = new Text();

            Text ttext1 = new Text();
            TextField textField1 = new TextField();
            textField1.setText("steps");
            textField1.setLayoutX(80);
            textField1.setPrefColumnCount(4);

            ttext1.setEffect(is);

            ttext1.setFont(Font.font(null, FontWeight.BOLD, 16));

            ttext1.textProperty().bind(t1.textProperty());

            ttext1.setLayoutY(150);
            ttext1.setLayoutX(100);

            ttext1.textOriginProperty().set(VPos.TOP);
            ttext1.textAlignmentProperty().set(TextAlignment.JUSTIFY);
            ttext1.wrappingWidthProperty().set(400);

            ttext1.setEffect(is);
            ttext1.setFill(Color.ALICEBLUE);
            ttext1.setFont(Font.font(null, FontWeight.BOLD, 16));

            textField1.setOnKeyPressed(event -> {

                if (event.getCode() == KeyCode.ENTER) {

                    if (s1 == null || s1.equals("steps")) {
                        s1 = "";
                    }
                    s1 = s1 + "\n" + textField1.getText();
                    textField1.setText("");
                    t1.setText(s1);
                    stps.add(Integer.valueOf(s1));
                }
            });


            root.getChildren().addAll(ttext, textField);
            root.getChildren().addAll(ttext1, textField1);

            Button finish = new Button("Finish");
            Button add = new Button("Add");
            finish.setLayoutX(0);
            finish.setLayoutY(50);
            finish.setOnMouseClicked(event2 -> {
                root.getChildren().removeAll(textField, textField1);
                root.getChildren().add(add);
                root.getChildren().remove(finish);
            });
            root.getChildren().add(finish);

            add.setLayoutX(0);
            add.setLayoutY(50);
            add.setOnMouseClicked(event2 -> {
                root.getChildren().addAll(textField, textField1);
                root.getChildren().add(finish);
                root.getChildren().remove(add);
            });

            Text nn = new Text("Name");
            nn.setLayoutX(0);
            nn.setLayoutY(150);
            nn.textOriginProperty().set(VPos.TOP);
            nn.textAlignmentProperty().set(TextAlignment.JUSTIFY);
            nn.wrappingWidthProperty().set(400);
            nn.setEffect(is);
            nn.setFill(Color.ALICEBLUE);
            nn.setFont(Font.font(null, FontWeight.BOLD, 16));
            root.getChildren().add(nn);

            Text ss = new Text("Steps");
            ss.setLayoutX(80);
            ss.setLayoutY(150);
            ss.textOriginProperty().set(VPos.TOP);
            ss.textAlignmentProperty().set(TextAlignment.JUSTIFY);
            ss.wrappingWidthProperty().set(400);
            ss.setEffect(is);
            ss.setFill(Color.ALICEBLUE);
            ss.setFont(Font.font(null, FontWeight.BOLD, 16));
            root.getChildren().add(ss);


            //+++++++++++++++++++++++++++++++++++++++++++++++++++++

            //input game map
            game = "171178187194205215" + "093D038D064E070C100D043D106A108F072A080A051D112F082B016C118D060D125B122D" + "093";
            game = ConstructionHex.getGame();
            recording = game;
            crannys = game.substring(0, 18);
            nooks = game.substring(18, 90);
            pieces = game.substring(90);
            steps = 0;
            int num = Integer.parseInt(pieces);

            //wangkun

            ArrayList<Hexagon> hexagons = new ArrayList<Hexagon>();
            Hexagon.createHexagons(hexagons);
            ArrayList<Text> texts = new ArrayList<Text>();
            FxCranny[] fxCrannies = new FxCranny[6];
            FxNook[] fxNooks = new FxNook[18];
            Main.CreateSide(root);
            Main.CreateCrannys(fxCrannies, crannys, root);
            Main.CreateNooks(fxNooks, nooks, root);
            start = Integer.parseInt(pieces);
            target = Main.setTarget(nooks, pieces);
            final Flag[] f = {new Flag(target)};
            Main.CreateNodes(hexagons, texts, target, root);


            points.add(new Point(start, null));
            mysteps = new Text("My path has " + steps + " steps");
            mysteps.setFont(Font.font("Tahoma", FontWeight.NORMAL, 16));
            mysteps.setLayoutX(450);
            mysteps.setLayoutY(500);
            root.getChildren().add(mysteps);


            Piece piece = new Piece(num, Color.BLUE, 0);
            Piece answer = new Piece(num, Color.GREEN, 0);
            polyline = new Polyline();
            polyline.setStrokeWidth(4);
            polyline.setStroke(piece.getFill());
            polyline1 = new Polyline();
            polyline1.setStrokeWidth(4);
            polyline1.setStroke(answer.getFill());
            final Text[] path = {Main.MakeMinimalpath(game, start, target, polyline1, answer)};


            piece.setOnMousePressed(event -> {

                before = piece.findnode();
                mousex = event.getSceneX();
                mousey = event.getSceneY();
                piecex = piece.getLayoutX();
                piecey = piece.getLayoutY();

                polyline.getPoints().add(piecex);
                polyline.getPoints().add(piecey);

            });


            piece.setOnMouseReleased(event -> {

                after = piece.findnode();
                if (HexGame.legitimateStep(game, before, after)) {
                    piece.refresh(after);
                    pieces = piece.getNumString();
                    game = crannys + nooks + pieces;// 四个球的时候需要改
                    if (after != before) {
                        points.add(new Point(after, points.get(steps)));
                        steps++;
                        if (after == target) {
                            piece.refresh(piece.getNum());
                            root.getChildren().remove(piece);
                            timeline.stop();
                            primaryStage.setFullScreen(true);
                            primaryStage.setFullScreen(false);
                            Stage stage = new Stage();
                            Label l = new Label("");
                            Scene s = new Scene(l, 300, 300);
                            s.getStylesheets().add("HexGame/Fx");
                            stage.setScene(s);
                            stage.show();
                            s.getStylesheets().add("HexGame/Fx");
                            stage.setFullScreen(true);

                            s.getStylesheets().add("HexGame/Fx");
                            stage.setFullScreen(false);
                            stage.setFullScreen(true);
                            s.getStylesheets().add("HexGame/Fx");
                            s.getStylesheets().add("HexGame/Fx");
                            stage.setFullScreen(false);

                            s.getStylesheets().add("HexGame/Fx");
                            stage.close();
                        }
                    }
                } else {
                    piece.refresh(before);
                }
                for (Hexagon hexagon : hexagons) {
                    if (hexagon.getFill() != Color.ALICEBLUE) {
                        hexagon.setFill(Color.ALICEBLUE);
                    }
                }
                int pieceNum = piece.getNum();
                for (int i = 1; i < 7; i++) {
                    int x = Node.move(game, pieceNum, i);
                    Hexagon.findHexagon(hexagons, x).setFill(Color.LIGHTGREEN);
                }

                piecex = piece.getLayoutX();
                piecey = piece.getLayoutY();

                polyline.getPoints().add(piecex);
                polyline.getPoints().add(piecey);

                mysteps.setText("My path has " + steps + " steps");


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
                int pieceNum = piece.getNum();
                for (int i = 1; i < 7; i++) {
                    int x = Node.move(game, pieceNum, i);
                    Hexagon.findHexagon(hexagons, x).setFill(Color.LIGHTGREEN);
                }
            });

            piece.setOnMouseExited(event -> {
                scene.setCursor(Cursor.DEFAULT);
                int pieceNum = piece.getNum();
                for (int i = 1; i < 7; i++) {
                    int x = Node.move(game, pieceNum, i);
                    Hexagon.findHexagon(hexagons, x).setFill(Color.ALICEBLUE);
                }
            });

            Button minimal = new Button("MinimalPath");
            minimal.setLayoutX(500);
            minimal.setLayoutY(550);

            Button clock = new Button("Start/Stop");
            clock.setLayoutX(50);
            clock.setLayoutY(500);

            timeSeconds.set(time.toSeconds());
            timerLabel.textProperty().bind(timeSeconds.asString());

            timerLabel.setTextFill(Color.BLUE);

            timerLabel.setLayoutX(50);
            timerLabel.setLayoutY(400);


            final URL resource = getClass().getResource("Piano.mp3");
            final Media media = new Media(resource.toString());
            //       final MediaPlayer mediaPlayer = new MediaPlayer(media);
//        mediaPlayer.play();


            clock.setOnMouseClicked(event -> {

                if (flag == 0) {
                    root.getChildren().add(piece);
                }

                if (timeline != null && flag % 2 != 0) {

                    timeline.stop();
                    flag++;
                } else if (timeline == null && flag % 2 == 0) {

                    timeline = new Timeline(
                            new KeyFrame(Duration.millis(100), ae -> {
                                Duration duration = ((KeyFrame) ae.getSource()).getTime();
                                time = time.subtract(duration);
                                timeSeconds.set(time.toSeconds());
                            })
                    );
                    flag++;
                    timeline.setCycleCount(Timeline.INDEFINITE);
                    timeline.play();
                } else if (timeline != null && flag % 2 == 0) {
                    timeline.play();
                    flag++;
                }

            });

            root.getChildren().add(clock);
            root.getChildren().add(timerLabel);
            primaryStage.getIcons().add(new Image(this.getClass().getResourceAsStream("pig.jpg")));

            minimal.setOnMouseClicked(event -> {

                minimalcount++;
                if (minimalcount % 2 == 1) {
                    answer.refresh(target);
                    root.getChildren().add(path[0]);
                    root.getChildren().add(answer);
                    root.getChildren().add(polyline1);
                    minimal.setText("HideMinimal");
                } else {
                    root.getChildren().remove(path[0]);
                    root.getChildren().remove(answer);
                    root.getChildren().remove(polyline1);
                    minimal.setText("MinimalPath");

                }
            });


            Button restart = new Button("ReStart");
            restart.setLayoutX(600);
            restart.setLayoutY(600);
            restart.setOnMouseClicked(event -> {
                if (steps > 0) {
                    root.getChildren().remove(polyline);
                    game = recording;
                    points.clear();
                    points.add(new Point(start, null));
                    piece.refresh(start);
                    for (Hexagon hexagon : hexagons) {
                        hexagon.setFill(Color.ALICEBLUE);
                    }
                    steps = 0;
                    mysteps.setText("My path has " + steps + " steps");
                    polyline = new Polyline();
                    polyline.setStrokeWidth(4);
                    polyline.setStroke(piece.getFill());
                    root.getChildren().add(polyline);


                }
                time = Duration.seconds(60.0);
                timeSeconds.set(time.toSeconds());

            });


            //++++++++++++++++++++++++++++++++


            Button easy = new Button("Easy [1,4)");
            easy.setLayoutX(0);
            easy.setLayoutY(600);
            easy.setOnMouseClicked(event -> {

                timeline.stop();
                time = Duration.seconds(60.0);
                timeSeconds.set(time.toSeconds());
                //      mediaPlayer.stop();

                root.getChildren().removeAll(fxCrannies);
                root.getChildren().removeAll(fxNooks);
                root.getChildren().remove(polyline);
                root.getChildren().remove(answer);
                root.getChildren().remove(polyline1);
                root.getChildren().remove(path[0]);
                root.getChildren().remove(piece);
                flag = 0;

                minimalcount = 0;
                minimal.setText("MinimalPath");

                int level;
                do {
                    game = ConstructionHex.getGame();
                    recording = game;
                    crannys = game.substring(0, 18);
                    nooks = game.substring(18, 90);
                    pieces = game.substring(90);
                    start = Integer.parseInt(pieces);
                    target = Main.setTarget(nooks, pieces);

                    level = Main.MakeMinimalpath(game, start, target);
                } while (level >= 4);

                steps = 0;

                Main.CreateCrannys(fxCrannies, crannys, root);
                Main.CreateNooks(fxNooks, nooks, root);


                points.clear();
                points.add(new Point(start, null));
                for (Hexagon hexagon : hexagons) {
                    hexagon.setFill(Color.ALICEBLUE);
                    hexagon.toFront();
                }
                piece.refresh(start);
                piece.toFront();
                answer.refresh(start);
                answer.toFront();
                f[0] = new Flag(target);
                root.getChildren().add(f[0]);
                mysteps.setText("My path has " + steps + " steps");
                polyline = new Polyline();
                polyline.setStrokeWidth(4);
                polyline.setStroke(piece.getFill());
                polyline1 = new Polyline();
                polyline1.setStrokeWidth(4);
                polyline1.setStroke(answer.getFill());
                root.getChildren().add(polyline);
                path[0] = Main.MakeMinimalpath(game, start, target, polyline1, answer);

            });


            Button middle = new Button("Middle [4,7)");
            middle.setLayoutX(75);
            middle.setLayoutY(600);
            middle.setOnMouseClicked(event -> {

                timeline.stop();
                time = Duration.seconds(60.0);
                timeSeconds.set(time.toSeconds());
                //      mediaPlayer.stop();

                root.getChildren().removeAll(fxCrannies);
                root.getChildren().removeAll(fxNooks);
                root.getChildren().remove(polyline);
                root.getChildren().remove(answer);
                root.getChildren().remove(polyline1);
                root.getChildren().remove(path[0]);
                root.getChildren().remove(piece);
                flag = 0;
                minimalcount = 0;
                minimal.setText("MinimalPath");

                int level;
                do {
                    game = ConstructionHex.getGame();
                    recording = game;
                    crannys = game.substring(0, 18);
                    nooks = game.substring(18, 90);
                    pieces = game.substring(90);
                    start = Integer.parseInt(pieces);
                    target = Main.setTarget(nooks, pieces);

                    level = Main.MakeMinimalpath(game, start, target);
                } while (level < 4 || level >= 7);

                steps = 0;

                Main.CreateCrannys(fxCrannies, crannys, root);
                Main.CreateNooks(fxNooks, nooks, root);


                points.clear();
                points.add(new Point(start, null));
                for (Hexagon hexagon : hexagons) {
                    hexagon.setFill(Color.ALICEBLUE);
                    hexagon.toFront();
                }
                piece.refresh(start);
                piece.toFront();
                answer.refresh(start);
                answer.toFront();
                f[0] = new Flag(target);
                root.getChildren().add(f[0]);
                mysteps.setText("My path has " + steps + " steps");
                polyline = new Polyline();
                polyline.setStrokeWidth(4);
                polyline.setStroke(piece.getFill());
                polyline1 = new Polyline();
                polyline1.setStrokeWidth(4);
                polyline1.setStroke(answer.getFill());
                root.getChildren().add(polyline);
                path[0] = Main.MakeMinimalpath(game, start, target, polyline1, answer);

            });


            Button hard = new Button("Hard [7,10)");
            hard.setLayoutX(160);
            hard.setLayoutY(600);
            hard.setOnMouseClicked(event -> {

                timeline.stop();
                time = Duration.seconds(60.0);
                timeSeconds.set(time.toSeconds());
                //      mediaPlayer.stop();

                root.getChildren().removeAll(fxCrannies);
                root.getChildren().removeAll(fxNooks);
                root.getChildren().remove(polyline);
                root.getChildren().remove(answer);
                root.getChildren().remove(polyline1);
                root.getChildren().remove(path[0]);
                root.getChildren().remove(piece);
                flag = 0;
                minimalcount = 0;
                minimal.setText("MinimalPath");

                int level;
                do {
                    game = ConstructionHex.getGame();
                    recording = game;
                    crannys = game.substring(0, 18);
                    nooks = game.substring(18, 90);
                    pieces = game.substring(90);
                    start = Integer.parseInt(pieces);
                    target = Main.setTarget(nooks, pieces);

                    level = Main.MakeMinimalpath(game, start, target);
                } while (level < 7 || level >= 10);

                steps = 0;

                Main.CreateCrannys(fxCrannies, crannys, root);
                Main.CreateNooks(fxNooks, nooks, root);


                points.clear();
                points.add(new Point(start, null));
                for (Hexagon hexagon : hexagons) {
                    hexagon.setFill(Color.ALICEBLUE);
                    hexagon.toFront();
                }
                piece.refresh(start);
                piece.toFront();
                answer.refresh(start);
                answer.toFront();
                f[0] = new Flag(target);
                root.getChildren().add(f[0]);
                mysteps.setText("My path has " + steps + " steps");
                polyline = new Polyline();
                polyline.setStrokeWidth(4);
                polyline.setStroke(piece.getFill());
                polyline1 = new Polyline();
                polyline1.setStrokeWidth(4);
                polyline1.setStroke(answer.getFill());
                root.getChildren().add(polyline);
                path[0] = Main.MakeMinimalpath(game, start, target, polyline1, answer);

            });


            Button Desperate = new Button("Desperate [10,+∞)");
            Desperate.setLayoutX(240);
            Desperate.setLayoutY(600);
            Desperate.setOnMouseClicked(event -> {

                timeline.stop();
                time = Duration.seconds(60.0);
                timeSeconds.set(time.toSeconds());
                //      mediaPlayer.stop();

                root.getChildren().removeAll(fxCrannies);
                root.getChildren().removeAll(fxNooks);
                root.getChildren().remove(polyline);
                root.getChildren().remove(answer);
                root.getChildren().remove(polyline1);
                root.getChildren().remove(path[0]);
                root.getChildren().remove(piece);
                flag = 0;
                minimalcount = 0;
                minimal.setText("MinimalPath");

                int level;
                do {
                    game = ConstructionHex.getGame();
                    recording = game;
                    crannys = game.substring(0, 18);
                    nooks = game.substring(18, 90);
                    pieces = game.substring(90);
                    start = Integer.parseInt(pieces);
                    target = Main.setTarget(nooks, pieces);

                    level = Main.MakeMinimalpath(game, start, target);
                } while (level <= 10);

                steps = 0;

                Main.CreateCrannys(fxCrannies, crannys, root);
                Main.CreateNooks(fxNooks, nooks, root);


                points.clear();
                points.add(new Point(start, null));
                for (Hexagon hexagon : hexagons) {
                    hexagon.setFill(Color.ALICEBLUE);
                    hexagon.toFront();
                }
                piece.refresh(start);
                piece.toFront();
                answer.refresh(start);
                answer.toFront();
                f[0] = new Flag(target);
                root.getChildren().add(f[0]);
                mysteps.setText("My path has " + steps + " steps");
                polyline = new Polyline();
                polyline.setStrokeWidth(4);
                polyline.setStroke(piece.getFill());
                polyline1 = new Polyline();
                polyline1.setStrokeWidth(4);
                polyline1.setStroke(answer.getFill());
                root.getChildren().add(polyline);
                path[0] = Main.MakeMinimalpath(game, start, target, polyline1, answer);

            });


            //++++++++++++++++++++++++++++++++

            Button newgame = new Button("NewGame");
            newgame.setLayoutX(0);
            newgame.setLayoutY(550);
            newgame.setOnMouseClicked(event -> {

                timeline.stop();
                time = Duration.seconds(60.0);
                timeSeconds.set(time.toSeconds());
                //      mediaPlayer.stop();

                root.getChildren().removeAll(fxCrannies);
                root.getChildren().removeAll(fxNooks);
                root.getChildren().remove(polyline);
                root.getChildren().remove(answer);
                root.getChildren().remove(polyline1);
                root.getChildren().remove(f[0]);
                root.getChildren().remove(path[0]);
                root.getChildren().remove(piece);
                flag = 0;

                minimalcount = 0;
                minimal.setText("MinimalPath");


                game = ConstructionHex.getGame();
                recording = game;
                crannys = game.substring(0, 18);
                nooks = game.substring(18, 90);
                pieces = game.substring(90);
                steps = 0;

                Main.CreateCrannys(fxCrannies, crannys, root);
                Main.CreateNooks(fxNooks, nooks, root);
                start = Integer.parseInt(pieces);
                target = Main.setTarget(nooks, pieces);

                points.clear();
                points.add(new Point(start, null));
                for (Hexagon hexagon : hexagons) {
                    hexagon.setFill(Color.ALICEBLUE);
                    hexagon.toFront();
                }
                piece.refresh(start);
                piece.toFront();
                answer.refresh(start);
                answer.toFront();
                f[0] = new Flag(target);
                mysteps.setText("My path has " + steps + " steps");
                polyline = new Polyline();
                polyline.setStrokeWidth(4);
                polyline.setStroke(piece.getFill());
                polyline1 = new Polyline();
                polyline1.setStrokeWidth(4);
                polyline1.setStroke(answer.getFill());
                root.getChildren().add(polyline);
                root.getChildren().add(f[0]);
                path[0] = Main.MakeMinimalpath(game, start, target, polyline1, answer);

            });

            Button numbers = new Button("ShowNumbers");
            numbers.setLayoutX(500);
            numbers.setLayoutY(600);

            Button undo = new Button("UnDo");
            undo.setLayoutX(600);
            undo.setLayoutY(550);

            undo.setOnMouseClicked(event -> {
                if (steps > 0) {
                    polyline.getPoints().clear();
                    root.getChildren().remove(polyline);
                    int size = points.size();
                    points.remove(size - 1);
                    size = points.size();
                    piece.refresh(points.get(size - 1).getValue());
                    pieces = piece.getNumString();
                    game = crannys + nooks + pieces;
                    steps--;

                    for (Hexagon hexagon : hexagons) {
                        if (hexagon.getFill() != Color.ALICEBLUE) {
                            hexagon.setFill(Color.ALICEBLUE);
                        }
                    }
                    int pieceNum = piece.getNum();
                    for (int i = 1; i < 7; i++) {
                        int x = Node.move(game, pieceNum, i);
                        Hexagon.findHexagon(hexagons, x).setFill(Color.LIGHTGREEN);
                    }

                    for (int i = 0; i < size; i++) {
                        int numm = points.get(i).getValue();
                        polyline.getPoints().add(Hexagon.findHexagon(hexagons, numm).getX());
                        polyline.getPoints().add(Hexagon.findHexagon(hexagons, numm).getY());
                    }
                    root.getChildren().add(polyline);
                    mysteps.setText("My path has " + steps + " steps");

                }
            });


            //just for fun
            Button ClickMe = new Button("ClickMeYouCanWin ^3^");
            ClickMe.setLayoutX(350);
            ClickMe.setLayoutY(600);

            ClickMe.setOnMouseEntered(event -> {
                if (ClickMe.getLayoutY() == 600) {
                    ClickMe.setLayoutY(650);
                } else {
                    ClickMe.setLayoutY(600);
                }
            });


            numbers.setOnMouseClicked(event -> {
                buttonnumbers++;
                if (buttonnumbers % 2 == 1) {
                    for (Text text : texts) {
                        root.getChildren().add(text);
                    }
                    numbers.setText("HideNumbers");

                } else {
                    for (Text text : texts) {
                        root.getChildren().remove(text);
                    }
                    numbers.setText("ShowNumbers");
                }
            });


            root.getChildren().add(easy);
            root.getChildren().add(middle);
            root.getChildren().add(hard);
            root.getChildren().add(Desperate);
            root.getChildren().add(polyline);
            root.getChildren().add(minimal);
            root.getChildren().add(restart);
            root.getChildren().add(newgame);
            root.getChildren().add(undo);
            root.getChildren().add(numbers);
            root.getChildren().add(ClickMe);
            root.getChildren().add(f[0]);


        });


        multipiece.setOnMouseClicked(event2 -> {

            root.getChildren().remove(singlepiece);
            root.getChildren().remove(multipiece);


            root.getChildren().remove(imageView);
            root.getChildren().remove(text1);


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

            ttext.setLayoutY(150);

            ttext.textOriginProperty().set(VPos.TOP);
            ttext.textAlignmentProperty().set(TextAlignment.JUSTIFY);
            ttext.wrappingWidthProperty().set(400);

            ttext.setEffect(is);
            ttext.setFill(Color.ALICEBLUE);
            ttext.setFont(Font.font(null, FontWeight.BOLD, 16));

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

            ttext1.setFont(Font.font(null, FontWeight.BOLD, 16));

            ttext1.textProperty().bind(t1.textProperty());

            ttext1.setLayoutY(150);
            ttext1.setLayoutX(100);

            ttext1.textOriginProperty().set(VPos.TOP);
            ttext1.textAlignmentProperty().set(TextAlignment.JUSTIFY);
            ttext1.wrappingWidthProperty().set(400);

            ttext1.setEffect(is);
            ttext1.setFill(Color.ALICEBLUE);
            ttext1.setFont(Font.font(null, FontWeight.BOLD, 16));

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
            root.getChildren().addAll(ttext1, textField1);

            Button finish = new Button("Finish");
            Button add = new Button("Add");
            finish.setLayoutX(0);
            finish.setLayoutY(50);
            finish.setOnMouseClicked(event3 -> {
                root.getChildren().removeAll(textField, textField1);
                root.getChildren().add(add);
                root.getChildren().remove(finish);
            });
            root.getChildren().add(finish);

            add.setLayoutX(0);
            add.setLayoutY(50);
            add.setOnMouseClicked(event3 -> {
                root.getChildren().addAll(textField, textField1);
                root.getChildren().add(finish);
                root.getChildren().remove(add);
            });

            Text nn = new Text("Name");
            nn.setLayoutX(0);
            nn.setLayoutY(150);
            nn.textOriginProperty().set(VPos.TOP);
            nn.textAlignmentProperty().set(TextAlignment.JUSTIFY);
            nn.wrappingWidthProperty().set(400);
            nn.setEffect(is);
            nn.setFill(Color.ALICEBLUE);
            nn.setFont(Font.font(null, FontWeight.BOLD, 16));
            root.getChildren().add(nn);

            Text ss = new Text("Steps");
            ss.setLayoutX(80);
            ss.setLayoutY(150);
            ss.textOriginProperty().set(VPos.TOP);
            ss.textAlignmentProperty().set(TextAlignment.JUSTIFY);
            ss.wrappingWidthProperty().set(400);
            ss.setEffect(is);
            ss.setFill(Color.ALICEBLUE);
            ss.setFont(Font.font(null, FontWeight.BOLD, 16));
            root.getChildren().add(ss);


            //+++++++++++++++++++++++++++++++++++++++++++++++++++++


            //wangkun

            //input game map
            game = "171178187194205215" + "093D038D064E070C100D043D106A108F072A080A051D112F082B016C118D060D125B122D" + "093";
            game = ConstructionHex.getGame();
            game = "171178187194205215" + "093D038D064E070C100D043D106A108F072A080A051D112F082B016C118D060D125B122D" + "093038064070";
            game = "171178187194205215093D038D064E070C100D043D106A108F072A080A051D112F082B016C118D060D125B122D060106100064";
            game = ConstructionHex.getGame(4);
            recording = game;
            crannys = game.substring(0, 18);
            nooks = game.substring(18, 90);
            pieces = game.substring(90);
            steps = 0;
            mysteps = new Text("My path has " + steps + " steps");
            mysteps.setFont(Font.font("Tahoma", FontWeight.NORMAL, 16));
            mysteps.setLayoutX(500);
            mysteps.setLayoutY(500);
            ArrayList<Hexagon> hexagons = new ArrayList<Hexagon>();
            Hexagon.createHexagons(hexagons);
            ArrayList<Text> texts = new ArrayList<Text>();
            ArrayList<Integer> order = new ArrayList<>();
            FxCranny[] fxCrannies = new FxCranny[6];
            FxNook[] fxNooks = new FxNook[18];

            Main.CreateSides(root);
            Main.CreateCrannys(fxCrannies, crannys, root);
            Main.CreateNooks(fxNooks, nooks, root);
            Main.CreateNodes(hexagons, texts, root);
            target = 70;
            target = Main.setTarget(nooks, pieces);
            Piece[] piecess = new Piece[4];
            Polyline[] polylines = new Polyline[4];
            Main.CreatePieces(pieces, piecess, root, starts);
            Main.CreatePolilines(polylines, root);
            final Flag[] f = {new Flag(target)};
            for (int i = 0; i < 4; i++) {
                mpoints.add(new ArrayList<Point>());
                mpoints.get(i).add(new Point(starts[i], null));
            }


            Piece[] answers = new Piece[4];
            Polyline[] polyline1s = new Polyline[4];
            Main.CreatePieces(pieces, answers, root, starts);
            Main.CreatePolilines(polyline1s, root);
            for (int i = 0; i < 4; i++) {
                polyline1s[i].getPoints().clear();
                polyline1s[i].getPoints().add(answers[i].getLayoutX());
                polyline1s[i].getPoints().add(answers[i].getLayoutY());
            }
            root.getChildren().removeAll(answers);
            root.getChildren().removeAll(polyline1s);
            for (int i = 0; i < 4; i++) {

                final int n = i;
                piecess[n].setOnMousePressed(event -> {

                    befores[n] = piecess[n].findnode();
                    mmousex[n] = event.getSceneX();
                    mmousey[n] = event.getSceneY();
                    mpiecex[n] = piecess[n].getLayoutX();
                    mpiecey[n] = piecess[n].getLayoutY();

                    polylines[n].getPoints().add(mpiecex[n]);
                    polylines[n].getPoints().add(mpiecey[n]);

                });


                piecess[n].setOnMouseReleased(event -> {

                    afters[n] = piecess[n].findnode();
                    if (HexGame.legitimateStep(game, befores[n], afters[n])) {
                        piecess[n].refresh(afters[n]);
                        pieces = "";
                        for (int j = 0; j < 4; j++) {
                            pieces += piecess[j].getNumString();
                        }
                        game = crannys + nooks + pieces;
                        if (afters[n] != befores[n]) {
                            mpoints.get(n).add(new Point(afters[n], mpoints.get(n).get(stepps[n])));
                            steps++;
                            stepps[n]++;
                            order.add(piecess[n].getOrder());
                            if (afters[n] == target) {
                                piecess[n].refresh(piecess[n].getNum());
                                root.getChildren().removeAll(piecess);
                                primaryStage.setFullScreen(true);
                                primaryStage.setFullScreen(false);
                                Stage stage = new Stage();
                                Label l = new Label("");
                                Scene s = new Scene(l, 300, 300);
                                s.getStylesheets().add("/Fx");
                                stage.setScene(s);
                                stage.show();
                                s.getStylesheets().add("/Fx");
                                stage.setFullScreen(true);

                                s.getStylesheets().add("/Fx");
                                stage.setFullScreen(false);
                                stage.setFullScreen(true);
                                s.getStylesheets().add("/Fx");
                                s.getStylesheets().add("/Fx");
                                stage.setFullScreen(false);

                                s.getStylesheets().add("/Fx");
                                stage.close();
                            }
                        }
                    } else {
                        piecess[n].refresh(befores[n]);
                    }
                    for (Hexagon hexagon : hexagons) {
                        if (hexagon.getFill() != Color.ALICEBLUE) {
                            hexagon.setFill(Color.ALICEBLUE);
                        }
                    }
                    int pieceNum = piecess[n].getNum();
                    for (int j = 1; j < 7; j++) {
                        int x = Node.move(game, pieceNum, j);
                        Hexagon.findHexagon(hexagons, x).setFill(Color.LIGHTGREEN);
                    }

                    mpiecex[n] = piecess[n].getLayoutX();
                    mpiecey[n] = piecess[n].getLayoutY();

                    polylines[n].getPoints().add(mpiecex[n]);
                    polylines[n].getPoints().add(mpiecey[n]);

                    mysteps.setText("My path has " + steps + " steps");

                });

                piecess[n].setOnMouseDragged(event -> {

                    mpiecex[n] = piecess[n].getLayoutX();
                    mpiecey[n] = piecess[n].getLayoutY();

                    double mouseOffsetx = event.getSceneX() - mmousex[n];
                    double mouseOffsety = event.getSceneY() - mmousey[n];
                    mpiecex[n] += mouseOffsetx;
                    mpiecey[n] += mouseOffsety;
                    piecess[n].setLayoutX(mpiecex[n]);
                    piecess[n].setLayoutY(mpiecey[n]);

                    mmousex[n] = event.getSceneX();
                    mmousey[n] = event.getSceneY();
                    event.consume();

                });

                piecess[n].setOnMouseEntered(event -> {
                    scene.setCursor(Cursor.HAND);
                    int pieceNum = piecess[n].getNum();
                    for (int k = 1; k < 7; k++) {
                        int x = Node.move(game, pieceNum, k);
                        Hexagon.findHexagon(hexagons, x).setFill(Color.LIGHTGREEN);
                    }
                });

                piecess[n].setOnMouseExited(event -> {
                    scene.setCursor(Cursor.DEFAULT);
                    int pieceNum = piecess[n].getNum();
                    for (int k = 1; k < 7; k++) {
                        int x = Node.move(game, pieceNum, k);
                        Hexagon.findHexagon(hexagons, x).setFill(Color.ALICEBLUE);
                    }
                });

            }


            Button numbers = new Button("ShowNumbers");
            numbers.setLayoutX(400);
            numbers.setLayoutY(600);
            numbers.setOnMouseClicked(event -> {
                buttonnumbers++;
                if (buttonnumbers % 2 == 1) {
                    for (Text text : texts) {
                        root.getChildren().add(text);
                    }
                    numbers.setText("HideNumbers");

                } else {
                    for (Text text : texts) {
                        root.getChildren().remove(text);
                    }
                    numbers.setText("ShowNumbers");
                }
            });

            Button restart = new Button("ReStart");
            restart.setLayoutX(80);
            restart.setLayoutY(600);
            restart.setOnMouseClicked(event -> {
                game = recording;
                pieces = game.substring(90);
                steps = 0;
                order.clear();
                for (int i = 0; i < 4; i++) {
                    mpoints.get(i).clear();
                    mpoints.get(i).add(new Point(starts[i], null));
                    stepps[i] = 0;
                }
                root.getChildren().removeAll(polylines);
                for (Hexagon hexagon : hexagons) {
                    hexagon.setFill(Color.ALICEBLUE);
                }
                for (int i = 0; i < 4; i++) {
                    piecess[i].refresh(starts[i]);
                }
                Main.CreatePolilines(polylines, root);

            });


            //++++++++++++++++++++++++++++++++

            Button minimal = new Button("MinimalPath");

            Button newgame = new Button("NewGame");
            newgame.setLayoutX(160);
            newgame.setLayoutY(600);
            newgame.setOnMouseClicked(event -> {


                root.getChildren().removeAll(fxCrannies);
                root.getChildren().removeAll(fxNooks);
                root.getChildren().removeAll(polylines);


                root.getChildren().removeAll(answers);
                root.getChildren().removeAll(polyline1s);


                minimalcount = 0;
                minimal.setText("MinimalPath");


                game = ConstructionHex.getGame(4);
                recording = game;
                crannys = game.substring(0, 18);
                nooks = game.substring(18, 90);
                pieces = game.substring(90);
                steps = 0;

                Main.CreateCrannys(fxCrannies, crannys, root);
                Main.CreateNooks(fxNooks, nooks, root);
                target = Main.setTarget(nooks, pieces);
                f[0] = new Flag(target);


                for (Hexagon hexagon : hexagons) {
                    hexagon.setFill(Color.ALICEBLUE);
                    hexagon.toFront();
                }
                mpoints.clear();

                for (int i = 0; i < 4; i++) {
                    starts[i] = Integer.parseInt(pieces.substring(3 * i, 3 * i + 3));
                    mpoints.add(new ArrayList<Point>());
                    mpoints.get(i).add(new Point(starts[i], null));
                    piecess[i].refresh(starts[i]);
                    piecess[i].toFront();
                    answers[i].refresh(starts[i]);
                    stepps[i] = 0;
                    polylines[i] = new Polyline();
                    polylines[i].setStrokeWidth(4);
                    polylines[i].setStroke(piecess[i].getFill());

                }


//            answer.refresh(start);
//            answer.toFront();
                mysteps.setText("My path has " + steps + " steps");

                root.getChildren().addAll(polylines);
                //path[0] = Main.MakeMinimalpath(game,start,target,polyline1,answer);

            });


            //++++++++++++++++++++++++++++++++


            Button undo = new Button("UnDo");
            undo.setLayoutX(240);
            undo.setLayoutY(600);

            undo.setOnMouseClicked(event -> {
                if (steps > 0) {
                    final int i = order.get(order.size() - 1);
                    polylines[i].getPoints().clear();
                    root.getChildren().remove(polylines[i]);
                    order.remove(order.size() - 1);
                    int size = mpoints.get(i).size();
                    mpoints.get(i).remove(size - 1);
                    size = mpoints.get(i).size();
                    piecess[i].refresh(mpoints.get(i).get(size - 1).getValue());
                    pieces = "";
                    for (int j = 0; j < 4; j++) {
                        pieces += piecess[j].getNumString();
                    }
                    game = crannys + nooks + pieces;
                    steps--;
                    stepps[i]--;

                    for (Hexagon hexagon : hexagons) {
                        if (hexagon.getFill() != Color.ALICEBLUE) {
                            hexagon.setFill(Color.ALICEBLUE);
                        }
                    }
                    for (int k = 0; k < size; k++) {
                        int numm = mpoints.get(i).get(k).getValue();
                        polylines[i].getPoints().add(Hexagon.findHexagon(hexagons, numm).getX());
                        polylines[i].getPoints().add(Hexagon.findHexagon(hexagons, numm).getY());
                    }
                    root.getChildren().add(polylines[i]);
                    mysteps.setText("My path has " + steps + " steps");

                }
            });


            pathss = new Text();
            pathss.setFont(Font.font("Tahoma", FontWeight.NORMAL, 16));
            pathss.setLayoutX(450);
            pathss.setLayoutY(525);


            minimal.setLayoutX(300);
            minimal.setLayoutY(600);
            minimal.setOnMouseClicked(event -> {


                minimalcount++;
                if (minimalcount % 2 == 1) {


                    minimal.setText("HideMinimal");
                    root.getChildren().removeAll(answers);
                    root.getChildren().removeAll(polyline1s);

                    String kun = Main.MinimalPath(game, starts, target);
                    int n = kun.length() / 4;
                    n--;

                    for (int i = 0; i < 4; i++) {
                        polyline1s[i].getPoints().clear();
                        answers[i].refresh(starts[i]);
                        polyline1s[i].getPoints().add(answers[i].getLayoutX());
                        polyline1s[i].getPoints().add(answers[i].getLayoutY());

                    }

                    for (int i = n - 1; i >= 0; i--) {
                        int who = Integer.parseInt(kun.substring(4 * i, 4 * i + 1));
                        int num = Integer.parseInt(kun.substring(4 * i + 1, 4 * i + 4));
                        answers[who].refresh(num);
                        polyline1s[who].getPoints().add(answers[who].getLayoutX());
                        polyline1s[who].getPoints().add(answers[who].getLayoutY());
                    }
                    pathss.setText("The minimal path has " + n + " steps");
                    root.getChildren().addAll(answers);
                    root.getChildren().addAll(polyline1s);

                    root.getChildren().add(pathss);

                } else {
                    root.getChildren().removeAll(answers);
                    root.getChildren().removeAll(polyline1s);
                    root.getChildren().remove(pathss);

                    minimal.setText("MinimalPath");

                }
            });


            root.getChildren().add(minimal);

            root.getChildren().add(numbers);
            root.getChildren().add(undo);
            root.getChildren().add(restart);
            root.getChildren().add(mysteps);
            root.getChildren().add(newgame);
            root.getChildren().add(f[0]);


        });


        root.getChildren().add(singlepiece);
        root.getChildren().add(multipiece);

        primaryStage.show();

    }

}
