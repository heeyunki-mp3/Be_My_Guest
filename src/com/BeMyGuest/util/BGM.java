package com.BeMyGuest.util;

import java.io.*;
import java.net.URL;
import javax.sound.sampled.*;
import javax.swing.*;

import com.BeMyGuest.world.Map;
import com.BeMyGuest.world.lounge.character.NPC;

public class BGM extends JFrame{
	public BGM() {
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setTitle("BGM");
        this.setVisible(true); 
   }
	public void play(NPC which) {
		
		try {
			which.getClip().open(which.getAudioIn());
		} catch (LineUnavailableException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        which.getClip().loop(Clip.LOOP_CONTINUOUSLY);

        which.setThemePlaying(true);
        FloatControl gainControl = (FloatControl) which.getClip().getControl(FloatControl.Type.MASTER_GAIN);
        gainControl.setValue(-10.0f);
        which.getClip().start();
	}
	public void stop(NPC which){
        which.getClip().close();
        which.setThemePlaying(false); 
	}
	public void play(Map which) {
		
		try {
			which.getClip().open(which.getAudioIn());
		} catch (LineUnavailableException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        which.getClip().loop(Clip.LOOP_CONTINUOUSLY);

        which.setThemePlaying(true);
        FloatControl gainControl = (FloatControl) which.getClip().getControl(FloatControl.Type.MASTER_GAIN);
        gainControl.setValue(-10.0f);
        which.getClip().start();
	}
	public void stop(Map which){
        which.getClip().close();
        which.setThemePlaying(false); 
	}
}

