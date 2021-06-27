package com.BeMyGuest.world.lounge.character;


import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import com.BeMyGuest.util.KeyHandler;

public class Character {
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
    protected String[][] ani = new String[POS_LIST.length][4];

    protected int imageIndex;
    protected int imageDirection; 

    protected final int UP =0;
    protected final int DOWN = 1;
    protected final int RIGHT = 2;
    protected final int LEFT = 3;

    protected final int SPEED = 2;
   

    protected final int FRAME_SPEED;

    private boolean canUp = true;
    private boolean canDown = true;
    private boolean canRight = true;
    private boolean canLeft = true;

    private ArrayList<Follower> followers;

    public Character(int x, int y, boolean m, int f, boolean w, String name){
        this.x = x;
        this.y = y;
        imageIndex = 0;
        FRAME_SPEED = f;
        
        followers = new ArrayList<Follower>();

        moveable = m;
		walkingAround = w;


        this.name = name;
        initializeAni();

        setImage(ani);
    }
    
    public void initializeAni(){
        for (int r=0; r<ani.length; r++){
            for (int c=0; c<ani[0].length; c++){
                ani[r][c] = "world/pic/character/" + name+ "/"+ POS_LIST[r] + (c+1) + ".png";

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
        
        moveFollowerPos();
        removeFollower();
        
        x += dx;
        y += dy;
    }
    
    protected void moveHelp(){
    	//This will be differently implemented in NPC, Player, and Follower classes
    }

    public void removeFollower(){
        for (int i=0; i<followers.size(); i++){
            if (followers.get(i).getImageIndex() == 6 && followers.get(i).isConsumable()){
                followers.remove(i);
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
        	if ((this.x - followers.get(i).getX())*dx < 0){ //if follower and owner will collide
        		followers.get(i).setX(x);
        	}else {
        		followers.get(i).setX(this.x+dx);
        	}
        	if ((this.y - followers.get(0).getY())*dy < 0){ //if follower and owner will collide
        		followers.get(i).setY(y);
        	}else {
        		followers.get(i).setY(this.y+dy);
        	}
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
        	System.out.println(name + " frame " + frame + "out of " + FRAME_SPEED);
            imageIndex = ((imageIndex +1) % (animation[0].length));
        }
    }

    public boolean moveable(){
        return moveable;
    }

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

    public Image getImage() {
        return animation[imageDirection][imageIndex];
    }
    
}
