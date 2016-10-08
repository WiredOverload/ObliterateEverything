/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package obliterateeverything;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author Michael Hodges
 */
public class Buttons {

    private int x;
    private int y;
    private int width;
    private int height;
    private Rectangle r;
    private boolean added = false;
    
    public Buttons(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.r = new Rectangle(x, y, width, height);
        r.setFill(Color.web("FF0000", 0.5));
    }
    
    public Buttons(int x, int y, int size) {
        this.x = x;
        this.y = y;
        this.width = size;
        this.height = size;
        this.r = new Rectangle(x, y, width, height);
        r.setFill(Color.web("FF0000", 0.5));
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Rectangle getR() {
        return r;
    }

    public boolean isAdded() {
        return added;
    }

    
    
    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setR(Rectangle r) {
        this.r = r;
    }

    public void setAdded(boolean added) {
        this.added = added;
    }
    
    
    public boolean collision(int x, int y)
    {
        if(x >= this.x && y > this.y && x < this.x + this.width && y < this.y + this.height)
            return true;
        else
            return false;
    }
    
}
