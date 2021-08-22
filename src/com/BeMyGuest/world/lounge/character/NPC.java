package com.BeMyGuest.world.lounge.character;

import java.io.*;
import java.net.URL;
import javax.sound.sampled.*;

import com.BeMyGuest.util.BGM;
import com.BeMyGuest.world.Communicate;
import com.BeMyGuest.world.Map;

public class NPC extends Character{
	private final int CLOSE_ENOUGH;
	private Communicate voice;

	private boolean themePlaying;
	private boolean firstNPCCall;
	
	public NPC(int x, int y, String name, int f, int c, boolean walkable,String quote, Map p) {
		super(x, y, false, f, walkable, name, p);
		CLOSE_ENOUGH = c;
		
		int height =35*quote.split("\n").length;
		
		voice = new Communicate(15*quote.length(),height,quote); // TODO: try num
		firstNPCCall = true;
		
		themePlaying = false;
	}
	
	public void playTheme(BGM player) {
		themePlaying = player.changePlay("world/music/"+name+".wav");
	}
	public void stopTheme(BGM player) {
		player.stop();
		themePlaying = false;
	}
	public int getCloseEnough(){
        return CLOSE_ENOUGH;
	}
	
	@Override
	public void initializeAni(){
		if(walkingAround) {
			super.initializeAni();
			return;
		}
		//for those who are not walking around but rotating at the same spot
		ani = new String[POS_LIST.length][1];
        for (int r=0; r<ani.length; r++){
            ani[r][0] = "world/pic/character/" + name+ "/"+ POS_LIST[r] + ".png";
        }
    }
	private boolean move = false;
	@Override
	protected void moveHelp(){
		animateTurn();
		if (walkingAround) {
			if(move) {
				checkCanBe(place);
		        if(imageDirection == UP) {dy = -SPEED;}
				else {dy = 0;}
		        
		        if(imageDirection == DOWN) {dy = SPEED;}
				else if (imageDirection != UP) {dy = 0;}
		        
		        if(imageDirection == LEFT) {dx = -SPEED;}
				else {dx = 0;}
		        
		        if(imageDirection == RIGHT) {imageDirection = RIGHT; dx = SPEED;}
				else if (imageDirection != LEFT) {dx = 0;}
			}
			else {
				dy=0;
				dx=0;
			}
		}
    }

	protected void animateTurn() {
        if (frame==2){
        	//determines which direction it will turn
        	imageDirection =(int) (Math.random()*POS_LIST.length);
			//determines if it will move forward or not
        	move = ((int)(Math.random()*3) ==1);
        }
	}
	
	public Communicate getVoice() {
		return voice;
	}
	public void setThemePlaying(boolean playing) {
		themePlaying = playing;
	}
	public boolean isThemePlaying() {
		return themePlaying;
	}
	public boolean isFirstNPCCall() {
		return firstNPCCall;
	}
	public void setFirstNPCCall(boolean first) {
		firstNPCCall = first;
	}


}
