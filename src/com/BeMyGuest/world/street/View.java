package com.BeMyGuest.world.street;
import java.awt.Image;

import javax.swing.ImageIcon;

import com.BeMyGuest.util.KeyHandler;

public class View {
	private int dx;

    private int x;
    private int y;

    private Image image;

    private int imageIndex;
    private int imageDirection; 

	private final int RIGHT = 2;
	private final int LEFT = 3;

    private final int SPEED = 2;

	protected boolean right;
	protected boolean left; 

    private boolean canRight = true;
    private boolean canLeft = true;


    public View(int x, int y, String imgLoc){
        this.x = x;
        this.y = y;
        imageIndex = 0;

        ImageIcon ii;
        ii = new ImageIcon(this.getClass().getClassLoader().getResource(imgLoc));
        image = ii.getImage();
    }

    public int getImageIndex(){
        return imageIndex;
    }

    public void move() {
        checkCanBe();
        moveHelp();
        System.out.println(x + ", "+ y);
        x += dx;
    }

    private void moveHelp(){
        if(left) {dx = -SPEED;}
		else {dx = 0;}
        if(right) {dx = +SPEED;}
		else if (!left) {dx = 0;}
    }


    public void keyClick(KeyHandler key){   
        if(key.right.down) {
            imageDirection = LEFT;
            System.out.println("left clicked in View");
            left = true;		
        }else {
            left= false;
        }
        if(key.left.down) {
            imageDirection = RIGHT;
            System.out.println("right clicked in View");
            right = true;
        }else {
            right = false;
        } 
        move();
    }
   
    public void checkCanBe(){  
        if (x>0){
            canRight = false;
        }else{
            canRight=true;
        }
        if(x<-3700){ 
            canLeft = false;
        }else{
            canLeft = true;
        }

        if (!canRight && right){
            x -= SPEED*3;
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if (!canLeft && left){
            x += SPEED*3;
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
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
        return image;
    }
}
