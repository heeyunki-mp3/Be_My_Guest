package com.BeMyGuest.world.lounge;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.awt.Image;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioSystem;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.Timer;

import com.BeMyGuest.mini_game.are_you_ok.MentalTest;
import com.BeMyGuest.mini_game.voltorb_flip.VoltorbFlipGUI;
import com.BeMyGuest.util.BGM;
import com.BeMyGuest.util.KeyHandler;
import com.BeMyGuest.world.Communicate;
import com.BeMyGuest.world.Map;

import com.BeMyGuest.world.lounge.character.Character;

import com.BeMyGuest.world.lounge.character.Follower;
import com.BeMyGuest.world.lounge.character.NPC;
import com.BeMyGuest.world.lounge.character.Player;

public class Room extends Map{

    private Player player;
    
    private NPC bartender;
    private NPC talker;
    private NPC dealer;
    private NPC drinker;
    private NPC staff;

    private Image backgroundImage;

    private final int DEALER_ACTION = 0;
    private final int BARTENDER_ACTION = 1;
    private final int TALKER_ACTION = 2;
    private final int DRINKER_ACTION = 3;
    private final int STAFF_ACTION = 4;

    private NPC[] npcList = new NPC[5];

    private BGM bgm;
    
    //for checking obstacles in front
    private boolean inTable = false;
    private boolean inBar = false;
    private boolean inUpperWall = false;
    private boolean inCouchSection = false;
    private boolean inMiddleWall = false;
    private boolean inShelf = false;
    private boolean inDealer = false;
    private boolean inStaff = false;
    

    public Room() throws IOException{
    	bgm = new BGM();
    	System.out.println(new File("res/world/pic/background/lounge.png").getAbsolutePath());
    	backgroundImage = ImageIO.read(new File("res/world/pic/background/lounge.png"));
    	 
    	System.out.println(backgroundImage);
    	place = "lounge";
        
    	this.playTheme(bgm);
        setFocusable(true);
        setBackground(Color.BLACK);
        setDoubleBuffered(true);
        
        key = new KeyHandler(this);

        player = new Player(180, 40, this);

        bartender = new NPC(790,200, "bartender", 300, 100, false, "Bartender: Eh... This is totally not a wine... Mhm! \n press Y to get wine", this);
        talker = new NPC(15,250, "talker", 300, 50, false, "A girl: I am bored! Let's Chat! \n Press Y if you want to chat", this);
        dealer = new NPC(408, 171, "dealer", 500, 50, false, "Dealer: Hey! Do you wanna play Voltorb Flip? \n Press Y to play", this);
        drinker = new NPC(759,455, "drinker", 50, 50, true, "A man: I want... wine... But I can't find a bar. \n Press Y to hand a wine", this);
        staff = new NPC(116,43, "staff", 400, 40, false, "Staff: Thank you for coming tonight! \n Press Y if you want to exit", this);
        
        npcList[0]=bartender;
        npcList[1]=talker;
        npcList[2]=dealer;
        npcList[3]=drinker;
        npcList[4]=staff;
        
        
        
        timer = new Timer(5, this);
        timer.start();
    }
    
    public void playTheme(BGM player) {
		themePlaying = player.play("world/music/"+place+".wav");
	}
	public void stopTheme(BGM player) {
		player.stop();
		themePlaying = false;
	}
   
    public void paint(Graphics g){
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(backgroundImage, 0,0, this);

        g2d.drawImage(bartender.getImage(), bartender.getX(), bartender.getY(), this);
        g2d.drawImage(talker.getImage(), talker.getX(), talker.getY(), this);
        g2d.drawImage(dealer.getImage(), dealer.getX(), dealer.getY(), this);
        g2d.drawImage(drinker.getImage(), drinker.getX(), drinker.getY(),this);
        g2d.drawImage(staff.getImage(), staff.getX(), staff.getY(),this);

        g2d.drawImage(player.getImage(), player.getX(), player.getY(), this);
        
        checkObstacles(player);
        checkObstacles(drinker);
        
        //draw followers if there are
        if (player.getFollowers().size() > 0){
        	// if it is facing down, the last follower needs to be drawn first appear to be at the back
        	if (player.getImageDirection()==player.DOWN)
        	{
        		for (int i=player.getFollowers().size()-1; i>=0; i--){
                    Follower f = player.getFollowers().get(i);
                    g2d.drawImage(f.getImage(), f.getX(), f.getY(), this);
                }
        	}
        	else {
	            for (int i=0; i<player.getFollowers().size(); i++){
	                Follower f = player.getFollowers().get(i);
	                g2d.drawImage(f.getImage(), f.getX(), f.getY(), this);
	            }
        	}
        }
        if (drinker.getFollowers().size() > 0){
        	// if it is facing down, the last follower needs to be drawn first appear to be at the back
        	if (drinker.getImageDirection()==drinker.DOWN){
        		for (int i=drinker.getFollowers().size()-1; i>=0; i--){
                    Follower f = drinker.getFollowers().get(i);
                    g2d.drawImage(f.getImage(), f.getX(), f.getY(), this);
                }
        	}
        	else{
	            for (int i=0; i<drinker.getFollowers().size(); i++){
	                Follower f = drinker.getFollowers().get(i);
	                g2d.drawImage(f.getImage(), f.getX(), f.getY(), this);
	            }
        	}
        }
        //draw alarts
        for (int i=0; i<npcList.length; i++) {
        	NPC thisNPC = npcList[i];
        	Communicate alart =npcList[i].getVoice();
        	if (player.close(thisNPC)){
        		//draw the communication
        		//draw the background box of the alart
                g2d.drawImage(alart.getBackground(), alart.getX(), alart.getY(), this);
                //draw the content (words) of the npc
        		int lineHeight = g.getFontMetrics().getHeight();
        		int fontSize = 25;
        		g2d.setFont(new Font("Helvetica", Font.BOLD, fontSize)); 
        		g2d.setColor(new Color(60,111,129));
        		int line = 1;
                for (String quote : alart.getQuote().split("\n")) {
                    g2d.drawString(quote,  alart.getX()+10, alart.getY()+(fontSize*line));
                	line ++;
            		g2d.setFont(new Font("Helvetica", Font.PLAIN, fontSize -5)); 
                }
                
                if (thisNPC.isFirstNPCCall()){
                	this.stopTheme(bgm);
                    if(!thisNPC.isThemePlaying()) thisNPC.playTheme(bgm);
                    thisNPC.setFirstNPCCall(false);
                }
                if (player.yesPressed()){
                    invokeAction(thisNPC.getName()); //TODO
                    player.setYes(false);
                }
                break;
            }else{
                if(thisNPC.isThemePlaying()){
                	thisNPC.stopTheme(bgm);
                    this.playTheme(bgm);
                    thisNPC.setThemePlaying(false);
                    alreadyInvoked = false;
                    player.playerBusy(false);
                }
                thisNPC.setFirstNPCCall(true);
            }
        }
        Toolkit.getDefaultToolkit().sync();
        g.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {  
        checkEvent();
        //moving followers
        for (int i=0; i<player.getFollowers().size();i++){
            player.getFollowers().get(i).move();
        }
        for (int i=0; i<drinker.getFollowers().size();i++){
            drinker.getFollowers().get(i).move();
        }
        //moving user
        player.keyClick(key);
        //moving npc
        bartender.move();
        dealer.move();
        talker.move();
        drinker.move(); 
        staff.move();
        repaint();
    }

    public void checkEvent(){
    	for (int i=0; i<npcList.length; i++) {
    		npcList[i].getVoice().setVisible(player.close(npcList[i]));
    	}
    }
    
    private boolean alreadyInvoked = false;

    public void invokeAction(String which){

        if (which == "dealer" && !alreadyInvoked){
            System.out.println("deal!");
            System.out.println("dealer invoked " + alreadyInvoked);
            alreadyInvoked = true;
            try {
                VoltorbFlipGUI.setOld(new VoltorbFlipGUI());                
            } catch (IOException e) {
                e.printStackTrace();
            } 
            player.playerBusy(true);  
        }else if (which == "bartender" && !alreadyInvoked){
            System.out.println("Wine!");
            System.out.println("bartneder invoked " + alreadyInvoked);
            player.addFollower(new Follower(player.getX()+10, player.getY(), 400, true, "wine", this, 6));
            alreadyInvoked = true;
            player.playerBusy(true);

        }else if (which == "talker" && !alreadyInvoked){
            System.out.println("Talk!");
            System.out.println("talker invoked " + alreadyInvoked);
            alreadyInvoked = true;
            new MentalTest();
            player.playerBusy(true);
        }else if (which == "drinker" && !alreadyInvoked){
            System.out.println("Drink!");
            System.out.println("drinker invoked " + alreadyInvoked);
            alreadyInvoked = true;
            drinkerTalk();
            player.playerBusy(true);
        }else if (which == "staff" && !alreadyInvoked){
            System.out.println("Exit!");
            System.out.println("exit invoked " + alreadyInvoked);
            alreadyInvoked = true;
            /*try {
                new Observatory();
            } catch (IOException e) {
                e.printStackTrace();
            }*/
            player.playerBusy(true);
        }
    }

    public void drinkerTalk(){
        drinker.addFollower(player.getFollowers().remove(player.getFollowers().size()-1));
        for (int i=0; i<drinker.getFollowers().size(); i++)
        {
            drinker.getFollowers().get(i).setY(drinker.getY()+10);
            drinker.getFollowers().get(i).setX(drinker.getX()+i*20);
        }
    }
    
    public void checkObstacles(Character who) {
    	int y = who.getY();
    	int x = who.getX();
    
    	if (y<276 &&x>700) inBar = true; 
        else inBar = false; 
        if ((409<x && x< 567)&&(35<y&&y<204)) inTable = true;
        else inTable = false;
        if (x>370 && (324<y && y<444)) inMiddleWall = true;
        else inMiddleWall = false;
        if (y<32) inUpperWall = true;
        else inUpperWall =false;
        if (((0<x&&x<50)&&(114<y&&y<336))|| ((54<x&&x<216)&&(114<y&&y<208))) inCouchSection = true;
        else inCouchSection =false;
        if ((366<x&&x<433)&&(103<y&&y<204)) inDealer = true;
        else inDealer = false;
        if ((422<x&&x<803)&&(440<y&&y<475)) inShelf = true;
        else inShelf = false;
        if(x<152 && y<52) inStaff = true;
        else inStaff = false;
        
        if(inBar || inTable || inMiddleWall || inUpperWall || inCouchSection || inDealer || inShelf || inStaff) {
        	who.setCanUp(false);
        }else{
        	who.setCanUp(true);
        }
        if (inMiddleWall || inCouchSection || inTable || y>520 || inDealer) {
            who.setCanDown(false);
        }else{
        	who.setCanDown(true);
        }
        if (inCouchSection || inTable || x<0 || inDealer||inShelf || inStaff){
            who.setCanLeft(false);
        }else{
            who.setCanLeft(true);
        }
        if(inBar || inTable || inMiddleWall || x>842 || inDealer||inShelf){
            who.setCanRight(false);
        }else{
            who.setCanRight(true);
        }
    }
}
