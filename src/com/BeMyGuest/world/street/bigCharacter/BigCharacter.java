package com.BeMyGuest.world.street.bigCharacter;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import com.BeMyGuest.world.Entity;
import com.BeMyGuest.world.Map;
import com.BeMyGuest.world.lounge.character.Follower;

public class BigCharacter extends Entity {   
	protected final int FRAME_SPEED;

    protected boolean up;
	protected boolean down;
	protected boolean right;
	protected boolean left; 

    public BigCharacter(int x, int y, String name, int f, int numFace){
        this.x = x;
        this.y = y;
        imageIndex = 0;
        FRAME_SPEED = f;
        this.name = name;
        
        ani = new String[POS_LIST.length][numFace];

        initializeAni();

        setImage(ani);

    }
    public void initializeAni(){
        for (int r=0; r<ani.length; r++){
            for (int c=0; c<ani[0].length; c++){
                ani[r][c] = "world/pic/character/" + name+ "/big/"+ POS_LIST[r] + (c+1) + ".png";
            }
        }
    }
    
    public void setImage(String[][] imgLoc){
        animation = new Image[imgLoc.length][imgLoc[0].length];

        ImageIcon ii;
        for (int r=0; r<imgLoc.length; r++){
          //  System.out.println(imgLoc[r]);
            for (int c=0; c<imgLoc[0].length; c++){
                System.out.println(imgLoc[r][c]);
                
                ii = new ImageIcon(this.getClass().getClassLoader().getResource(imgLoc[r][c]));
               
                animation[r][c] = ii.getImage();
            }
        }
    }
    private int frame = 0;
    protected void animate(){
        frame ++;
        frame %= FRAME_SPEED;
        if (dx==0 && dy==0 && (imageDirection ==LEFT ||imageDirection == RIGHT)){
            imageIndex = 0;
        }
        else if (frame == 0){
            imageIndex = ((imageIndex +1) % (animation[0].length));
        }
    }
    
    public int getImageIndex(){
        return imageIndex;
    }
    
    public void move() {
    	moveHelp();
        animate();
    }
    protected void moveHelp() {
    	//Implemented differently for npc and player
    }
   
}
