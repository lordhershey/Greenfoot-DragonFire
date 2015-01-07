import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
import java.awt.*;
import java.awt.image.*;

/**
 * Write a description of class HighScoreActor here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class HighScoreActor extends Actor
{
    BufferedImage bi = null;
    Graphics2D g = null;
    int w = 0;
    int h = 0;
    private Font myFont = new Font("TimesRoman", Font.BOLD, 18);

    public HighScoreActor()
    {
        GreenfootImage gi = getImage();
        bi = gi.getAwtImage();
        g = bi.createGraphics();
        w = bi.getWidth();
        h = bi.getHeight();
        g.setFont(myFont);
        g.setBackground(new Color(255,255,255,0));

        g.clearRect(0,0,w,h);
        g.setColor(Color.YELLOW);

        
        if(UserInfo.isStorageAvailable())
        {
            int y = 18;
            int advance = 24;
            java.util.List<UserInfo> list =  UserInfo.getTop(10);

            for(UserInfo user : list)
            {
                String stuff = user.getUserName() + " " + user.getScore();
                g.drawString(stuff,0,y);
                y += advance;
            }
        }
        //g.drawString(score,0,18);
    }

    public void act() 
    {
        // Add your action code here.
    }    
}
