import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class StartDoorActor here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class StartDoorActor extends Actor
{
    public static GreenfootImage image[];
    static
    {
        image = new GreenfootImage[2];
        image[0] = new GreenfootImage("DoorOpened.png");
        image[1] = new GreenfootImage("DoorClosed.png");        
    }

    boolean canReleasePlayer = true;
    boolean doorOpen = true;

    public void act() 
    {
        boolean LeftKey = Greenfoot.isKeyDown("a") || Greenfoot.isKeyDown("left");
        // Add your action code here.
        if(!doorOpen)
        {
            if(!LeftKey && !canReleasePlayer)
            {
                canReleasePlayer = true;
                return;
            }
            
            if(LeftKey && canReleasePlayer)
            {
                doorOpen = true;
                setImage(image[0]);
                TreasureWorld tw = (TreasureWorld)getWorld();
                tw.addObject(tw.playeractor,getX()-20,getY()+11);
            }
            return;
        }

        PlayerActor pa = (PlayerActor) getOneIntersectingObject(PlayerActor.class);
        if(null != pa && !pa.isNAN() /*&& !pa.isOnFire()*/)
        {
            //if(!pa.alive())
            //{
            //    getWorld().removeObject(pa);
            //    return;
            //}
            //we only have to worry about one direction
            if(Math.abs(pa.getX() - getX()) < 20 && Math.abs(pa.getY() - getY()) < 24)
            {
                //switch worlds
                TreasureWorld tw = (TreasureWorld)getWorld();
                doorOpen = false;
                tw.removeObject(tw.playeractor);
                setImage(image[1]);
                canReleasePlayer = false;
            }
        }
    }    
}
