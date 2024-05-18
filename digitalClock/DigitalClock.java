package com.digitalClock;

import javax.swing.*;
import java.awt.*;
import java.util.Calendar;

public class DigitalClock extends JPanel implements Runnable {
    Thread thread;
    public DigitalClock(){
        thread = new Thread(this);
        thread.start();

    }

    String am_pm[] = {" am", " pm"};
    public void paint(Graphics graphics){

        int w = getWidth(), h = getHeight();
        int fontSize = 30*h/150;
        Font font = new Font("Digital-7",Font.BOLD+Font.ITALIC,fontSize);
        String time = getTimeString();
        FontMetrics fontMetrics = graphics.getFontMetrics(font);
        int textWidth = fontMetrics.stringWidth(time);
        graphics.setColor(Color.GRAY);
        graphics.fillRect(0,0,w,h);
        //  graphics.clearRect(0,0,w,h);
        graphics.setFont(font);
        graphics.setColor(Color.ORANGE);
        graphics.drawString(time,(w-textWidth)/2, h/2);


        // date
        String date = getDateString();
        Font dateFont = new Font("Digital-7",Font.BOLD+Font.ITALIC,38*fontSize/100);
        fontMetrics = graphics.getFontMetrics(dateFont);
        graphics.setFont(dateFont);
        graphics.setColor(Color.WHITE);
        graphics.drawString(date,(w-textWidth)/2, h/2+fontMetrics.getHeight());


    }

    String getTimeString(){
        // Setup Calender
        Calendar calendar = Calendar.getInstance();
        String time = "";
        int amPm = calendar.get(Calendar.AM_PM);
        int hour = calendar.get(Calendar.HOUR);
        time += hour+":";

        int minute = calendar.get(Calendar.MINUTE);
        if ( minute < 10 )time += "0";
        time += minute+":";

        int second = calendar.get(Calendar.SECOND);
        if ( second < 10 ) time += "0";
        time += second;

        time +=am_pm[amPm]; // for am and pm
        return time;
    }


    String[] days = {"","Sunday","Monday","Tuesday","Wednesday","thursday","Friday","Saturday"};
    String[] months = {"January","February","Mach","April","May","June","July","August","September","October","November","December"};
    String getDateString(){
        String date = "";
        Calendar calendar = Calendar.getInstance();
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        date += days[dayOfWeek]+" ";

        // date of week
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        date +=day+", ";

        // Month
        int month = calendar.get(Calendar.MONTH);
        date +=months[month]+" ";

        //Year
        date +=calendar.get(Calendar.YEAR);
        return date;
    }
    int fps = 1;
    @Override
    public void run() {
        while (true){
            repaint();
            try{
                Thread.sleep(1000/fps);// We need to update every second
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }

    }
}
