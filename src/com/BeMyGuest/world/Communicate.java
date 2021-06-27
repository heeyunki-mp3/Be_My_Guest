package com.BeMyGuest.world;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

public class Communicate {
	private Image image;
	private String quote;
	private int x;
	private int y;
	private boolean visible;
	
	public Communicate(int width, int height, String quote) {
		image = createImage(width, height, Color.orange);
		this.quote = quote;
		x = 0;
		y = 0;
	}
	public Communicate(int width, int height, int x, int y, String quote) {
		image = createImage(width, height, Color.orange);
		this.quote = quote;
		this.x = x;
		this.y = y;
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
    public void setVisible(boolean v){
        visible = v;
    }
    public boolean getVisible(){
        return visible;
    }
	 
}
