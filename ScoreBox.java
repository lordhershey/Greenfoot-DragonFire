import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.*;
import java.awt.image.*;

/**
 * Write a description of class ScoreBox here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ScoreBox extends Actor
{
    BufferedImage bi = null;
    Graphics2D g = null;
    int w = 0;
    int h = 0;
    private Font myFont = new Font("TimesRoman", Font.BOLD, 18);
    
    public ScoreBox()
    {
        GreenfootImage gi = getImage();
        bi = gi.getAwtImage();
        g = bi.createGraphics();
        w = bi.getWidth();
        h = bi.getHeight();
        g.setFont(myFont);
        g.setBackground(new Color(255,255,255,0));
    }
    
    public void act() 
    {
        g.clearRect(0,0,w,h);
        g.setColor(Color.YELLOW);
        String score = "Score : " + Score.getScore();
        g.drawString(score,0,18);
    }    
}
