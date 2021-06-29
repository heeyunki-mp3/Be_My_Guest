package com.BeMyGuest.world.lounge;

import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

public class LoungeFrame extends JFrame{
    public LoungeFrame() throws IOException{    

        add(new Room());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(866,600);
        setLocationRelativeTo(null);
        setTitle("Be My Guest");
        setResizable(false);
        setVisible(true);
    }
 /*
    public static void main(String[] args) throws IOException {
        new LoungeFrame();
    }*/
}
