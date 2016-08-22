/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package obliterateeverything;

import java.util.ArrayList;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.transform.Rotate;

/**
 *
 * @author Michael Hodges
 */
public class Player {

    private int energy; //currency
    private int team;
    private ArrayList<Spawner> spawners;
    private Image ship;// = new Image("small4.png");
    private Image spawner;// = new Image("port2.png");
    private Image laser;// = new Image("laserBasic.png");
    private Image hit = new Image("smallHit.png");
    private Image target = new Image("target.png"); //debug only
    private Image boundingBox = new Image("boundingBox.png"); //debug only
    Rotate r; // needed for sprite rotation

    public Player() {
        this.energy = 500;
        this.team = 0;
        this.spawners = new ArrayList<Spawner>();
    }

    public Player(int team) {
        this.team = team;
        this.energy = 500;
        this.spawners = new ArrayList<Spawner>();
        //test spawners, remove with user input
        if (this.team == 0) {
            this.spawners.add(new Spawner((short) 1, 768, 300));
            this.ship = new Image("small4_1.png");
            this.spawner = new Image("port2_1.png");
            this.laser = new Image("laserBasic_1.png");
        } else if (this.team == 1) {
            this.spawners.add(new Spawner((short) 1, 256, 256));
            this.ship = new Image("small4_2.png");
            this.spawner = new Image("port2_2.png");
            this.laser = new Image("laserBasic_2.png");
        }
    }

    public Player(int team, int energy) {
        this.team = team;
        this.energy = energy;
        this.spawners = new ArrayList<Spawner>();
    }

    //getters
    public int getEnergy() {
        return energy;
    }

    public ArrayList<Spawner> getSpawners() {
        return spawners;
    }

    public int getTeam() {
        return team;
    }

    //setters
    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public void setSpawners(ArrayList<Spawner> spawners) {
        this.spawners = spawners;
    }

    public void setTeam(int team) {
        this.team = team;
    }

    //methods
    void update(Player enemy) {
        energy++;
        for (int i = 0; i < spawners.size(); i++) {
            //System.out.println(this.team);
            spawners.get(i).update(enemy);
            if (spawners.get(i).getHealth() <= 0) {
                spawners.remove(i);
            }
        }
    }

    void render(GraphicsContext gc) {
        for (int i = 0; i < spawners.size(); i++) {
            gc.drawImage(spawner, spawners.get(i).getX(), spawners.get(i).getY());
            for (int j = 0; j < spawners.get(i).getShips().size(); j++) {
                gc.save();
                r = new Rotate(spawners.get(i).getShips().get(j).getAngle(), spawners.get(i).getShips().get(j).getX() + 8, spawners.get(i).getShips().get(j).getY() + 8);
                gc.setTransform(r.getMxx(), r.getMyx(), r.getMxy(), r.getMyy(), r.getTx(), r.getTy());
                gc.drawImage(ship, spawners.get(i).getShips().get(j).getX(), spawners.get(i).getShips().get(j).getY());
                gc.restore();
                if (spawners.get(i).getShips().get(j).getHitTime() > 0) {
                    gc.drawImage(hit, spawners.get(i).getShips().get(j).getHitX(), spawners.get(i).getShips().get(j).getHitY());
                }
                if(this.team == 1)
                gc.drawImage(target, spawners.get(i).getShips().get(j).getTargetX(), spawners.get(i).getShips().get(j).getTargetY());
                //gc.drawImage(boundingBox, spawners.get(i).getShips().get(j).getX(), spawners.get(i).getShips().get(j).getY());

                for (int k = 0; k < spawners.get(i).getShips().get(j).getLasers().size(); k++) {
                    gc.save();
                    r = new Rotate(spawners.get(i).getShips().get(j).getLasers().get(k).getAngle(), spawners.get(i).getShips().get(j).getLasers().get(k).getX() + 1, spawners.get(i).getShips().get(j).getLasers().get(k).getY() + 4);
                    gc.setTransform(r.getMxx(), r.getMyx(), r.getMxy(), r.getMyy(), r.getTx(), r.getTy());
                    gc.drawImage(laser, spawners.get(i).getShips().get(j).getLasers().get(k).getX(), spawners.get(i).getShips().get(j).getLasers().get(k).getY());
                    gc.restore();
                }
            }
        }
    }
}
