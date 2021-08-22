package com.BeMyGuest.world.street;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.io.IOException;

import com.BeMyGuest.util.KeyHandler;
import com.BeMyGuest.world.Map;
import com.BeMyGuest.world.street.bigCharacter.BigPlayer;
import javax.swing.Timer;

/**
 * The JPanel that contains the street background, characters, and conversation
 * @author heeyunkim
 *
 */
public class Street extends Map{
	private BigPlayer player;
    private View windowView;
    private SideConversation talks;

    public Street() throws IOException{

        setFocusable(true);
        setBackground(Color.BLACK);
        setDoubleBuffered(true);
        
        key = new KeyHandler(this);

        windowView = new View(0,0,"world/pic/background/night_street.png"); // TODO: corret 

        String[] sideConvo= {"Old Man: Hey thank you for getting me the wine!","A Girl: Mom! Can you take a picture of me?","A Homeless: Any changes? Please sir...","A High Schooler: I failed my Calculus test...","Heeyun: Did you have fun? \nIt took me 80 hours to code this.","A Couple: You are more beautiful than this night view!","A Guy: Nice view!"};
        talks = new SideConversation(0,0,sideConvo);
        player = new BigPlayer(500, 400, "user", 40, 2);

        timer = new Timer(5, this);
        timer.start();
    }
    
    public void paint(Graphics g){
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;

        g2d.drawImage(windowView.getImage(), windowView.getX(), windowView.getY(), this);
        g2d.drawImage(player.getImage(), player.getX(), player.getY(), this);
        
        //draw the background for the side conversation
        g2d.drawImage(talks.getBackground(), talks.getX(), talks.getY(), this);
        //draw the side conversation
		int lineHeight = g.getFontMetrics().getHeight();
		int fontSize = 25;
		g2d.setFont(new Font("Helvetica", Font.BOLD, fontSize)); 
		g2d.setColor(new Color(233,171,154));
		int line = 0;
        for (String quote : talks.getQuote().split("\n")) {
            g2d.drawString(quote, talks.getX()+40, talks.getY()+25+((int)(fontSize*line*1.4)));
        	line ++;
        }
        
        Toolkit.getDefaultToolkit().sync();
        g.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {  
        player.keyClick(key);
        windowView.keyClick(key);
        talks.talk();

        repaint();
    }
}
