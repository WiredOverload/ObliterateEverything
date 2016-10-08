/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package obliterateeverything;

//import java.util.*;
import javafx.scene.paint.Color;
import java.util.ArrayList;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

/**
 *
 * @author Michael Hodges
 * @todo 
 *      slow everything down
 *      improve layering, space - spawners - ships - lasers
 *      add menu
 *      add toggling to keys
 *      add mouse placement
 *      add spawning progress bars
 *      add sound / in progress
 *      add AI
 *      add multiple ship types
 *      add multiple spawner types
 *      add dynamic backgrounds
 *      add threading
 * @bugs
 *      health bars causing screen to jerk at edges
 *      ships movement "fixed" but still strange
 *      lasers not always going in right direction
 *      incorrect layering, one player always renders over another
 */
public class ObliterateEverything extends Application {
    
    private static final boolean DEBUG = false;
    
    int mouseX;
    int mouseY;
    
    @Override
    public void start(Stage primaryStage) {

        
        Image boundingBox = new Image("boundingBox.png");
        Image space = new Image("space1024.png"); //old static image
        Image stars = new Image("stars.png");
        Image clouds = new Image("clouds.png");
        Image buttons = new Image("buttons.png");
        Image grid = new Image("grid.png");
        //ImageView imageView = null;// = new ImageView(space); //resizable scaling image
        //WritableImage sceneImage = null; //where snapshots go for ^
        Player p1 = new Player(0);
        Player p2 = new Player(1);
        Buttons b1 = new Buttons(100, 100, 100);
        
        primaryStage.setTitle("Obliterate Everything");
        
        
        Group trueRoot = new Group();
        Group root = new Group(); //invisible unscaled image
        Scene scene = new Scene(root);
        //trueRoot.getChildren().add(root);
        
        //primaryStage.setScene(scene);
        Canvas canvas = new Canvas(1024, 512);
        root.getChildren().add(canvas);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        
        
        
        //entire block for resizing
        Scene scene2 = new Scene(trueRoot);
        primaryStage.setScene(scene2); //change to scene to view original unstretched
        
        WritableImage sceneImage = root.snapshot(new SnapshotParameters(), null);
        ImageView imageView = new ImageView(sceneImage); //new ImageView(root.snapshot(new SnapshotParameters(), null));
        trueRoot.getChildren().add(imageView); //also for resizing
        
        

        
        
        ArrayList<String> input = new ArrayList<String>();

        scene2.setOnKeyPressed(
                new EventHandler<KeyEvent>() {
            public void handle(KeyEvent e) {
                String code = e.getCode().toString();

                // only add once... prevent duplicates
                if (!input.contains(code)) {
                    input.add(code);
                }
            }
        });
        
        scene2.setOnMouseClicked(
                new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println(event.getX());
                System.out.println(event.getY());
                
            }
        });
        
        scene2.setOnMouseMoved(
                new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                mouseX = (int) event.getSceneX();
                mouseY = (int) event.getSceneY();
            }
        });

        scene2.setOnKeyReleased(
                new EventHandler<KeyEvent>() {
            public void handle(KeyEvent e) {
                String code = e.getCode().toString();
                input.remove(code);
            }
        });

        new AnimationTimer() {
            int slow = 0;
            int cloudTimer = 0;
            public void handle(long currentNanoTime) {

                if(input.contains("A")) {
                    //pause everything
                }
                else if(input.contains("S"))
                {
                    if(slow == 0)
                    {
                        slow = 10;
                        
                        gc.drawImage(space, 0, 0);
                        
                        if (DEBUG) {
                            gc.drawImage(grid, 0, 0);
                            gc.drawImage(boundingBox, (mouseX / 8) * 8, (mouseY / 8) * 8);
                        }

                        p1.update(p2, root);
                        p2.update(p1, root);
                        p1.render(gc, root);
                        p2.render(gc, root);
                        imageView.setFitWidth(scene2.getWidth()); //for resizing
                        imageView.setFitHeight(scene2.getHeight()); //for resizing
                        root.snapshot(new SnapshotParameters(), sceneImage);
                    }
                    else
                    {
                        slow--;
                    }
                }
                else //normal main game loop
                {
                    //gc.drawImage(space, 0, 0); //old static image
                    
                    //simple animated background
                    if (cloudTimer == 2048)
                        cloudTimer = 0;
                    else
                        cloudTimer++;
                    gc.drawImage(stars, 0, 0);
                    gc.drawImage(clouds, cloudTimer, 0);
                    gc.drawImage(clouds, cloudTimer - 2048, 0);
                    //gc.drawImage(buttons, 0, 0); //for menu
                    
                    if (DEBUG)
                    {
                        gc.drawImage(grid, 0, 0);
                        gc.drawImage(boundingBox, (mouseX/8)*8, (mouseY/8)*8);
                    }
                    
                    //change so that this works with variable players
                    //updates
                    p1.update(p2, root);
                    p2.update(p1, root);
                    //renders
                    p1.render(gc, root);
                    p2.render(gc, root);
                    
                    imageView.setFitWidth(scene2.getWidth()); //for resizing
                    imageView.setFitHeight(scene2.getHeight()); //for resizing
                    
                    root.snapshot(new SnapshotParameters(), sceneImage);
                }
            }
        }.start();

        
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
