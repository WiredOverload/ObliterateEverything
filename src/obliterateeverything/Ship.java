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
public class Ship {

    private int x;
    private int y;
    private int xVelocity;
    private int yVelocity;
    private int acceleration;
    private int maxSpeed;
    private int targetX;
    private int targetY;
    private int targetDistance;
    private int angle;
    private int cooldown;
    private short type;
    /*
        1 = standard
        2 = glass cannon
        3 = shield
        go through all attributes and change based on type
     */
    private int health;
    private int damage;
    private int hitX;
    private int hitY;
    private int hitTime;
    private ArrayList<Laser> lasers;

    public Ship() {
        this.x = 0;
        this.y = 0;
        this.xVelocity = 0;
        this.yVelocity = 0;
        this.angle = 0;
        this.type = 1;
        this.health = 100;
        this.damage = 20;
        this.acceleration = 5;
        this.maxSpeed = 10;
        this.cooldown = 0;
        this.lasers = new ArrayList<Laser>();
        this.targetDistance = 5000;
        this.hitX = -1;
        this.hitY = -1;
        this.hitTime = 0;
    }

    public Ship(short type, int x, int y) {
        this.x = x;
        this.y = y;
        this.xVelocity = 0;
        this.yVelocity = 0;
        this.type = type;
        this.angle = 0;
        this.acceleration = 5;
        this.maxSpeed = 10;
        this.cooldown = 0;
        this.lasers = new ArrayList<Laser>();
        this.targetDistance = 5000;
        this.hitX = -1;
        this.hitY = -1;
        this.hitTime = 0;

        if (type == 1) {
            this.health = 100;
            this.damage = 20;
        } else if (this.type == 2) {
            this.health = 50;
            this.damage = 40;
        } else if (this.type == 3) {
            this.health = 200;
            this.damage = 10;
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

    public int getxVelocity() {
        return xVelocity;
    }

    public int getyVelocity() {
        return yVelocity;
    }

    public int getAcceleration() {
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

    //setters
    public void setxVelocity(int xVelocity) {
        this.xVelocity = xVelocity;
    }

    public void setyVelocity(int yVelocity) {
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

    public void setAcceleration(int acceleration) {
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
                this.angle = -90;
            } else {
                this.angle = 90;
            }
        } else {
            this.angle = (int) Math.toDegrees(Math.atan(((double) this.targetY - (double) this.y) / ((double) this.targetX - (double) this.x)));
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
                            && this.lasers.get(i).getX() <= enemy.getSpawners().get(j).getShips().get(k).getX() + 15
                            && this.lasers.get(i).getY() >= enemy.getSpawners().get(j).getShips().get(k).getY() - 7
                            && this.lasers.get(i).getY() <= enemy.getSpawners().get(j).getShips().get(k).getY() + 15) {
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
                        && this.lasers.get(i).getX() <= enemy.getSpawners().get(j).getX() + 31
                        && this.lasers.get(i).getY() >= enemy.getSpawners().get(j).getY() - 7
                        && this.lasers.get(i).getY() <= enemy.getSpawners().get(j).getY() + 31) {
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
        if (this.x > 1022) {
            this.xVelocity = -10;
        } else if (this.x < 2) {
            this.xVelocity = 10;
        }
        this.x += this.xVelocity;

        if (this.y > 510) {
            this.yVelocity = -10;
        } else if (this.y < 2) {
            this.yVelocity = 10;
        }
        this.y += this.yVelocity;
    }

    public void fire() {
        lasers.add(new Laser(this.x, this.y, this.angle));
    }
}
