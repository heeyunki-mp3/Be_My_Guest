package com.BeMyGuest.util;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import com.BeMyGuest.world.Map;

public class KeyHandler implements KeyListener {

	public static final int UP = 0;
	public static final int DOWN = 1;
	public static final int LEFT = 2;
	public static final int RIGHT = 3;
	public static final int YES = 4;
	
	public static ArrayList<Key> keys = new ArrayList<Key>(); // ArrayList vs List
	
	public class Key {
		public int presses, absorbs;
		public boolean down, clicked;

		
		public Key() {
			keys.add(this);
		}
		
		public void toggle(boolean pressed) {
			if (pressed !=down ) {
				down = pressed;
			}
			if(pressed) {
				presses++;
			}
		}
		
		public void tick() {
			if(absorbs < presses) {
				absorbs++;
				clicked = true;
			} else {
				clicked = false;
			}
		}
	}
	
	public Key up = new Key();
	public Key down = new Key();
	public Key left = new Key();
	public Key right = new Key();
	public Key yes = new Key();
	
	
	public KeyHandler(Map game) {
		game.addKeyListener(this);
	}

	
	public void releaseAll() {
		for (int i = 0; i< keys.size(); i++) {
			keys.get(i).down = false;
		}
	}
	public void tick() {
		for (int i=0; i< keys.size(); i++) {
			keys.get(i).tick();
		}
	}
	public void release(int which){
		keys.get(which).down = false;
	}
	
	public void toggle(KeyEvent e, boolean pressed) {
		if (e.getKeyCode() == KeyEvent.VK_W) up.toggle(pressed);
		if (e.getKeyCode() == KeyEvent.VK_S) down.toggle(pressed);
		if (e.getKeyCode() == KeyEvent.VK_A) left.toggle(pressed);
		if (e.getKeyCode() == KeyEvent.VK_D) right.toggle(pressed);
		if (e.getKeyCode() == KeyEvent.VK_Y) yes.toggle(pressed);
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		// do nothing. I am doing it differently (tick)
	}

	@Override
	public void keyPressed(KeyEvent e) {
		toggle(e, true);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		toggle(e, false);
	}

}
