package com.BeMyGuest.world.street;

import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
/**
 * Window / Frame of the street view
 * @author heeyunkim
 *
 */
public class StreetFrame extends JFrame{
    public StreetFrame() throws IOException{    

        add(new Street());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1600, 800);
        setLocationRelativeTo(null);
        setTitle("Be My Guest");
        setResizable(false);
        setVisible(true);
    }

    /*public static void main(String[] args) throws IOException {
        new Observatory();
    }*/
}