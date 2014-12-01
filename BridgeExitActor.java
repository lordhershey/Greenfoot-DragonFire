import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class BridgeExitActor here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BridgeExitActor extends Actor
{
    /**
     * Act - do whatever the BridgeExitActor wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // Add your action code here.
        PlayerActor pa = (PlayerActor) getOneIntersectingObject(PlayerActor.class);
        if(null != pa)
        {
            //we only have to worry about one direction
            if((pa.getX() - getX()) < 10)
            {
                //switch worlds
                BridgeWorld bw = (BridgeWorld)getWorld();
                TreasureWorld tw = new TreasureWorld(bw.level);
                Greenfoot.setWorld((World)tw);
            }
        }
    }    
}
