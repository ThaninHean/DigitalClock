package com.digitalClock;

import javax.swing.*;

public class Main extends JFrame {
    public Main(){
        DigitalClock obj = new DigitalClock();
        add(obj);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(300,100,600,350);
        setAlwaysOnTop(true);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Main();
    }
}
