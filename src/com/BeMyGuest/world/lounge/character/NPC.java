package com.BeMyGuest.world.lounge.character;

import java.io.*;
import java.net.URL;
import javax.sound.sampled.*;

import com.BeMyGuest.world.Communicate;

public class NPC extends Character{
	private final int CLOSE_ENOUGH;
	private Communicate voice;
	
	private Clip themeClip;
	private boolean themePlaying;
	private AudioInputStream audioIn;
	
	private boolean firstNPCCall;
	public NPC(int x, int y, String name, int f, int c, String quote) {
		super(x, y, false, f, name);
		CLOSE_ENOUGH = c;
		voice = new Communicate(100,50,quote); // TODO: try num
		
		firstNPCCall = true;
		
		URL url = this.getClass().getClassLoader().getResource("/world/music"+name+".wav");

		themePlaying = false;
		
        try {
        	audioIn = AudioSystem.getAudioInputStream(url);
			themeClip = AudioSystem.getClip();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public int getCloseEnough(){
        return CLOSE_ENOUGH;
	}
	
	@Override
	protected void moveHelp(){
		imageDirection =(int) (Math.random()*4);
        if(imageDirection == UP) {dy = -SPEED;}
		else {dy = 0;}
        
        if(imageDirection == DOWN) {dy = SPEED;}
		else if (imageDirection != UP) {dy = 0;}
        
        if(imageDirection == LEFT) {dx = -SPEED;}
		else {dx = 0;}
        
        if(imageDirection == RIGHT) {imageDirection = RIGHT; dx = SPEED;}
		else if (imageDirection != LEFT) {dx = 0;}
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
	public Clip getClip() {
		return themeClip;
	}
	public AudioInputStream getAudioIn() {
		return audioIn;
	}
	public boolean isFirstNPCCall() {
		return firstNPCCall;
	}
	public void setFirstNPCCall(boolean first) {
		firstNPCCall = first;
	}

}
