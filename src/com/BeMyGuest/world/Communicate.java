package com.BeMyGuest.world;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Communicate {
	private Image image;
	private String quote;
	private int x;
	private int y;
	private boolean visible;
	
	public Communicate(int width, int height, String quote) {
		try {
			image = ImageIO.read(new File("res/world/pic/util/textbox.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		//image = createImage(width, height, new Color(243,181,154,200));
		this.quote = quote;
		x = 30;
		y = 0;
	}
	public Communicate(int width, int height, int x, int y, String quote) {
		try {
			image = ImageIO.read(new File("res/world/pic/util/textbox.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		//image = createImage(width, height, new Color(233,171,154,150));
		this.quote = quote;
		this.x = x;
		this.y = y;
	}
	
	public Communicate(int width, int height,  String quote, Color bg) {
		try {
			image = ImageIO.read(new File("res/world/pic/util/textbox.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		//image = createImage(width, height, bg);
		
		this.quote = quote;
		x=100;
		y=100;
	}
	
	 private static BufferedImage createImage (int width, int height, Color color) {
		    BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR);
		    Graphics2D g = image.createGraphics();
		    g.setColor(color);
		    g.fillRect(0, 0, width, height);
		    g.dispose();
		    return image;
	 }
	 public Image getBackground() {
		 return image;
	 }
	 public String getQuote() {
		 return quote;
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
	 public void setVisible(boolean v){
		 visible = v;
	 }
	 public boolean getVisible(){
		 return visible;
	 }
	 
}
