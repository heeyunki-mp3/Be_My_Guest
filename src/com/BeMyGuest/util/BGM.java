package com.BeMyGuest.util;

import java.io.*;
import java.net.URL;
import javax.sound.sampled.*;
import javax.swing.*;

import com.BeMyGuest.world.Map;
import com.BeMyGuest.world.lounge.character.NPC;

public class BGM extends JFrame{
	private Clip nowPlaying;
	private boolean isPlaying;
	
	public BGM() {
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setTitle("BGM");
        this.setVisible(true); 
	}
	
	public boolean play(String destination){
	    try {
	        URL url = this.getClass().getClassLoader().getResource(destination);
	        
	        AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);

	        nowPlaying = AudioSystem.getClip();

	        nowPlaying.open(audioIn);
	        nowPlaying.loop(Clip.LOOP_CONTINUOUSLY);

	        isPlaying = true;
	        FloatControl gainControl = (FloatControl) nowPlaying.getControl(FloatControl.Type.MASTER_GAIN);
	        gainControl.setValue(-10.0f);
	        nowPlaying.start();

        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
	    return true;
   }
	
	public boolean changePlay(String destination){
		nowPlaying.close();
	    try {
	        URL url = this.getClass().getClassLoader().getResource(destination);
	        AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);

	        nowPlaying = AudioSystem.getClip();

	        nowPlaying.open(audioIn);
	        nowPlaying.loop(Clip.LOOP_CONTINUOUSLY);

	        isPlaying = true;
	        FloatControl gainControl = (FloatControl) nowPlaying.getControl(FloatControl.Type.MASTER_GAIN);
	        gainControl.setValue(-10.0f);
	        nowPlaying.start();

        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
	    return true;
   }
	public void stop() {
		nowPlaying.close();
	}
}

