package com.BeMyGuest.world.lounge.character;

import com.BeMyGuest.util.KeyHandler;
import com.BeMyGuest.world.Map;

public class Player extends Character{

	protected boolean up;
	protected boolean down;
	protected boolean right;
	protected boolean left; 
    protected boolean yes;
    
    private boolean actionOn;
    
    public Player(int x, int y, Map p) {
        super(x, y, true, 30, false, "user", p);
        
    }

    public void keyClick(KeyHandler key){  
   
        if(key.up.down) {
            up = true;
        }else {
            up = false;
        }
        if(key.down.down) {
            down = true;
        }else {
            down = false;
        }
        if(key.left.down) {
            left = true;		
        }else {
            left= false;
        }
        if(key.right.down) {
            right = true;
        }else {
            right = false;
        } 
        if(key.yes.down && !actionOn){
            System.out.println("yes pressed");
            yes = true;
        }else{
            yes = false;
        }
        if(actionOn){
            key.release(KeyHandler.YES);
        }
        checkCanBe(place);
        move();
    }
   
    @Override
	protected void moveHelp(){
        if(up) {imageDirection = UP; dy = -SPEED;}
		else {dy = 0;}
        if(down) {imageDirection = DOWN; dy = SPEED;}
		else if (!up) {dy = 0;}
        if(left) {imageDirection = LEFT; dx = -SPEED;}
		else {dx = 0;}
        if(right) {imageDirection = RIGHT; dx = SPEED;}
		else if (!left) {dx = 0;}
    }
   
    
    public void playerBusy(boolean busy){
        actionOn = busy;
    }
    
    public boolean yesPressed(){
        return yes;
    }
    public void setYes(boolean yea){
        yes = yea;
    }

    public boolean close(NPC other){
        int npcCloseness = other.getCloseEnough();
        if ((Math.abs(this.x -other.getX()) < npcCloseness) && (Math.abs(this.y - other.getY())<npcCloseness)){
            //System.out.println("Close to " + other);
            return true;
        }
        return false;
    }
}
