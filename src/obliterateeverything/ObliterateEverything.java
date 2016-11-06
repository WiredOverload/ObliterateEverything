/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package obliterateeverything;

//import java.util.*;
import javafx.scene.paint.Color;
import java.util.ArrayList;
import java.util.List;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.CacheHint;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

/**
 *
 * @author Michael Hodges
 * @todo 
 *      slow everything down
 *      change to slower turning speed
 *      improve layering, space - spawners - ships - lasers
 *      add menu
 *          title screen
 *          armory
 *          gameplay
 *          credits / help
 *      add custom buttons w/ icons
 *      add toggling to keys
 *      add mouse placement
 *      add spawning progress bars
 *      add sound / in progress
 *      add AI
 *      add multiple ship types
 *      add multiple spawner types
 *      add turrets
 *      add more / different dynamic backgrounds
 *      add zoom
 *          compensate for fast speeds with large distances
 *      add threading
 * @bugs
 *      entire game lags with larger map
 *          possibly caused by snapshots or imageView forcing entire scene to render
 *          most likely the constant snapshots
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
        //Image space = new Image("space1024.png"); //old static image
        Image stars = new Image("space4096.png");
        Image clouds1 = new Image("8192clouds1.png");
        Image clouds2 = new Image("8192clouds2.png");
        Image grid = new Image("grid.png");
        //ImageView imageView = null;// = new ImageView(space); //resizable scaling image
        //WritableImage sceneImage = null; //where snapshots go for ^
        Player p1 = new Player(0);
        Player p2 = new Player(1);
        List<Buttons> buttonA = new ArrayList<>();
        List<Image> images = new ArrayList<>();
        images.add(stars);
        images.add(clouds1);
        images.add(clouds2);
        
        
        primaryStage.setTitle("Obliterate Everything");
        
        
        Group trueRoot = new Group();
        Group root = new Group(); //invisible unscaled image
        //Scene scene = new Scene(root);
        
        //primaryStage.setScene(scene);
        Canvas canvas = new Canvas(4096, 2048); //unedited canvas
        root.getChildren().add(canvas);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        
        
        
        //entire block for resizing
        Scene scene2 = new Scene(trueRoot);
        primaryStage.setScene(scene2); //change to scene to view original unstretched
        
        WritableImage sceneImage = root.snapshot(new SnapshotParameters(), null); //snapshot of fully rendered root scene
        ImageView imageView = new ImageView(sceneImage); //imageView can be used to resize or crop
        Rectangle2D viewportRect = new Rectangle2D(0, 0, 1024, 512); //for cropping
        imageView.setViewport(viewportRect); //for cropping
        trueRoot.getChildren().add(imageView); //also for resizing / cropping
        
        
        gc.setFill(Color.BLACK);
        //gc.setStroke(Color.BLACK);
        gc.setLineWidth(2);
        Font theFont = Font.font("monospaced", FontWeight.BOLD, 32);
        gc.setFont(theFont);

        
        
        ArrayList<String> input = new ArrayList<String>();
        ArrayList<Integer> mouseCoords = new ArrayList<Integer>();

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
                mouseCoords.add(0, (int) event.getX());
                mouseCoords.add(1, (int) event.getY());
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
            int menuNumber;
            int slow = 0;
            int cloudTimer = 0;
            public void handle(long currentNanoTime) { //everything within happens 60 times per second

                //if()
                
                if(input.contains("A")) {
                    //pause everything
                }
                else if(input.contains("S"))
                {
                    if(slow == 0)
                    {
                        slow = 10;
                        
                        //simple animated background
                        if (cloudTimer == 16384) {
                            cloudTimer = 0;
                        } else {
                            cloudTimer++;
                        }
                        
                        menuNumber = menu(1, cloudTimer, gc, root, buttonA, images, mouseCoords);

                        if (DEBUG) {
                            gc.drawImage(grid, 0, 0);
                            gc.drawImage(boundingBox, (mouseX / 8) * 8, (mouseY / 8) * 8);
                        }

                        /* button logic still being moved around
                        if(!menuB1.isAdded())
                        {
                            root.getChildren().add(menuB1.getR());
                            root.getChildren().add(menuB2.getR());
                            root.getChildren().add(menuB3.getR());
                            menuB1.setAdded(true);
                            menuB2.setAdded(true);
                            menuB3.setAdded(true);
                        }
                        */
                        
                        p1.update(p2, root);
                        p2.update(p1, root);
                        p1.render(gc, root);
                        p2.render(gc, root);
                        //imageView.setFitWidth(scene2.getWidth()); //for resizing
                        //imageView.setFitHeight(scene2.getHeight()); //for resizing
                        //sceneImage.setImage();
                        root.snapshot(null, new SnapshotParameters(), sceneImage);
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
                    if (cloudTimer == 16384)
                        cloudTimer = 0;
                    else
                        cloudTimer++;
                    gc.drawImage(stars, 0, 0);
                    gc.drawImage(clouds1, cloudTimer % 16384, 0);
                    gc.drawImage(clouds1, (cloudTimer % 16384) - 8192, 0);
                    gc.drawImage(clouds2, (cloudTimer / 2) % 16384, 0);
                    gc.drawImage(clouds2, ((cloudTimer / 2) % 16384) - 8192, 0);
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
                    
                    //.setFitWidth(scene2.getWidth()); //for resizing
                    //imageView.setFitHeight(scene2.getHeight()); //for resizing
                    root.snapshot(new SnapshotParameters(), sceneImage); //takes root scene and hands it to trueRoot
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

    public int menu(int menuNumber, int cloudTimer, GraphicsContext gc, Group root, List<Buttons> buttonA, List<Image> images, ArrayList<Integer> mouseCoords)
    {
        switch (menuNumber) {
        //main menu
            case 1:
                gc.drawImage(images.get(0), 0, 0);
                gc.drawImage(images.get(1), cloudTimer % 16384, 0);
                gc.drawImage(images.get(1), (cloudTimer % 16384) - 16384, 0);
                gc.drawImage(images.get(2), cloudTimer / 2, 0);
                gc.drawImage(images.get(2), (cloudTimer / 2) - 16384, 0);
                if(buttonA.isEmpty())
                {
                    buttonA.add(new Buttons(31, 447, 192, 32, "OPTIONS"));
                    buttonA.add(new Buttons(415, 447, 192, 32, "PLAY"));
                    buttonA.add(new Buttons(799, 447, 192, 32, "CREDITS"));
                    
                    for (int i = 0; i < buttonA.size(); i++) {
                        if (!buttonA.get(i).isAdded()) {
                            root.getChildren().add(buttonA.get(i).getR());
                            buttonA.get(i).setAdded(true);
                        }
                    }
                }
                for(int i = 0; i < buttonA.size(); i++)
                {
                    gc.fillText(buttonA.get(i).getMessage(), buttonA.get(i).getX(), buttonA.get(i).getY());
                    if(buttonA.get(i).isClicked(mouseCoords.get(0), mouseCoords.get(1)))
                    {
                        System.out.println("Clicked");
                        mouseCoords.set(0, 0);
                        mouseCoords.set(1, 0);
                    }
                    //gc.strokeText(buttonA.get(i).getMessage(), buttonA.get(i).getX(), buttonA.get(i).getY());
                }   break;
        //options
            case 2:
                break;
        // information / credits / how-to
            case 3:
                break;
        //map
            case 4:
                break;
        //possible loadout / misc
            case 5:
                break;
        //possibly put gameplay screen here
            default:
                break;
        }
        return menuNumber;
    }
}
