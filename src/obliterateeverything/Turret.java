/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package obliterateeverything;

import java.io.File;
import java.util.ArrayList;
import javafx.scene.media.AudioClip;
import javafx.scene.shape.Line;

/**
 *
 * @author Michael Hodges
 */
public class Turret {
    private int type;
    private int x;
    private int y;
    private int angle;
    private int targetDistance;
    private int targetX;
    private int targetY;
    private int health;
    private int cooldown;
    private int maxCooldown;
    private int size;
    private int maxHealth;
    private int weapon;
    private int hitX;
    private int hitY;
    private int hitTime;
    private ArrayList<Laser> lasers;
    private Line hitScan;
    private int damage;
    private Line gHealth = new Line();
    private Line rHealth = new Line();
    AudioClip plonkSound = new AudioClip(new File("slink2.mp3").toURI().toString());

    public Turret(int x, int y, int type) {
        this.type = type;
        this.x = x;
        this.y = y;
        this.cooldown = -2;
        this.angle = 0;
        this.hitX = -1;
        this.hitY = -1;
        this.hitTime = 0;
        this.lasers = new ArrayList<Laser>();
        switch (type) {
            case 0: //standard balistic turret
                maxHealth = 150;
                health = 150;
                size = 16;
                weapon = 1;
                damage = 10;
                maxCooldown = 30;
                break;
            case 1: //wall
                maxHealth = 500;
                health = 500;
                size = 16;
                weapon = 0;
                break;
            case 2: //hitscan laser turret
                maxHealth = 100;
                health = 100;
                size = 16;
                weapon = 2;
                damage = 20;
                maxCooldown = 120;
                break;
            case 3: //homing rocket turret
                maxHealth = 100;
                health = 100;
                size = 16;
                weapon = 3;
                damage = 20;
                maxCooldown = 60;
                break;
            case 4: //autocannon
                maxHealth = 200;
                health = 200;
                size = 16;
                weapon = 1;
                damage = 10;
                maxCooldown = 10;
            case 5: //super weapon
                maxHealth = 500;
                health = maxHealth;
                size = 32;
                weapon = 1;
                damage = 100;
                maxCooldown = 120;
            default:
                break;
        }
    }

    
    //getters
    public int getType() {
        return type;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getAngle() {
        return angle;
    }

    public int getHealth() {
        return health;
    }

    public int getCooldown() {
        return cooldown;
    }

    public int getMaxCooldown() {
        return maxCooldown;
    }

    public int getSize() {
        return size;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public int getWeapon() {
        return weapon;
    }

    public int getDamage() {
        return damage;
    }

    public Line getgHealth() {
        return gHealth;
    }

    public Line getrHealth() {
        return rHealth;
    }

    public int getTargetDistance() {
        return targetDistance;
    }

    public int getTargetX() {
        return targetX;
    }

    public int getTargetY() {
        return targetY;
    }

    
    //setters
    public void setType(int type) {
        this.type = type;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setAngle(int angle) {
        this.angle = angle;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setCooldown(int cooldown) {
        this.cooldown = cooldown;
    }

    public void setMaxCooldown(int maxCooldown) {
        this.maxCooldown = maxCooldown;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public void setWeapon(int weapon) {
        this.weapon = weapon;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public void setgHealth(Line gHealth) {
        this.gHealth = gHealth;
    }

    public void setrHealth(Line rHealth) {
        this.rHealth = rHealth;
    }

    public void setTargetDistance(int targetDistance) {
        this.targetDistance = targetDistance;
    }

    public void setTargetX(int targetX) {
        this.targetX = targetX;
    }

    public void setTargetY(int targetY) {
        this.targetY = targetY;
    }
    
    
    
    public void update(Player enemy) {
        this.targetDistance = 5000;
        for (int i = 0; i < (enemy.getSpawners()).size(); i++) {
            for (int j = 0; j < (((enemy.getSpawners()).get(i)).getShips()).size(); j++) {
                if (Math.sqrt(Math.pow((double) ((((enemy.getSpawners()).get(i)).getShips()).get(j)).getX() - this.x, 2) + Math.pow((double) ((((enemy.getSpawners()).get(i)).getShips()).get(j)).getY() - this.y, 2)) < this.targetDistance) {
                    // above is just sqrt((x2 - x1)^2 + (y2 - y1)^2)
                    this.targetDistance = (int) Math.sqrt(Math.pow((double) ((((enemy.getSpawners()).get(i)).getShips()).get(j)).getX() - this.x, 2) + Math.pow((double) ((((enemy.getSpawners()).get(i)).getShips()).get(j)).getY() - this.y, 2));
                    this.targetX = ((((enemy.getSpawners()).get(i)).getShips()).get(j)).getX();
                    this.targetY = ((((enemy.getSpawners()).get(i)).getShips()).get(j)).getY();
                }
            }
            if (Math.sqrt(Math.pow((double) (((enemy.getSpawners()).get(i)).getX() - this.x), 2) + Math.pow((double) (((enemy.getSpawners()).get(i)).getY() - this.y), 2)) < this.targetDistance) {
                this.targetDistance = (int) Math.sqrt(Math.pow((double) (((enemy.getSpawners()).get(i)).getX() - this.x), 2) + Math.pow((double) (((enemy.getSpawners()).get(i)).getY() - this.y), 2));
                this.targetX = ((enemy.getSpawners()).get(i)).getX();
                this.targetY = ((enemy.getSpawners()).get(i)).getY();
            }
        }
        if (this.targetX - this.x == 0) {
            if (this.targetY - this.y < 0) {
                this.angle = 270;
            } else {
                this.angle = 90;
            }
        } else if (this.targetY - this.y == 0) {
            if (this.targetX - this.x < 0) {
                this.angle = 180;
            } else {
                this.angle = 0;
            }
        } else {
            this.angle = (int) Math.toDegrees(Math.atan(((double) this.targetY - (double) this.y) / ((double) this.targetX - (double) this.x)));

            if (this.targetY - this.y > 0) {
                if (this.targetX - this.x > 0) {
                    //do nothing
                } else {
                    this.angle += 180;
                }
            } else if (this.targetX - this.x > 0) {
                this.angle += 360;
            } else {
                this.angle += 180;
            }
        }

        if (this.cooldown == 0) {
            if (this.targetDistance < 50) {
                this.fire();
                this.cooldown = 30;
            }
        } else {
            cooldown--;
        }

        for (int i = 0; i < lasers.size(); i++) {
            lasers.get(i).update();
            if (lasers.get(i).getX() > 1022 || lasers.get(i).getX() < 2
                    || lasers.get(i).getY() > 510 || lasers.get(i).getX() < 2) {
                this.lasers.remove(i);
            }
        }

        if (this.hitTime > 0) {
            hitTime--;
        }

        //laser collision
        for (int i = 0; i < this.lasers.size(); i++) {
            laserCheck:
            for (int j = 0; j < enemy.getSpawners().size(); j++) {
                for (int k = 0; k < enemy.getSpawners().get(j).getShips().size(); k++) {
                    //remove constants for other ship types
                    if (this.lasers.get(i).getX() >= enemy.getSpawners().get(j).getShips().get(k).getX() - 1
                            && this.lasers.get(i).getX() <= enemy.getSpawners().get(j).getShips().get(k).getX() + (enemy.getSpawners().get(j).getShips().get(k).getSize() - 1)
                            && this.lasers.get(i).getY() >= enemy.getSpawners().get(j).getShips().get(k).getY() - 7
                            && this.lasers.get(i).getY() <= enemy.getSpawners().get(j).getShips().get(k).getY() + (enemy.getSpawners().get(j).getShips().get(k).getSize() - 1)) {
                        enemy.getSpawners().get(j).getShips().get(k).setHealth(
                                enemy.getSpawners().get(j).getShips().get(k).getHealth() - this.damage);
                        this.hitX = this.lasers.get(i).getX();
                        this.hitY = this.lasers.get(i).getY();
                        this.hitTime = 10;
                        this.lasers.remove(i);
                        break laserCheck;
                    }
                }
                //remove constants for other spawner types
                if (this.lasers.get(i).getX() >= enemy.getSpawners().get(j).getX() - 1
                        && this.lasers.get(i).getX() <= enemy.getSpawners().get(j).getX() + (enemy.getSpawners().get(j).getSize() - 1)
                        && this.lasers.get(i).getY() >= enemy.getSpawners().get(j).getY() - 7
                        && this.lasers.get(i).getY() <= enemy.getSpawners().get(j).getY() + (enemy.getSpawners().get(j).getSize() - 1)) {
                    enemy.getSpawners().get(j).setHealth(
                            enemy.getSpawners().get(j).getHealth() - this.damage);
                    this.hitX = this.lasers.get(i).getX();
                    this.hitY = this.lasers.get(i).getY();
                    this.hitTime = 10;
                    this.lasers.remove(i);
                    break laserCheck;
                }
            }
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

    public void fire() {
        if(type == 0 || type == 5)
        {
            lasers.add(new Laser(this.x, this.y, this.angle));
            plonkSound.play();
        }
        else if(type == 2)
        {
            hitScan = new Line(x, y, targetX, targetY);
        }
    }
}
