import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.*;

/**
 * Write a description of class TreasureWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TreasureWorld extends World
{
    int level = 0;

    GreenfootImage bg = null;
    public PlayerActor playeractor = null;

    boolean stopping = false;
    int counter = 0;
    
    public TreasureWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(640, 480, 1,false); 
        level = 0;
        createBackground();

        setPaintOrder(TreasureActor.class,Dragon.class,BumperBlock.class,PlayerActor.class,BulletFireActor.class,BulletActor.class,StartDoorActor.class,EndDoorActor.class,TreasureBackWall.class);

        prepare();
        //We are No Longer on the Bridge we move differently now.
        playeractor.onBridge = false;
        //Drop Default Treasure
        placeTreasures();
        placeDragon();
    }

    public TreasureWorld(int level)
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        this();  
        this.level = level;
        createBackground();
        playeractor.extrarun = 1 + level/3;
        if(playeractor.extrarun > 3)
        {
            playeractor.extrarun = 3;
        }
        //Remove Default Treasure and Drop Treasure Based on Level
        placeTreasures();
        placeDragon();
    }

    private void placeDragon()
    {
        removeObjects(getObjects(Dragon.class));
        Dragon dragon = null;
        switch(level)
        {
            case 0:
            case 1:
            dragon = new GreenDragon();

            dragon.speed = 3;
            dragon.bulletspeed = 5;
            dragon.firecounterreset = 60;
            if(0 != level)
            {
                dragon.speed = 4;
                dragon.bulletspeed = 6;
                dragon.firecounterreset = 50;
            }
            break;
            case 2:
            case 3:
            dragon = new BlueDragon();

            dragon.speed = 5;
            dragon.bulletspeed = 7;
            dragon.firecounterreset = 40;

            if(2 != level)
            {
                dragon.speed = 6;
                dragon.bulletspeed = 8;
                dragon.firecounterreset = 30;
            }
            break;
            case 4:
            case 5:
            dragon = new RedDragon();

            dragon.walkcountermax = 9;
            dragon.walkcounterdivisor = 3;

            dragon.speed = 7;
            dragon.bulletspeed = 9;
            dragon.firecounterreset = 25;

            if(4 != level)
            {
                dragon.speed = 8;
                dragon.bulletspeed = 10;
                dragon.firecounterreset = 20;
            }
            break;
            case 6:
            case 7:
            default:
            dragon = new OrangeDragon();

            dragon.walkcountermax = 9;
            dragon.walkcounterdivisor = 3;
            dragon.speed = 9;
            dragon.bulletspeed = 11;
            dragon.firecounterreset = 11;
            if(6 != level)
            {
                dragon.speed = 10;
                dragon.bulletspeed = 12;
                dragon.firecounterreset = 7;
            }
            if(level > 8)
            {
                dragon.speed = 12;
                dragon.bulletspeed = 13;
                dragon.firecounterreset = 5;
            }
        }

        addObject(dragon, 468, 448);
    }

    private void placeTreasures()
    {
        //TODO evaluate Level
        removeObjects(getObjects(TreasureActor.class));
        TreasureActor t = null;
        for (int i = 0; i < 20 ; i++)
        {
            int x,y;
            int glevel = level;
            if(glevel > 5)
            {
                glevel = 5;
            }
            int ti = Greenfoot.getRandomNumber(6) + glevel;

            switch(ti)
            {
                case 0:
                case 1:
                t = new TreasureActor();
                break;
                case 2:
                case 3:
                t = new LampActor();
                break;
                case 4:
                case 5:
                t = new GobletActor();
                break;
                case 6:
                case 7:
                default:
                t = new ChestActor();
                break;
            }

            if(Greenfoot.getRandomNumber(2) < 1)
            {
                x = Greenfoot.getRandomNumber(471) + 131;
                y = Greenfoot.getRandomNumber(170) + 120;
            }
            else
            {
                x = Greenfoot.getRandomNumber(451) + 40;
                y = Greenfoot.getRandomNumber(193) + 208;
            }

            addObject(t,x,y);

        }
    }

    private void createBackground()
    {
        bg = new GreenfootImage(640,480);
        Graphics2D g2 = bg.getAwtImage().createGraphics();
        switch(level)
        {
            case 0:
            g2.setColor(Color.BLACK);
            break;
            case 1:
            g2.setColor(new Color(5,5,116));
            break;
            case 2:
            g2.setColor(new Color(110,6,136));
            break;
            case 3:
            g2.setColor(new Color(10,169,115));
            break;
            case 4:
            g2.setColor(new Color(60,201,153));
            break;
            default:
            //sky blue
            g2.setColor(new Color(117,192,255));
            break;
        }
        g2.fillRect(0,0,640,480);
        setBackground(bg);

    }

    /**
     * Prepare the world for the start of the program. That is: create the initial
     * objects and add them to the world.
     */
    private void prepare()
    {
        StartDoorActor startdooractor = new StartDoorActor();
        addObject(startdooractor, 548, 345);
        playeractor = new PlayerActor();

        playeractor.onBridge = false;
        addObject(playeractor, 539, 356 /*530, 356*/);
        //removeObject(playeractor);

        Dragon dragon = new Dragon();
        addObject(dragon, 468, 448);

        //TreasureActor treasureactor = new TreasureActor();
        //addObject(treasureactor, 334, 294);

        TreasureBackWall treasurebackwall = new TreasureBackWall();
        addObject(treasurebackwall, 318, 244);

        EndDoorActor enddooractor = new EndDoorActor();
        addObject(enddooractor, 82, 159);

        WestWallBumper westwallbumper = new WestWallBumper();
        addObject(westwallbumper, 3, 290);

        EastWallBumper eastwallbumper = new EastWallBumper();
        addObject(eastwallbumper, 637, 290);

        ScoreBox scorebox = new ScoreBox();
        addObject(scorebox, 575, 26);

        LivesBox livesbox = new LivesBox();
        addObject(livesbox, 65, 26);
    }

    private void RandomBackground()
    {
        bg = new GreenfootImage(640,480);
        Graphics2D g2 = bg.getAwtImage().createGraphics();
        
        switch(Greenfoot.getRandomNumber(13))
        {
            default:
            case 0:
            g2.setColor(Color.black);
            break;
            case 1:
            g2.setColor(Color.blue);
            break;
            case 2:
            g2.setColor(Color.cyan);
            break;
            case 3:
            g2.setColor(Color.darkGray);
            break;
            case 4:
            g2.setColor(Color.gray);
            break;
            case 5:
            g2.setColor(Color.green);
            break;
            case 6:
            g2.setColor(Color.lightGray);
            break;
            case 7:
            g2.setColor(Color.magenta);
            break;
            case 8:
            g2.setColor(Color.orange);
            break;
            case 9:
            g2.setColor(Color.pink);
            break;
            case 10:
            g2.setColor(Color.red);
            break;
            case 11:
            g2.setColor(Color.white);
            break;
            case 12:
            g2.setColor(Color.yellow);
            break;
        }
        
        g2.fillRect(0,0,640,480);
        setBackground(bg);
    }
    
    @Override
    public void act()
    {
        if(Manager.PlacePlayerFlag())
        {
            if(Manager.CanPlaceNewPlayer())
            {
                playeractor = new PlayerActor();
                playeractor.onBridge = false;
                /*I put the new character back to the start*/
                addObject(playeractor, 539, 356);
            }
            else
            {
                stopping = true;
                counter = 80;
            }

           
        }
        
         if(stopping)
            {
                counter--;
                RandomBackground();
                if(counter < 0)
                {
                    //Switch Worlds
                    StartingWorld bw = new StartingWorld();
                    Greenfoot.setWorld((World)bw);
                }

            }
    }
}
