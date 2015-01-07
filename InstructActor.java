import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class InstructActor here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class InstructActor extends Actor
{
    static GreenfootImage image[];
    
    static
    {
        image = new GreenfootImage[6];
        image[0] = new GreenfootImage("BridgeRun1.png");
        image[1] = new GreenfootImage("BridgeRun2.png");
        image[2] = new GreenfootImage("BridgeRun3.png");
        image[3] = new GreenfootImage("BridgeRun4.png");
        image[4] = new GreenfootImage("BridgeRun5.png");
        image[5] = new GreenfootImage("BridgeRun6.png");
    }
    
    long startTime = System.currentTimeMillis();
    int counter = 0;
    
    public void act() 
    {
        long endTime = System.currentTimeMillis();
        
        if((endTime - startTime) < 1500)
        {
            return;
        }
        
        counter = (counter + 1) % 6;
        setImage(image[counter]);
        startTime = System.currentTimeMillis();
    }    
}
