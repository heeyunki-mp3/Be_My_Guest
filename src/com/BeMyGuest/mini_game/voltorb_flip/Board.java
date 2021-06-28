package com.BeMyGuest.mini_game.voltorb_flip;

import javax.swing.JComponent;

import java.awt.Graphics;
import java.awt.Image;

import javax.lang.model.util.ElementScanner14;
import javax.swing.ImageIcon;
import java.awt.Dimension;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOError;
import java.io.IOException;
import java.util.Random;

import java.util.ArrayList;
import java.awt.image.*;
import java.io.*;
import javax.swing.*;
import javax.imageio.ImageIO;

import java.util.concurrent.TimeUnit;

public class Board extends JComponent {
    private Card[][] cards = new Card[5][5];
    private SumCard[][] sumCards = new SumCard[2][5];
    private int score;
    private static int overallScore;
    private JLabel communicationGUI;
    public Board () throws IOException
    {
        BufferedImage image = ImageIO.read(new File("res/mini_game/voltorb_flip/communication/WelcomeCommunication.png"));
        communicationGUI = new JLabel(new ImageIcon(image));
        score = 0;
        overallScore = 0;
       //creates cards
       for (int i = 0; i < cards.length; i++)
       {
           for (int j = 0; j < cards[i].length; j++) 
           {
               cards[i][j] = new Card(decideScore());
           }
       }

       // create sum cards
       int volt = 0;
       int scoreSum = 0;    
       for (int i = 0; i < sumCards.length; i++) 
       {
           for (int j = 0; j < sumCards[i].length; j++) 
           {
                volt =0;
                scoreSum =0;
                ArrayList tempList = getCorrespondingCardList(i,j);
                for (int a = 0; a < tempList.size(); a++) 
                {
                    if (tempList.get(a).equals(0)) 
                    {
                        volt++;
                    }
                    scoreSum += (int) tempList.get(a);
                }

                sumCards[i][j] = new SumCard(volt, scoreSum);
           }
       }
    }
    public JLabel getCommunicationGUI()
    {
        return communicationGUI;
    }
    private ArrayList<Integer> getCorrespondingCardList(int row, int col)
    {
        boolean happy = false;
        ArrayList<Integer> scoreList = new ArrayList<Integer>();
        if (row ==0)
        {
            for (int i =0; i<cards.length; i++)
            {
                happy = scoreList.add(cards[i][col].getNumber());
            }
        }
        else
        {
            for (int i =0; i<cards.length; i++)
            {
                happy = scoreList.add(cards[col][i].getNumber());
            }
        }
        System.out.println("Hi it is " + happy + " that I am happy");
        return scoreList;
    }
    private int decideScore()
    {
        double chances = Math.random()*10;
        if (chances < 4)
        {return 1;}
        else if (chances < 5)
        {return 2;}
        else if (chances < 6)
        {return 3;}
        else 
        {return 0;}
    }
    public Card[][] getCards()
    {
        return cards;
    }
    public SumCard[][] getSumCards()
    {
        return sumCards;
    }
    public static int getOverallScore()
    {
        return overallScore;
    }
    public String grabClicked(int x, int y) throws IOException
    {
        int outer = -1;
        int inner = -1;
        Card target = null;
        //x
        if (106<x && x<189)
        {
            inner = 0;
        }
        else if (200<x && x<285)
        {
            inner = 1;
        }
        else if (296<x && x<377)
        {
            inner = 2;
        }
        else if (390<x && x<466)
        {
            inner = 3;
        }
        else if (483<x && x<565)
        {
            inner = 4;
        }
        //y
        if (66<y && y<143)
        {
            outer = 0;
        }
        else if (182<y && y<261)
        {
            outer = 1;
        }
        else if (292<y && y<371)
        {
            outer = 2;
        }
        else if (411<y && y<490)
        {
            outer = 3;
        }
        else if (522<y && y<609)
        {
            outer = 4;
        }
        if(outer==-1 || inner ==-1)
        {
            System.out.println("x==-1 ||y==-1 is triggered");
        }
        else
        {
            target = cards[outer][inner];
            if(!target.isClicked())
            {
                int sc = target.setClicked(true);
                boolean falseIfOver = updateScore(sc);
                checkWin();
                if (!falseIfOver)
                    return "game over";
                if (checkWin())
                    return "game won";
            }
        }
        return "nothing"; 
    }
    public boolean updateScore(int s) throws IOException
    {
        if (s == 0)
        {
            gameOver();
            return false;
        }
        if (score == 0)
        {
            score += s;
        }
        else
        {
            score *= s;
        }
        return true;
    }
    public void gameOver() throws IOException
    {
        overallScore = 0;
        score = 0;
        BufferedImage go = ImageIO.read(new File("res/mini_game/voltorb_flip/communication/GameoverCommunication.png"));
        communicationGUI = new JLabel(new ImageIcon(go));

    }
    public boolean checkWin() throws IOException
    {
        int notYetFound = 0;
        for (Card[] cardList : cards)
        {
            for (Card card : cardList)
            {
                if (!card.isClicked() && card.getNumber() > 1)
                {
                    notYetFound += 1;
                }
            }
        }
        if (notYetFound == 0)
        {
            win();
            return true;
        }
        return false;
    }
    public void win() throws IOException
    {
        BufferedImage winI = ImageIO.read(new File("res/mini_game/voltorb_flip/communication/winCommunication.png"));
        communicationGUI = new JLabel(new ImageIcon(winI));
        //reveal();
    }
    public void reveal() throws IOException
    {
        for (Card[] cardList : cards)
        {
            for (Card card: cardList)
            {
                card.setClicked(true);
            }
        }
        BufferedImage go3 = ImageIO.read(new File("res/mini_game/voltorb_flip/communication/gameOverCommunication3.png"));
        communicationGUI = new JLabel(new ImageIcon(go3));
    }
    public void reset() throws IOException
    {
        overallScore += score;
        score = 0;
       //creates cards
       for (int i = 0; i < cards.length; i++)
       {
           for (int j = 0; j < cards[i].length; j++) 
           {
               cards[i][j] = new Card(decideScore());
           }
       }

       // create sum cards
       int volt = 0;
       int scoreSum = 0;    
       for (int i = 0; i < sumCards.length; i++) 
       {
           for (int j = 0; j < sumCards[i].length; j++) 
           {
                volt =0;
                scoreSum =0;
                ArrayList tempList = getCorrespondingCardList(i,j);
                
                for (int a = 0; a < tempList.size(); a++) 
                {
                    if (tempList.get(a).equals(0)) 
                    {
                        volt++;
                    }
                    scoreSum += (int) tempList.get(a);
                }

                sumCards[i][j] = new SumCard(volt, scoreSum);
           }
       }
        BufferedImage go = ImageIO.read(new File("res/mini_game/voltorb_flip/communication/WelcomeCommunication.png"));
        communicationGUI = new JLabel(new ImageIcon(go));
    }
    public void gameOver2() throws IOException
    {
        BufferedImage go2 = ImageIO.read(new File("res/mini_game/voltorb_flip/communication/GameoverCommunication2.png"));
        communicationGUI = new JLabel(new ImageIcon(go2));
    }
    public void gameOver3() throws IOException
    {
        BufferedImage go3 = ImageIO.read(new File("res/mini_game/voltorb_flip/communication/GameoverCommunication3.png"));
        communicationGUI = new JLabel(new ImageIcon(go3));
    }
    public int getScore()
    {
        return score;
    }
}