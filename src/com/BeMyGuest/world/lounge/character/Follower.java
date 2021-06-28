package com.BeMyGuest.world.lounge.character;

import com.BeMyGuest.world.Map;

public class Follower extends Character{
	private final boolean consumable;
	public Follower(int x, int y, int f, boolean c, String name, Map m, int numFace) {
		super(x, y, false, f, false, name, m, numFace);
		consumable = c;
		
	}
	
	@Override
	public void move() {
      //  moveHelp();
        animate();
    }
	protected void setImageDirection(int direction) {
		imageDirection = direction;
	}
	@Override
	protected void moveHelp(){
    	if (dx<0) {imageDirection = LEFT;}
    	if (dx>0) {imageDirection = RIGHT;}
    	if (dy<0) {imageDirection = UP;}
    	if (dy>0) {imageDirection = DOWN;}
    }
	public boolean isConsumable() {
		return consumable;
	}
	public int getWidth() {
		return animation[0][0].getWidth(null);
	}
	public int getHeight() {
		return animation[0][0].getHeight(null);
	}
}
