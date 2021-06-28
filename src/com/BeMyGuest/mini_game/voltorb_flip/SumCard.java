package com.BeMyGuest.mini_game.voltorb_flip;

import java.awt.Image;
import javax.swing.ImageIcon;

import javax.swing.*;
import javax.imageio.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.ImageIO;


public class SumCard {

    private int voltorbs;
    private int sum;
    
    private JLabel cardLabel;

    private JPanel containerPanel;
    public SumCard(int voltorbs, int sum) throws IOException{
        this.voltorbs = voltorbs;
        this.sum = sum;   
        
        System.out.println("sum: "+ sum);

        BufferedImage cardImage = ImageIO.read(new File ("res/mini_game/voltorb_flip/ScoreCard/score"+ voltorbs + "V"+sum+"S.png"));
        
        cardLabel = new JLabel(new ImageIcon(cardImage));


    }
    /**
     * 
     * @return JPanel containing card backgronud, num volt image, num sum image
     */
    public JLabel getGuiPart()
    {
        return cardLabel;
    }

    public void reset() {
        this.voltorbs = 0;
        this.sum = 0;
    }

    public int getVoltorbs() {
        return this.voltorbs;
    }

    public int getSum() {
        return this.sum;
    }
}
