import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
 * Write a description of class FireBallGenerator here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class FireBallGenerator extends Actor
{
    static GreenfootImage image[];
    int counter = 0;
    long startTime = 0;
    int delay = 0;
    boolean needInit = true;
    int level = 0;

    //static boolean suppressFire = false;

    static
    {
        image = new GreenfootImage[5];
        image[0] = new GreenfootImage("WarningFlare1.png");
        image[1] = new GreenfootImage("WarningFlare2.png");
        image[2] = new GreenfootImage("WarningFlare3.png");
        image[3] = new GreenfootImage("WarningFlare4.png");
        image[4] = new GreenfootImage("WarningFlare5.png");

    }

    public FireBallGenerator()
    {
        needInit = true;
        delay = GetRandomDelay() + 300;
        startTime = System.currentTimeMillis();
        //always clear this flag when adding object
        //suppressFire = false;
        counter = 0;
    }

    public FireBallGenerator(int level)
    {
        this();
        this.level = level;
    }

    public void act() 
    {
        if(needInit)
        {
            setImage(image[0]);
            startTime = System.currentTimeMillis();
            needInit = false;
            return;
        }

        List <PlayerActor>pl1 = getObjectsInRange(1000,PlayerActor.class);
        if(!pl1.isEmpty())
        {
            boolean anyAlive = false;

            for(PlayerActor player : pl1)
            {
                if(player.alive())
                {
                    anyAlive = true;
                }
            }
            if(!anyAlive)
            {
                counter = 0;
                setImage(image[0]);
                startTime = System.currentTimeMillis();
                delay=GetRandomDelay();
                return;
            }
        }

        //Check to make sure player is not dead or dying

        long endTime = System.currentTimeMillis();

        if(0 == counter && (endTime - startTime) < delay)
        {
            return;
        }

        List<FireBallGenerator> c = getObjectsInRange(100,FireBallGenerator.class);
        if(!c.isEmpty())
        {
            for(FireBallGenerator fbg : c)
            {
                if(fbg == this)
                    continue;
                if(fbg.counter > 0)
                {
                    //wait if another cannon is flaring up
                    counter = 0;
                    setImage(image[0]);
                    startTime = System.currentTimeMillis();
                    delay=GetRandomDelay();
                    return;
                }
            }
        }

        List l = getObjectsInRange(100,BulletActor.class);
        if(!l.isEmpty())
        {
            counter = 0;
            setImage(image[0]);
            startTime = System.currentTimeMillis();
            delay=GetRandomDelay();
            return;
        }

        List p = getObjectsInRange(38,PlayerActor.class);
        if(!p.isEmpty())
        {
            counter = 0;
            setImage(image[0]);
            startTime = System.currentTimeMillis();
            delay=GetRandomDelay();
            return;
        }

        /*
        if(0 == counter && suppressFire)
        {
        //gets an extra delay for trying to be too fast
        delay += Greenfoot.getRandomNumber(200) + 100;
        startTime = System.currentTimeMillis();
        return;
        }
         */

        /*
        if(0 == counter)
        {
        suppressFire = true;
        }   

        if(3 == counter)
        {
        suppressFire = false;
        }
         */

        counter = (counter + 1) % 5;

        setImage(image[counter]);

        if(0 == counter)
        {
            //Spawn the Fireball
            //System.out.println("Fireball!");
            BulletActor b = new BulletActor();
            getWorld().addObject(b,getX(),getY());
            startTime = System.currentTimeMillis();
            delay=GetRandomDelay();
        }
    }

    private static int GetRandomDelay()
    {
        return Greenfoot.getRandomNumber(500) + 500;
    }
}
