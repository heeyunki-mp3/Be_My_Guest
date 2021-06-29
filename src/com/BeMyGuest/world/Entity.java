package com.BeMyGuest.world;

import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public abstract class Entity {
	protected int dx;
	protected int dy;
	protected int x;
	protected int y;
	
    protected String name;
    private Image image;
    protected boolean moveable;
	protected boolean walkingAround; //for NPC
	
    protected Image[][] animation;
    protected static final String[] POS_LIST = {"Back", "Front", "Right","Left"};
    protected String[][] ani; //picture url

    protected int imageIndex;
    protected int imageDirection; 

    public final int UP =0;
    public final int DOWN = 1;
    public final int RIGHT = 2;
    public final int LEFT = 3;

    protected final int SPEED = 2;
    
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
       this.y = y;
    }
    public String getName() {
    	return name;
    }

    public Image getImage() {
        return animation[imageDirection][imageIndex];
    }
}
