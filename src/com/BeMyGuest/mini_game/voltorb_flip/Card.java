package com.BeMyGuest.mini_game.voltorb_flip;

import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.*;
import javax.imageio.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.ImageIO;

public class Card {
	private Image image;
	private int number;
	private boolean[] annotation;
	private boolean clicked;
	private JLabel guiPart;

	public Card(int n) throws IOException
	{
	    number = n;
	    clicked = false;
	    annotation = new boolean[4];
	    BufferedImage image = ImageIO.read(new File("res/mini_game/voltorb_flip/card/unclicked.png"));
	    guiPart = new JLabel(new ImageIcon(image));
	}
	public JLabel getGuiPart()
	{
		return guiPart;
	}

	public Image getImage() 
	{
		return image;
	}

	public int getNumber() 
	{
		return number;
	}

	public void setNumber(int number) 
	{
		this.number = number;
		setImage();
	}
	public boolean[] getAnnotation()
	{
		return annotation;
	}
	public void setAnnotation(boolean[] a)
	{
		annotation = a;
	}
	public boolean isClicked() 
	{
		return clicked;
	}
  /**
   * 
   * @param clicked
   * @return number (score for that card)
   * @throws IOException
   */
	public int setClicked(boolean clicked) throws IOException
	{
		this.clicked = clicked;
		BufferedImage imageFlipped = ImageIO.read(new File("res/mini_game/voltorb_flip/card/"+ number +".png"));

		guiPart = new JLabel(new ImageIcon(imageFlipped));
		System.out.println(number + "is clicked");
		return number;
	}

	private void setImage() 
	{
		image = (new ImageIcon("res/mini_game/voltorb_flip/card/" + number + ".png")).getImage();
	}

	public void reset() 
	{
		this.number = 1;
		this.clicked = false;
		setImage();
	}
}