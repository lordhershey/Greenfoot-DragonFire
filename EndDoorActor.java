import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
 * Write a description of class EndDoorActor here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class EndDoorActor extends Actor
{
    public static GreenfootImage image[];
    static
    {
        image = new GreenfootImage[3];
        image[1] = new GreenfootImage("DoorOpened.png");
        image[0] = new GreenfootImage("DoorClosedDark.png");
        image[2] = new GreenfootImage("DoorClosed.png");
    }

    boolean doorOpen = false;
    boolean switchWorlds = false;
    int countdown = 0;

    PlayerActor hold = null;
    
    public void act() 
    {
        if(switchWorlds)
        {
            if(countdown-- > 0)
            {
                return;
            }
            //switch worlds
            TreasureWorld tw = (TreasureWorld)getWorld();
            BridgeWorld bw = new BridgeWorld(++tw.level,hold.isOnFire());
            Greenfoot.setWorld((World)bw);
            return;
        }

        if(doorOpen)
        {
            //check to see if hte player is here.

            // Add your action code here.
            PlayerActor pa = (PlayerActor) getOneIntersectingObject(PlayerActor.class);
            if(null != pa)
            {
                //we only have to worry about one direction
                if(Math.abs(pa.getX() - getX()) < 24 && Math.abs(pa.getY() - getY()) < 20)
                {
                    //switch worlds
                    
                    switchWorlds = true;
                    countdown = 10;
                    setImage(image[2]);
                    hold = pa;
                    getWorld().removeObject(pa);
                }
            }

            return;
        }

        // Add your action code here.
        List<TreasureActor> t = getWorld().getObjects(TreasureActor.class);
        if (t.isEmpty())
        {
            doorOpen = true;
            setImage(image[1]);
        }
    }    
}
