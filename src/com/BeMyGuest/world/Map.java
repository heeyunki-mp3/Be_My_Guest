package com.BeMyGuest.world;

import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JPanel;
import javax.swing.Timer;


import com.BeMyGuest.util.KeyHandler;

public abstract class Map extends JPanel implements ActionListener {
	protected Timer timer;
	protected KeyHandler key;
	
	protected Clip themeClip;
	protected boolean themePlaying;
	
	protected String place;
	protected AudioInputStream audioIn;

	
	public void setBackgroundMusic() {
		URL url = this.getClass().getClassLoader().getResource("/bin/world/music"+place+".wav");
		System.out.println(url);

		themePlaying = false;
		

        try {
        	audioIn = AudioSystem.getAudioInputStream(url);
			themeClip = AudioSystem.getClip();
		} catch (Exception e) {
			e.printStackTrace();
		}
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
}
