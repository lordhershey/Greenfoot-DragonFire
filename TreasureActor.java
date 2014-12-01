import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.lang.Math;

/**
 * Write a description of class TreasureActor here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TreasureActor extends Actor
{
    protected int pointValue = 10;
    
    public TreasureActor()
    {
        pointValue = 10;
    }
    
    public void act() 
    {
       PlayerActor pa = (PlayerActor) getOneIntersectingObject(PlayerActor.class);
        if(null != pa && /*!pa.isOnFire() &&*/ pa.alive())
        {
            //we only have to worry about one direction
            if(Math.abs(pa.getX() - getX()) < 28 && Math.abs(pa.getY()-getY()) < 28)
            {
                Score.addScore(pointValue);
                getWorld().removeObject(this);
         
            }
        }
    }    
}
