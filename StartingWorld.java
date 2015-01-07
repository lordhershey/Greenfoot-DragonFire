import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class StartingWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class StartingWorld extends World
{

    /**
     * Constructor for objects of class StartingWorld.
     * 
     */
    public StartingWorld()
    {    
        super(640, 480, 1,false); 
        Score.resetScore();

        prepare();
        
        Greenfoot.start();
    }

    @Override
    public void act()
    {
        boolean JumpKey = Greenfoot.isKeyDown("space");

        if(JumpKey || Greenfoot.mouseClicked(null))
        {
            Score.resetScore();
            BridgeWorld bw = new BridgeWorld();
            Greenfoot.setWorld((World)bw);
        }
    }

    /**
     * Prepare the world for the start of the program. That is: create the initial
     * objects and add them to the world.
     */
    private void prepare()
    {
        InstructActor instructactor = new InstructActor();
        addObject(instructactor, 159, 340);
        PressSpaceBarActor pressspacebaractor = new PressSpaceBarActor();
        addObject(pressspacebaractor, 317, 324);
        HighScoreActor highscoreactor = new HighScoreActor();
        addObject(highscoreactor, 479, 356);
    }
}
