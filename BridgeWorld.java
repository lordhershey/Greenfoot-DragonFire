import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.image.*;
import java.awt.*;

/**
 * Write a description of class BridgeWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BridgeWorld extends World
{

    public int level = 0;

    GreenfootImage bg = null;

    PlayerActor playeractor = null;

    int counter = 0;
    boolean stopping = false;

    /**
     * Constructor for objects of class BridgeWorld.
     * 
     */
    public BridgeWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(640, 480, 1); 

        prepare();

        level = 0;

        /*Note CastleExterior.classs should be the last in the forground display, 
         * player class goes before water, The only thing that can come before the 
         * CastleExteriorTopOverlay would be an archers arrow.*/
        //setPaintOrder(BridgeExitActor.class,BumperBlock.class,SafeZone.class,CastleExteriorTopOverlay.class,BulletFireActor.class,BulletActor.class,FireBallGenerator.class,WaterExteriorOver.class,PlayerActor.class,CastleExterior.class);
        setPaintOrder(CastleExteriorTopOverlay.class,BulletFireActor.class,BulletActor.class,FireBallGenerator.class,WaterExteriorOver.class,PlayerActor.class,CastleExterior.class,BridgeExitActor.class,BumperBlock.class,SafeZone.class);

        createBackground();
    }

    public BridgeWorld(int level,boolean launchPlayer)
    {
        this();
        this.level = level;
        createBackground();

        if(launchPlayer)
        {
            playeractor.comicFireLaunch();
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
            g2.setColor(new Color(5,5,22));
            break;
            case 2:
            g2.setColor(new Color(40,6,66));
            break;
            case 3:
            //g2.setColor(new Color(10,89,55));
            g2.setColor(new Color(50,16,76));
            break;
            case 4:
            g2.setColor(new Color(70,36,96));
            //g2.setColor(new Color(60,126,103));
            break;
            default:
            //sky blue
            g2.setColor(new Color(117,192,255));
            break;
        }
        g2.fillRect(0,0,640,480);
        setBackground(bg);
        g2.setColor(Color.WHITE);
        if(level > 1)
        {
            g2.setColor(Color.GRAY);
        }
        if(level > 4)
        {
            g2.setColor(Color.MAGENTA);
        }
        g2.fillRect(0,442,640,38);
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
    
    /**
     * Prepare the world for the start of the program. That is: create the initial
     * objects and add them to the world.
     */
    private void prepare()
    {
        CastleExterior castleexterior = new CastleExterior();
        addObject(castleexterior, 320, 240);

        WaterExteriorOver waterexteriorover = new WaterExteriorOver();
        addObject(waterexteriorover, 320, 240);
        CastleExteriorTopOverlay castleexteriortopoverlay = new CastleExteriorTopOverlay();
        addObject(castleexteriortopoverlay, 320, 240);
        playeractor = new PlayerActor();
        addObject(playeractor, 600, 328);

        BumperBlock bumperblock = new BumperBlock();
        addObject(bumperblock, 30, 244);

        BumperBlock bumperblock2 = new BumperBlock();
        addObject(bumperblock2, 614, 244);

        SafeZone safezone = new SafeZone();
        addObject(safezone, 603, 310);

        FireBallGenerator fireballgenerator = new FireBallGenerator();
        addObject(fireballgenerator, 34, 337);
        FireBallGenerator fireballgenerator2 = new FireBallGenerator();
        addObject(fireballgenerator2, 34, 308);

        BridgeExitActor bridgeexitactor = new BridgeExitActor();
        addObject(bridgeexitactor, 10, 317);
        shrubs shrubs = new shrubs(); 
        addObject(shrubs, 333, 398+3);

        ScoreBox scorebox = new ScoreBox();
        addObject(scorebox, 575, 26);

        LivesBox livesbox = new LivesBox();
        addObject(livesbox, 65, 26);
    }

    @Override
    public void act()
    {
        if(Manager.PlacePlayerFlag())
        {
            if(Manager.CanPlaceNewPlayer())
            {
                playeractor = new PlayerActor();
                playeractor.onBridge = true;
                /*I put the new character back to the start*/
                addObject(playeractor, 600, 328);
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
