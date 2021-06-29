package com.BeMyGuest.world.lounge.character;


import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import com.BeMyGuest.util.KeyHandler;
import com.BeMyGuest.world.Entity;
import com.BeMyGuest.world.Map;

public class Character extends Entity{
    protected final int FRAME_SPEED;

    private boolean canUp = true;
    private boolean canDown = true;
    private boolean canRight = true;
    private boolean canLeft = true;

    private ArrayList<Follower> followers;
    
    protected Map place;

    public Character(int x, int y, boolean m, int f, boolean w, String name, Map p){
        this.x = x;
        this.y = y;
        imageIndex = 0;
        FRAME_SPEED = f;
        ani = new String[POS_LIST.length][4];
        
        followers = new ArrayList<Follower>();

        moveable = m;
		walkingAround = w;

		place = p;
		
        this.name = name;
        initializeAni();

        setImage(ani);
    }
    
    public Character(int x, int y, boolean m, int f, boolean w, String name, Map p, int numFace){
        this.x = x;
        this.y = y;
        imageIndex = 0;
        FRAME_SPEED = f;
        ani = new String[POS_LIST.length][numFace];
        
        followers = new ArrayList<Follower>();

        moveable = m;
		walkingAround = w;

		place = p;
		
        this.name = name;
        initializeAni();

        setImage(ani);
    }
    
    
    public void initializeAni(){
    	//System.out.println(name + " r: "+ ani.length + " c: "+ ani[0].length);
        for (int r=0; r<ani.length; r++){
            for (int c=0; c<ani[0].length; c++){
                ani[r][c] = "world/pic/character/" + name+ "/"+ POS_LIST[r] + (c+1) + ".png";//TODO chance the url for small ones
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
    
    public void move() {
        moveHelp();
        animate();
        
        if (dx !=0 || dy!=0) {
        	moveFollowerPos();
        }
        removeFollower();
        
        x += dx;
        y += dy;
    }
    
    protected void moveHelp(){
    	//This will be differently implemented in NPC, Player, and Follower classes
    }

    public void removeFollower(){
        for (int i=0; i<followers.size(); i++){
        	//System.out.println("follower is consumable" + followers.get(i).isConsumable());
            if (followers.get(i).getImageIndex() == followers.get(i).ani[0].length -1 && followers.get(i).isConsumable()){
                //System.out.println("remove follower at " +followers.get(i).getImageIndex());
            	followers.remove(i);
                moveFollowerPos();
            }
        }
    }

    public int getImageIndex(){
        return imageIndex;
    }

    public void addFollower(Follower f){
        followers.add(f);
    }
    
    public ArrayList<Follower> getFollowers(){
        return followers;
    }

    public void moveFollowerPos(){
        for (int i = 0; i<followers.size(); i++){
        	//followers.get(i).setX(this.x-dx*20-dx*i*20);
        	//followers.get(i).setY(this.y-dy*20-dy*i*20);
        	
        	if ((this.x - followers.get(i).getX())*dx < 0){ //if follower and owner will collide
        		followers.get(i).setX(x);
        	}else {
        		followers.get(i).setX(this.x-dx*(i+1)*(followers.get(i).getWidth()/2));
        	}
        	if ((this.y - followers.get(0).getY())*dy < 0){ //if follower and owner will collide
        		followers.get(i).setY(y);
        	}else {
        		followers.get(i).setY(this.y-dy*(i+1)*(followers.get(i).getHeight()/3));
        	}
        	if (dx == 0 && dy == 0) {
        		int multiplier = 0;
        		if (this.imageDirection == LEFT || this.imageDirection == UP) {
        			multiplier = -2;
        		}
        		else {
        			multiplier = 2;
        		}
        		//followers.get(i).setY(this.y-dy*20-multiplier*(i+1)*20);
        		followers.get(i).setX(this.x-dx*20-multiplier*(i+1)*20);
        	}
        	followers.get(i).setImageDirection(this.imageDirection);
        }
    }

    protected int frame = 0;
    protected void animate(){
        frame ++;
        frame %= FRAME_SPEED;
        if (dx == 0 && dy == 0 && moveable){
            imageIndex = 0;
        }
        else if (frame == 0){
            imageIndex = ((imageIndex +1) % (animation[0].length));
        }
    }
    
    public void checkCanBe(Map place) {
        if (!canUp && dy<0){
            y += SPEED +3;
            place.checkObstacles(this);
            /*try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/
        }
        if (!canDown && dy>0){
            y -= SPEED+3;
            place.checkObstacles(this);
            /*try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/
        }
        if (!canRight && dx>0){
            x -= SPEED+3;
            place.checkObstacles(this);
            /*try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/
        }
        if (!canLeft && dx<0){
            x += SPEED+3;
            place.checkObstacles(this);
            /*try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/
        }
    } 
    
    public int getImageDirection() {
    	return imageDirection;
    }

    public boolean moveable(){
        return moveable;
    }
    
    //for checking if there are obstacles in front
    public boolean canUp() {
    	return canUp;
    }
    public boolean canDown() {
    	return canDown;
    }
    public boolean canLeft() {
    	return canLeft;
    }
    public boolean canRight() {
    	return canRight;
    }
    public void setCanUp(boolean c) {
    	canUp = c;
    }
    public void setCanDown(boolean c) {
    	canDown = c;
    }
    public void setCanLeft(boolean c) {
    	canLeft = c;
    }
    public void setCanRight(boolean c) {
    	canRight = c;
    }
    
    public void setPlace(Map p) {
    	place = p;
    }
}
