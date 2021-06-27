package com.BeMyGuest.world.lounge;

import java.awt.Color;
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

import com.BeMyGuest.util.BGM;
import com.BeMyGuest.util.KeyHandler;
import com.BeMyGuest.world.Communicate;
import com.BeMyGuest.world.Map;
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

    private NPC[] npcList = {bartender, talker, dealer, drinker, staff};

    private BGM bgm;
    

    public Room() throws IOException{
    	backgroundImage = ImageIO.read(new File("res/world/pic/background/lounge.png"));
    	
    	place = "lounge";
        setBackgroundMusic();
        

        bgm = new BGM();
        bgm.play(this);
        

        setFocusable(true);
        setBackground(Color.BLACK);
        setDoubleBuffered(true);
        
        key = new KeyHandler(this);

        player = new Player(180, 40);

        bartender = new NPC(790,200, "bartender", 300, 100, "Hi I am bartender");
        talker = new NPC(15,250, "talker", 300, 50, "I am talker");
        dealer = new NPC(408, 171, "dealer", 500, 50, "I am dealer");
        drinker = new NPC(759,455, "drinker", 100, 50, "I am drinker");
        staff = new NPC(116,43, "staff", 400, 40, "I am staff");
        
        timer = new Timer(5, this);
        timer.start();
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
        
        if (player.getFollowers().size() > 0){
            for (int i=0; i<player.getFollowers().size(); i++){
                Follower f = player.getFollowers().get(i);
                g2d.drawImage(f.getImage(), f.getX(), f.getY(), this);
            }
        }
        if (drinker.getFollowers().size() > 0){
            for (int i=0; i<drinker.getFollowers().size(); i++){
                Follower f = drinker.getFollowers().get(i);
                g2d.drawImage(f.getImage(), f.getX(), f.getY(), this);
            }
        }
        
        for (int i=0; i<npcList.length; i++) {
        	NPC thisNPC = npcList[i];
        	Communicate alart =npcList[i].getVoice();
        	if (alart.getVisible()){
                g2d.drawImage(alart.getBackground(), alart.getX(), alart.getY(), this);
                g2d.drawString(alart.getQuote(), alart.getX(), alart.getY());
                
                if (thisNPC.isFirstNPCCall()){
                    bgm.stop(npcList[i]);
                    if(!thisNPC.isThemePlaying()) bgm.play(thisNPC);
                    thisNPC.setFirstNPCCall(false);
                }
                if (player.yesPressed()){
                    invokeAction(DEALER_ACTION);
                    player.setYes(false);
                }
            }else{
                if(thisNPC.isThemePlaying()){
                    bgm.stop(thisNPC);
                    bgm.play(this);
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
        for (int i=0; i<player.getFollowers().size();i++){
            player.getFollowers().get(i).move();
        }
        for (int i=0; i<drinker.getFollowers().size();i++){
            drinker.getFollowers().get(i).move();
        }
        player.keyClick(key);
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

    public void invokeAction(int which){

        if (which == DEALER_ACTION && !alreadyInvoked){
            System.out.println("deal!");
            System.out.println("dealer invoked " + alreadyInvoked);
            alreadyInvoked = true;
         /*   try {
                VoltropFlipGUI.setOld(new VoltropFlipGUI());                
            } catch (IOException e) {
                e.printStackTrace();
            } */
            player.playerBusy(true);  
        }else if (which == BARTENDER_ACTION && !alreadyInvoked){
            System.out.println("Wine!");
            System.out.println("bartneder invoked " + alreadyInvoked);
            player.addFollower(new Follower(player.getX()+10, player.getY(), 400, true, "wine"));
            alreadyInvoked = true;
            player.playerBusy(true);

        }else if (which == TALKER_ACTION && !alreadyInvoked){
            System.out.println("Talk!");
            System.out.println("talker invoked " + alreadyInvoked);
            alreadyInvoked = true;
            //new Test();
            player.playerBusy(true);
        }else if (which == DRINKER_ACTION && !alreadyInvoked){
            System.out.println("Drink!");
            System.out.println("drinker invoked " + alreadyInvoked);
            alreadyInvoked = true;
            drinkerTalk();
            player.playerBusy(true);
        }else if (which == STAFF_ACTION && !alreadyInvoked){
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
	

}
