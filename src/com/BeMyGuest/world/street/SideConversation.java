package com.BeMyGuest.world.street;

import java.awt.Image;

import com.BeMyGuest.world.Communicate;
/**
 * This class is the side conversation that comes out in the Street view
 * It is just a group of "Communicate" class that was used in the lounge 
 * @author heeyunkim
 *
 */
public class SideConversation  {
	private Communicate[] communications;
	private int talkIndex;
    private final int FRAME_SPEED = 300;

	public SideConversation(int width, int height, String[] quote) {
		communications = new Communicate[quote.length];
		talkIndex = 0;
		for (int i=0; i<communications.length; i++) {
			
			int maxLine = 0;
			for (String line: quote[i].split("\n"))
			{
				if (line.length()>maxLine) maxLine = line.length();
			}
			communications[i] = new Communicate(50+ 12*maxLine,40*quote[i].split("\n").length,quote[i]);
		}
	}
	
	public void randomizePosition(Communicate talk) {
		talk.setX((int) (Math.random()*1000));
		talk.setY((int)(Math.random()*400));
	}
	
	private int frame = 0;
    protected void talk(){
        frame ++;
        frame %= FRAME_SPEED;
        if (frame == 0){
        	talkIndex = ((talkIndex +1) % (communications.length));
            randomizePosition(communications[talkIndex]);
        }
    }

    public String getQuote() {
    	return communications[talkIndex].getQuote();
    }

    public Image getBackground() {
        return communications[talkIndex].getBackground();
    }
    
    public int getX() {
    	return communications[talkIndex].getX();
    }
    public int getY() {
    	return communications[talkIndex].getY();
    }


}
