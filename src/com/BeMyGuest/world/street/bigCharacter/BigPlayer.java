package com.BeMyGuest.world.street.bigCharacter;

import com.BeMyGuest.util.KeyHandler;

public class BigPlayer extends BigCharacter {
	public BigPlayer(int x, int y, String name, int f, int face) {
		super(x, y, name, f, face);
		// TODO Auto-generated constructor stub
	}
	public void keyClick(KeyHandler key){  
        if(key.up.down) {
            imageDirection = DOWN;
            up = true;
        }else {
            up = false;
        }
        if(key.down.down) {
            imageDirection = UP;
            down = true;
        }else {
            down = false;
        }
        if(key.left.down) {
            System.out.println("left clicked in player");
            imageDirection = LEFT;
            left = true;		
        }else {
            left= false;
        }
        if(key.right.down) {
            imageDirection = RIGHT;
            right = true;
        }else {
            right = false;
        } 
        move();
    }
	protected void moveHelp(){
        if(up) {imageDirection = UP;}

        if(down) {imageDirection = DOWN; }

        if(left) {imageDirection = LEFT; dx = -SPEED;}
		else {dx = 0;}
        if(right) {imageDirection = RIGHT; dx = SPEED;}
		else if (!left) {dx = 0;}
    }
}
