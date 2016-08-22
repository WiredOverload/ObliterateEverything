/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package obliterateeverything;

import java.util.ArrayList;

/**
 *
 * @author Michael Hodges
 */
public class Spawner {

    private int x;
    private int y;
    private short type;
    private int cooldown;
    private ArrayList<Ship> ships;
    private int health;

    public Spawner() {
        this.x = 0;
        this.y = 0;
        this.type = 1;
        this.cooldown = 120;
        this.health = 200;
        this.ships = new ArrayList<Ship>();
    }

    public Spawner(short type, int x, int y) {
        this.x = x;
        this.y = y;
        this.type = type;
        this.cooldown = 120;
        this.health = 200;
        this.ships = new ArrayList<Ship>();
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

    //methods
    public void addShip() {
        this.ships.add(new Ship(this.type, this.x, this.y));
    }

    public void update(Player enemy) {
        if (this.cooldown == 0) {
            this.addShip();
            this.cooldown = -120; //make negative to disable multiple spawns
        } else {
            cooldown--;
        }

        for (int i = 0; i < ships.size(); i++) {
            ships.get(i).update(enemy);
            if (ships.get(i).getHealth() <= 0) {
                ships.remove(i);
            }
        }
    }
}
