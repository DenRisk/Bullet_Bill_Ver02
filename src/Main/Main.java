package Main;

import Physics.Circle_Circle_Collision;
import Physics.MoveEngine;
import Sprite.Sphere;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;


public class Main extends Application {

    AnimationTimer animationTimer;
    boolean living = true;

    public static final double GRAVITY = 1200;
    public static final double DRAG = 0.2;
    public static final double BOUNCE = 0.6;
    private Sphere aSphere;
    private Sphere cSphere;


    @Override
    public void start(Stage primaryStage) throws Exception{
        Pane root = new Pane();
        primaryStage.setTitle("Sphere");
        primaryStage.setScene(new Scene(root, 800, 700));


        aSphere = new Sphere(400, 200,30, Color.BLACK, 0, 0);
        root.getChildren().add(aSphere);

        cSphere = new Sphere(420, 450,30, Color.BLACK, 0, 0);
        root.getChildren().add(cSphere);



        animationTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (living == true) {
                    aSphere.setCenterX(aSphere.getX0());
                    aSphere.setCenterY(aSphere.getY0());
                    new MoveEngine(now, aSphere);
                    Circle_Circle_Collision.cicleDetection(aSphere, cSphere);




                }
            }
        };

        animationTimer.start();
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
