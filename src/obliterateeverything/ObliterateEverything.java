/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package obliterateeverything;

//import java.util.*;
import java.util.ArrayList;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Michael Hodges
 * @todo 
 *      slow everything down
 *      improve layering, space - spawners - ships - lasers
 *      add menu
 *      add placement
 *      add health bars
 *      add spawning progress bars
 *      add sound
 *      add AI
 *      add multiple ship types
 *      add multiple spawner types
 *      add dynamic backgrounds
 * @bugs
 *      ships moving strangely
 *      lasers not always pointing the correct direction
 *      incorrect layering, one player always renders over another
 *      ships not facing right direction, always 90 degrees off
 */
public class ObliterateEverything extends Application {

    @Override
    public void start(Stage primaryStage) {

        Image space = new Image("space1024.png");
        Player p1 = new Player(0);
        Player p2 = new Player(1);
        
        primaryStage.setTitle("Obliterate Everything");
        Group root = new Group();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        Canvas canvas = new Canvas(1024, 512);
        root.getChildren().add(canvas);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        ArrayList<String> input = new ArrayList<String>();

        scene.setOnKeyPressed(
                new EventHandler<KeyEvent>() {
            public void handle(KeyEvent e) {
                String code = e.getCode().toString();

                // only add once... prevent duplicates
                if (!input.contains(code)) {
                    input.add(code);
                }
            }
        });

        scene.setOnKeyReleased(
                new EventHandler<KeyEvent>() {
            public void handle(KeyEvent e) {
                String code = e.getCode().toString();
                input.remove(code);
            }
        });

        new AnimationTimer() {
            public void handle(long currentNanoTime) {

                if(input.contains("A")) {
                    //pause everything
                }
                else
                {
                    gc.drawImage(space, 0, 0);
                    //change so that this works with variable players
                    //updates
                    p1.update(p2);
                    p2.update(p1);
                    //renders
                    p1.render(gc);
                    p2.render(gc);
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
