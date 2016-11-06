/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package obliterateeverything;

import java.io.File;
import java.util.ArrayList;
import javafx.scene.media.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

/**
 *
 * @author Michael Hodges
 */
public class Ship {

    private static final boolean DEBUG = false;
    
    private int x;
    private int y;
    private double xVelocity;
    private double yVelocity;
    private double acceleration;
    private int maxSpeed;
    private int size;
    private int targetX;
    private int targetY;
    private int targetDistance;
    private int angle;
    private int cooldown;
    private short type;
    private Line gHealth = new Line();//green portion of health bar
    private Line rHealth = new Line();//red portion of health bar
    private Line line = new Line(); //for debug only
    /*
        1 = standard
        2 = glass cannon
        3 = shield
        go through all attributes and change based on type
    
        270
     180 + 0
        90
     */
    private int health;
    private int maxHealth;
    private int damage;
    private int hitX;
    private int hitY;
    private int hitTime;
    private ArrayList<Laser> lasers;
    //Media music = new Media(new File("slink.mp3").toURI().toString()); //move this
    //MediaPlayer mediaPlayer = new MediaPlayer(music);//                  and this
    AudioClip plonkSound = new AudioClip(new File("slink2.mp3").toURI().toString());

    public Ship() {
        this.x = 0;
        this.y = 0;
        this.xVelocity = 0;
        this.yVelocity = 0;
        this.angle = 0;
        this.size = 16;
        this.type = 1;
        this.health = 100;
        this.maxHealth = 100;
        this.damage = 20;
        this.acceleration = .1;
        this.maxSpeed = 10;
        this.cooldown = -2;
        this.lasers = new ArrayList<Laser>();
        this.targetDistance = 5000;
        this.hitX = -1;
        this.hitY = -1;
        this.hitTime = 0;
        gHealth.setStroke(Color.LAWNGREEN);
        rHealth.setStroke(Color.RED);
    }

    public Ship(short type, int x, int y) {
        this.x = x;
        this.y = y;
        this.xVelocity = 0;
        this.yVelocity = 0;
        this.type = type;
        this.angle = 0;
        this.acceleration = 3; //I have no idea what the acceleration should be
        this.maxSpeed = 10;
        this.cooldown = -2; //-2 for debug target line
        this.lasers = new ArrayList<Laser>();
        this.targetDistance = 5000;
        this.hitX = -1;
        this.hitY = -1;
        this.hitTime = 0;
        gHealth.setStroke(Color.LAWNGREEN);
        rHealth.setStroke(Color.RED);

        if (type == 1) {
            this.health = 100;
            this.maxHealth = 100;
            this.damage = 20;
            this.size = 16;
        } else if (this.type == 2) {
            this.health = 50;
            this.maxHealth = 50;
            this.damage = 40;
            this.size = 16;
        } else if (this.type == 3) {
            this.health = 200;
            this.maxHealth = 200;
            this.damage = 10;
            this.size = 16;
        }
        
        

    }

    //getters
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getAngle() {
        return angle;
    }

    public short getType() {
        return type;
    }

    public int getHealth() {
        return health;
    }

    public int getDamage() {
        return damage;
    }

    public double getxVelocity() {
        return xVelocity;
    }

    public double getyVelocity() {
        return yVelocity;
    }

    public double getAcceleration() {
        return acceleration;
    }

    public int getTargetX() {
        return targetX;
    }

    public int getTargetY() {
        return targetY;
    }

    public int getTargetDistance() {
        return targetDistance;
    }

    public int getCooldown() {
        return cooldown;
    }

    public ArrayList<Laser> getLasers() {
        return lasers;
    }

    public int getHitX() {
        return hitX;
    }

    public int getHitY() {
        return hitY;
    }

    public int getHitTime() {
        return hitTime;
    }
    
    public Line getLine() {
        return line;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public Line getgHealth() {
        return gHealth;
    }

    public Line getrHealth() {
        return rHealth;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public int getSize() {
        return size;
    }

    
    
    //setters
    public void setxVelocity(double xVelocity) {
        this.xVelocity = xVelocity;
    }

    public void setyVelocity(double yVelocity) {
        this.yVelocity = yVelocity;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setAngle(short angle) {
        this.angle = angle;
    }

    public void setType(short type) {
        this.type = type;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public void setAcceleration(double acceleration) {
        this.acceleration = acceleration;
    }

    public void setTargetX(int targetX) {
        this.targetX = targetX;
    }

    public void setTargetY(int targetY) {
        this.targetY = targetY;
    }

    public void setTargetDistance(int targetDistance) {
        this.targetDistance = targetDistance;
    }

    public void setAngle(int angle) {
        this.angle = angle;
    }

    public void setCooldown(int cooldown) {
        this.cooldown = cooldown;
    }

    public void setLasers(ArrayList<Laser> lasers) {
        this.lasers = lasers;
    }

    public void setHitX(int hitX) {
        this.hitX = hitX;
    }

    public void setHitY(int hitY) {
        this.hitY = hitY;
    }

    public void setHitTime(int hitTime) {
        this.hitTime = hitTime;
    }

    public void setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public void setgHealth(Line gHealth) {
        this.gHealth = gHealth;
    }

    public void setrHealth(Line rHealth) {
        this.rHealth = rHealth;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public void setSize(int size) {
        this.size = size;
    }

    
    
    
    //methods
    public void update(Player enemy) {
        //looks through all enemy ships and spawners for the closest, then sets it as target
        //change to a first pass with estimates w/o exponants
        //then second pass for closest 10 with precise distance logic like below
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
        //precise direction equation, if laggy replace with estimates

        if (this.targetX - this.x == 0) {
            if (this.targetY - this.y < 0) {
                this.angle = 270;
            } else {
                this.angle = 90;
            }
        }
        else if (this.targetY - this.y == 0) {
            if (this.targetX - this.x < 0) {
                this.angle = 180;
            } else {
                this.angle = 0;
            }
        }
        else {
            this.angle = (int) Math.toDegrees(Math.atan(((double) this.targetY - (double) this.y) / ((double) this.targetX - (double) this.x)));
            
            if(this.targetY - this.y > 0)
            {
                if(this.targetX - this.x > 0)
                {
                    //do nothing
                }
                else
                {
                    this.angle += 180;
                }
            }
            else
            {
                if(this.targetX - this.x > 0)
                {
                    this.angle += 360;
                }
                else
                {
                    this.angle += 180;
                }
            }
        }

        this.xVelocity += Math.cos(angle) * this.acceleration;
        if (this.xVelocity > this.maxSpeed) {
            this.xVelocity = this.maxSpeed;
        }
        if (this.xVelocity < -this.maxSpeed) {
            this.xVelocity = -this.maxSpeed;
        }

        this.yVelocity += Math.sin(angle) * this.acceleration;
        if (this.yVelocity > this.maxSpeed) {
            this.yVelocity = this.maxSpeed;
        }
        if (this.yVelocity < -this.maxSpeed) {
            this.yVelocity = -this.maxSpeed;
        }

        //lasers
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

        if(this.hitTime > 0)
            hitTime--;
        
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

        //error check for sides of window
        //change for scalable windows
        if (this.x > 1024 - maxSpeed) {
            this.xVelocity = -10;
        } else if (this.x < maxSpeed) {
            this.xVelocity = 10;
        }
        this.x += this.xVelocity;

        if (this.y > 512 - maxSpeed) {
            this.yVelocity = -10;
        } else if (this.y < maxSpeed) {
            this.yVelocity = 10;
        }
        this.y += this.yVelocity;
        
        //crazy cooldowns for efficient(?) debug target line and health bar
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
        
        if(DEBUG)
        {
            line.setStartX(this.x);
            line.setStartY(this.y);
            line.setEndX(this.targetX);
            line.setEndY(this.targetY);

            //debug position and angle
            System.out.print(x);
            System.out.print(", ");
            System.out.print(y);
            System.out.print(": ");
            System.out.println(angle);
        }
        
    }

    public void fire() {
        lasers.add(new Laser(this.x, this.y, this.angle));
        plonkSound.play();
    }
}
