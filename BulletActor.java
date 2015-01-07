import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
 * Write a description of class BulletActor here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BulletActor extends Actor
{

    public Actor owner = null;
    public int speed = 5;

    boolean canScore = true;

    public BulletActor()
    {
        super();
        speed = 5;
        setRotation(0);
        canScore = true;
    }

    public void explodeFireball()
    {
        int limit = Greenfoot.getRandomNumber(15) + 11;
        for(int i = 0 ; i < 25 ; i++)
        {

            BulletFireActor bfa = new BulletFireActor();
            bfa.counter = 0;
            getWorld().addObject(bfa,getX() + Greenfoot.getRandomNumber(7) - 3,
                getY()+ Greenfoot.getRandomNumber(7) - 3);

            bfa.setRotation(getRotation() + (Greenfoot.getRandomNumber(31) - 15) * 5);
            bfa.speed = Greenfoot.getRandomNumber(5) - 6;
            bfa.move(3);

        }
        getWorld().removeObject(this);
        return;
    }

    /**
     * Act - do whatever the BulletActor wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        int i;
        int x = getX();
        int y = getY();
        BulletFireActor bfa;

        bfa = new BulletFireActor();
        getWorld().addObject(bfa,getX() + Greenfoot.getRandomNumber(7) - 3,
            getY()+ Greenfoot.getRandomNumber(7) - 3);

        bfa.setRotation(getRotation() + (Greenfoot.getRandomNumber(91) - 45) * 2);
        bfa.move(-1);
        bfa.speed = 1;

        bfa = new BulletFireActor();
        getWorld().addObject(bfa,getX() + Greenfoot.getRandomNumber(5) - 2,
            getY()+ Greenfoot.getRandomNumber(5) - 2);

        bfa.setRotation(getRotation() + (Greenfoot.getRandomNumber(91) - 45) * 2);
        bfa.move(-1);
        bfa.speed = -1;

        move(speed);

        SafeZone sz = (SafeZone)getOneIntersectingObject(SafeZone.class);
        if(null != sz)
        {
            //move(-speed);
            explodeFireball();
            return;
        }

        PlayerActor pa = (PlayerActor) getOneIntersectingObject(PlayerActor.class);
        if(null != pa)
        {
            //we only have to worry about one direction
            if((pa.getX() - getX()) < 8)
            {
                if((getY() > pa.getY()) || !pa.ducking)
                {
                    pa.fryGuy();
                    explodeFireball();
                    return;
                }
            }

        }
        
        if(canScore)
        {
            List <PlayerActor>pl1 = getObjectsInRange(60,PlayerActor.class);
            for(PlayerActor pez : pl1)
            {
                if(!pez.onBridge)
                {
                    canScore = false;
                    break;
                }
                
                if(pez.alive() && !pez.isOnFire() && canScore && pez.onBridge)
                {
                    if(pez.getX() < getX())
                    {
                        Score.addScore(5);
                        canScore = false;
                    }
                }
            }
        }
        
        // Add your action code here.
        if(getX() < 0)
        {
            getWorld().removeObject(this);
            return;
        }

        if(getX() >= getWorld().getWidth())
        {
            getWorld().removeObject(this);
            return;
        }

        if(getY() < 10)
        {
            getWorld().removeObject(this);
            return;
        }

        if(getY() >= getWorld().getHeight())
        {
            getWorld().removeObject(this);
            return;
        }
    }    

}

