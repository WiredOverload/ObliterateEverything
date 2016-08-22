/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package obliterateeverything;

/**
 *
 * @author Michael Hodges
 */
public class Laser {

    private int x;
    private int y;
    private int angle;
    private int velocity;

    public Laser() {
        this.x = 0;
        this.y = 0;
        this.angle = 0;
        this.velocity = 15;
    }

    public Laser(int x, int y, int angle) {
        this.x = x;
        this.y = y;
        this.angle = angle;
        this.velocity = 15;
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

    public int getVelocity() {
        return velocity;
    }

    //setters
    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setAngle(int angle) {
        this.angle = angle;
    }

    public void setVelocity(int velocity) {
        this.velocity = velocity;
    }

    //methods
    public void update() {
        //collison has to be with the ship object

        this.x += Math.cos(angle) * velocity;
        this.y += Math.sin(angle) * velocity;

    }
}
