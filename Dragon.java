import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
 * Write a description of class Dragon here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Dragon extends Actor
{
    /**
     * Act - do whatever the Dragon wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */

    static GreenfootImage image[];
    static GreenfootImage imagefire[];

    public GreenfootImage use_image[];
    public GreenfootImage use_imagefire[];
    
    static
    {
        image = new GreenfootImage[6];
        image [0] = new GreenfootImage("DragonGrey1a.png");
        image [1] = new GreenfootImage("DragonGrey2a.png");
        image [2] = new GreenfootImage("DragonGrey3a.png");
        image [3] = new GreenfootImage("DragonGrey1a.png");
        image [4] = new GreenfootImage("DragonGrey2a.png");
        image [5] = new GreenfootImage("DragonGrey3a.png");
        image[3].mirrorHorizontally();
        image[4].mirrorHorizontally();
        image[5].mirrorHorizontally();

        imagefire = new GreenfootImage[6];
        imagefire [0] = new GreenfootImage("DragonGrey1b.png");
        imagefire [1] = new GreenfootImage("DragonGrey2b.png");
        imagefire [2] = new GreenfootImage("DragonGrey3b.png");
        imagefire [3] = new GreenfootImage("DragonGrey1b.png");
        imagefire [4] = new GreenfootImage("DragonGrey2b.png");
        imagefire [5] = new GreenfootImage("DragonGrey3b.png");
        imagefire[3].mirrorHorizontally();
        imagefire[4].mirrorHorizontally();
        imagefire[5].mirrorHorizontally();
    }

    public Dragon()
    {
        use_image = image;
        use_imagefire = imagefire;
    }
    
    /*2 states, wandering - just walk back and forth on the screen; hunting the dragon can
    fire and it will come after the player to kill them.*/    

    public static enum State {NAN,WANDERING,
        HUNTING}

    boolean goLeft = true;
    int walkcounter = 0;
    
    int headupcount = 0;
    
    public int firecounter = 100;
    public int speed = 2;
    public int firecounterreset = 200;
    public int walkcountermax = 12;
    public int walkcounterdivisor = 4;
    public int bulletspeed = 3;
    
    State playerState = State.NAN;
    public void act() 
    {
        int x = getX();

        if(playerState == State.NAN)
        {
            walkcounter = 0;
            goLeft = true;
            setImage(use_image[0]);
            playerState = State.WANDERING;
            return;
        }

        int base = 0;  

        firecounter--;

        if(firecounter < 0)
        {
            firecounter = 0;
        }

        if(!goLeft)
        {
            base = 3;
        }

        walkcounter = (walkcounter + 1) % walkcountermax;

        setImage(use_image[base + walkcounter/walkcounterdivisor]);

        if(headupcount > 0)
        {
            headupcount--;
            setImage(use_imagefire[base + walkcounter/walkcounterdivisor]);
        }

        if(goLeft)
        {
            x -= speed;
            if(x < 75)
            {
                x = 75;
                goLeft = false;
            }
        }
        else
        {
            x += speed;
            if(x > 565)
            {
                x = 565;
                goLeft = true;
            }
        }

        setLocation(x,getY());

        if(firecounter > 0)
        {
            return;
        }

        if(playerState == State.WANDERING)
        {
            //Check State Change
            PlayerActor p = getLivingPlayer();
            if(p != null)
            {
                playerState = State.HUNTING;
            }
            
            return;
        }

        if(playerState == State.HUNTING)
        {

            PlayerActor p = getLivingPlayer();

            if(p == null)
            {
                //Player is hiding or dead
                playerState = State.WANDERING;
                return;
            }


            if(goLeft)
            {

                if((p.getX() < (getX()-40)) && (p.getX() > (getX()-55)))
                {
                    setImage(use_imagefire[base + walkcounter/walkcounterdivisor]);
                    firecounter = firecounterreset;
                    playerState = State.WANDERING;
                    headupcount = 30;
                    int offset = 4;
                    BulletActor b = new BulletActor();
                    b.setRotation(-90);
                    getWorld().addObject(b,getX() - 45 +- offset,getY()+1);
                    b.speed = bulletspeed;

                    b = new BulletActor();
                    b.setRotation(-90);
                    getWorld().addObject(b,getX() - 49 - offset ,getY()+6);
                    b.speed = bulletspeed;

                    b = new BulletActor();
                    b.setRotation(-90);
                    getWorld().addObject(b,getX() - 53 - offset ,getY()+1);
                    b.speed = bulletspeed;

                    b = new BulletActor();
                    b.setRotation(-90);
                    getWorld().addObject(b,getX() - 49 - offset ,getY());
                    b.speed = bulletspeed;

                    return;
                }

                if(p.getX() > (getX()+80))
                {
                    //go right
                    goLeft = false;
                }
            }
            else
            {
                if((p.getX() > (getX()+40)) && (p.getX() < (getX()+55)))
                {
                    setImage(use_imagefire[base + walkcounter/walkcounterdivisor]);
                    firecounter = firecounterreset;
                    headupcount = 30;
                    playerState = State.WANDERING;

                    int offset = 4;
                    BulletActor b = new BulletActor();
                    b.setRotation(-90);
                    getWorld().addObject(b,getX() + 45 + offset,getY()+1);
                    b.speed = bulletspeed;

                    b = new BulletActor();
                    b.setRotation(-90);
                    getWorld().addObject(b,getX() + 49 + offset ,getY()+6);
                    b.speed = bulletspeed;

                    b = new BulletActor();
                    b.setRotation(-90);
                    getWorld().addObject(b,getX() + 53 + offset ,getY()+1);
                    b.speed = bulletspeed;

                    b = new BulletActor();
                    b.setRotation(-90);
                    getWorld().addObject(b,getX() + 49 + offset ,getY());
                    b.speed = bulletspeed;

                    return;
                }

                if(p.getX() < (getX()-80))
                {
                    //go right
                    goLeft = true;
                }
            }

        }

    }   

    private PlayerActor getLivingPlayer()
    {
        List<PlayerActor> pl = getWorld().getObjects(PlayerActor.class);
        if (!pl.isEmpty())
        {
            for(PlayerActor p : pl)
            {
                //ignore all burnt out corpses
                if(p.alive())
                {
                    return (p);
                }
            }
        }

        return null;
    }
}
