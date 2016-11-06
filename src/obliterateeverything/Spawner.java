/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package obliterateeverything;

import java.util.ArrayList;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

/**
 *
 * @author Michael Hodges
 */
public class Spawner {

    private int x;
    private int y;
    private int size;
    private short type;
    private int cooldown;
    private ArrayList<Ship> ships;
    private int health;
    private int maxHealth;
    private Line gHealth = new Line();
    private Line rHealth = new Line();

    public Spawner() {
        this.x = 0;
        this.y = 0;
        this.size = 32;
        this.type = 1;
        this.cooldown = -2;
        this.health = 2000;
        this.maxHealth = 2000;
        this.ships = new ArrayList<Ship>();
        this.gHealth.setStroke(Color.LAWNGREEN);
        this.rHealth.setStroke(Color.RED);
    }

    public Spawner(short type, int x, int y) {
        this.x = x;
        this.y = y;
        this.type = type;
        this.cooldown = -2;
        this.ships = new ArrayList<Ship>();
        this.gHealth.setStroke(Color.LAWNGREEN);
        this.rHealth.setStroke(Color.RED);
        
        if(type == 1)
        {
            this.size = 32;
            this.health = 2000;
            this.maxHealth = 2000;
        }
    }

    //getters
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public short getType() {
        return type;
    }

    public int getCooldown() {
        return cooldown;
    }

    public ArrayList<Ship> getShips() {
        return ships;
    }

    public int getHealth() {
        return health;
    }

    public int getSize() {
        return size;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public Line getgHealth() {
        return gHealth;
    }

    public Line getrHealth() {
        return rHealth;
    }

    
    
    //setters
    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setType(short type) {
        this.type = type;
    }

    public void setCooldown(int cooldown) {
        this.cooldown = cooldown;
    }

    public void setShips(ArrayList<Ship> ships) {
        this.ships = ships;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public void setgHealth(Line gHealth) {
        this.gHealth = gHealth;
    }

    public void setrHealth(Line rHealth) {
        this.rHealth = rHealth;
    }

    
    
    //methods
    public void addShip() {
        this.ships.add(new Ship(this.type, this.x, this.y));
    }

    public void update(Player enemy) {
        if (this.cooldown == 0) {
            this.addShip();
            this.cooldown = 120; //make negative to disable multiple spawns
        } else {
            cooldown--;
        }

        for (int i = 0; i < ships.size(); i++) {
            ships.get(i).update(enemy);
            if (ships.get(i).getHealth() <= 0) {
                ships.remove(i);
            }
        }
        
        if (this.cooldown == -3) {
            this.cooldown += 2;
        } else if (this.cooldown == -2) {
            this.cooldown = 0;
        }
        
        gHealth.setStartX(this.x);
        gHealth.setStartY(this.y + size);
        gHealth.setEndX(this.x + (((double) health / (double) maxHealth) * (double) size));
        gHealth.setEndY(this.y + size);

        rHealth.setStartX(this.x + size);
        rHealth.setStartY(this.y + size);
        rHealth.setEndX(this.x + (((double) health / (double) maxHealth) * (double) size));
        rHealth.setEndY(this.y + size);
    }
}
