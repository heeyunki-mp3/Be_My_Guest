package com.BeMyGuest.mini_game.voltorb_flip;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import java.awt.event.*;
import java.util.*;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;
import javax.swing.border.*;



public class VoltorbFlipGUI extends JFrame 
{
    private static Board game;
    //trying to make static variable
    static {
        try {
            game = new Board();
        }
        catch (IOException e) {
            throw new RuntimeException("Class initialization failed due to UnknownHostException", e);
        }
    }
    private JLabel communication;
    private JPanel commuPanel = new JPanel();
    private static JPanel[] boardRowPanel = new JPanel[5];
    private JPanel scorePanel = new JPanel();
    private JPanel gamePanel = new JPanel();

    private Card[][] gameCard = game.getCards();
    private SumCard[][] scoreCard = game.getSumCards();

    private JLabel totalCoin = new JLabel();
    private JLabel coin = new JLabel();
    private JPanel container = new JPanel();
    private static VoltorbFlipGUI old;
    private JFrame frame = new JFrame("Voltorb Flip!!!");
    private JLabel newGameLabel = new JLabel();
    private JLabel showCardsLabel = new JLabel();
    static boolean gameGo = true;
    static boolean won = false;
    
    public VoltorbFlipGUI () throws IOException
    {
        communication = game.getCommunicationGUI();
        for (int i=0; i< gameCard.length; i++)
        {
            boardRowPanel[i] = new JPanel();
            for (int j=0; j<gameCard.length; j++)
            {
                boardRowPanel[i].add(gameCard[i][j].getGuiPart());
            }
            boardRowPanel[i].add(scoreCard[1][i].getGuiPart()); //getGuiPart should return JPanel
        }
        totalCoin.setText("  Total Coin:  " + Board.getOverallScore());
        coin.setText("  Coin:  " + game.getScore());

        totalCoin.setFont(new Font("Gill Sans", Font.BOLD, 30));
        totalCoin.setForeground(Color.WHITE);
        coin.setFont(new Font("Gill Sans", Font.BOLD, 30));
        coin.setForeground(Color.WHITE);

        scorePanel.add(totalCoin);
        scorePanel.add(coin);
        scorePanel.setBackground(new Color(60,125,99));

        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        container.setAlignmentX(Component.LEFT_ALIGNMENT);
        container.setBackground(new Color(80,158,108));
        container.add(scorePanel);

        commuPanel.add(communication);
        commuPanel.setBackground(new Color(60,125,99));
        //commuPanel.setAlignmentX(Component.RIGHT_ALIGNMENT);

        BufferedImage newGameImage = ImageIO.read(new File ("res/mini_game/voltorb_flip/util/NewGameButton.png"));
        newGameLabel = new JLabel(new ImageIcon(newGameImage));
        commuPanel.add(newGameLabel);

        BufferedImage showCardsImage = ImageIO.read(new File ("res/mini_game/voltorb_flip/util/showCardsButton.png"));
        showCardsLabel = new JLabel(new ImageIcon(showCardsImage));
        commuPanel.add(showCardsLabel);

        if (gameGo)
        {
            showCardsLabel.setVisible(false);
        }
        else
        {
            showCardsLabel.setVisible(true);
        }
        if (won)
        {
            showCardsLabel.setVisible(true);
        }

        JFrame.setDefaultLookAndFeelDecorated(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        for (JPanel row: boardRowPanel)
        {
            row.setBorder(new EmptyBorder(0, 100, 0, 0));
            row.setAlignmentX(Component.RIGHT_ALIGNMENT);
            row.setBackground(new Color(80,158,108));
            container.add(row); 
        }

        JPanel bottomSumCardPanel = new JPanel();
        bottomSumCardPanel.setBackground(new Color(80,158,108));
        bottomSumCardPanel.setAlignmentX(Component.RIGHT_ALIGNMENT);
         
        for (SumCard card: scoreCard[0])
        {
            bottomSumCardPanel.add(card.getGuiPart());
            card.getGuiPart().setBackground(new Color(80,158,108));
        }
        container.addMouseListener(new FlipClickListener());

        container.add(bottomSumCardPanel);
        container.add(commuPanel);
        frame.add(container);

        frame.pack();
        frame.setResizable(false);
        frame.setVisible(true);
    }
    public static void setOld(VoltorbFlipGUI v){
        old = v;
    }
    /*
    public static void main(String[] args) throws IOException {
       old = new VoltorbFlipGUI();
       new SoundGUI();
       new Cheese();
    }*/
    public static void updateGUI() throws IOException, InterruptedException
    {
        VoltorbFlipGUI hold = new VoltorbFlipGUI();
        old.frame.dispose();
        old = hold;
    }
   
    public static void gameOverGUI() throws IOException, InterruptedException
    {
        game.gameOver();
        updateGUI();
        
        //game.gameOver2();
        //updateGUI();
        /*
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        BufferedImage comgo3 = ImageIO.read(new File("images/GameOverCommunication3.png"));
        communication = new JLabel(new ImageIcon(comgo3));
        new VoltorbFlipGUI();*/
    }
    
    public static void gameOverGUI2() throws IOException, InterruptedException
    {
        Thread.sleep(3 * 1000);
        System.out.println("waiting done");
        game.gameOver2();
        updateGUI();
    }
    private static class FlipClickListener implements MouseListener {
        @Override
        public void mouseClicked(java.awt.event.MouseEvent evt){
            System.out.println("x:" + evt.getX());
            System.out.println("y:" + evt.getY());
            boolean newGameClicked = false;
            boolean showCardsClicked = false;
            if (gameGo)
            {
                newGameClicked = (627<evt.getX()&&evt.getX()<755&&781<evt.getY()&&evt.getY()<851);
            }
            else 
            {
                newGameClicked = (586<evt.getX()&&evt.getX()<717&&781<evt.getY()&&evt.getY()<851);
                showCardsClicked = (726<evt.getX()&&evt.getX()<854&&781<evt.getY()&&evt.getY()<851);
            }
            if (newGameClicked)
            {
                try {
                    game.reset();
                    gameGo = true;
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    updateGUI();
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if (showCardsClicked)
            {
                try {
                    game.reveal();
                    updateGUI();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if (gameGo && !won)
            {
                try
                {

                   // gameGo = game.grabClicked(evt.getX(), evt.getY());
                    if (game.grabClicked(evt.getX(), evt.getY()) == "game over")
                    {
                        gameGo = false;
                        gameOverGUI();
                        
                    }
                    else if (game.grabClicked(evt.getX(), evt.getY()) == "game won")
                    {
                        won = true;
                        updateGUI();
                    }
                    else
                    {
                        updateGUI();
                    }
                }
                catch (IOException | InterruptedException e)
                {
                    System.out.println("IOException at grabClicked in gui program");
                }
            }
            else
            {
                System.out.println("hey game is over!");
            }
        }
        
        @Override
        public void mousePressed(java.awt.event.MouseEvent e) {}

        @Override
        public void mouseReleased(java.awt.event.MouseEvent e) {}

        @Override
        public void mouseEntered(java.awt.event.MouseEvent e) {}

        @Override
        public void mouseExited(java.awt.event.MouseEvent e) {}      
    } 
}