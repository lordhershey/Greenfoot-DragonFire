import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class WaterExteriorOver here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WaterExteriorOver extends Actor
{
    /**
     * Act - do whatever the WaterExteriorOver wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    
    long currentTime = 0;
    
    boolean up = true;
    
    int limit = Greenfoot.getRandomNumber(400) + 700;
    
    public void act() 
    {
        // Add your action code here.
        long newTime = System.currentTimeMillis();
        
        if((newTime - currentTime) < limit)
        {
            return;
        }
        
        currentTime = newTime;
        
        limit = Greenfoot.getRandomNumber(400) + 700;
        
        if(up)
        {
            up = false;
            setLocation(getX(),getY()+1);
        }
        else
        {
            up = true;
            setLocation(getX(),getY()-1);
        }
    }    
}
