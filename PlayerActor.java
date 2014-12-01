import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class PlayerActor here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PlayerActor extends Actor
{
    static GreenfootImage guy[];
    static GreenfootImage guyducking[];
    static GreenfootImage fryguy;
    static GreenfootImage burningman[];

    static{
        guy = new GreenfootImage[6];
        guyducking = new GreenfootImage[2];
        guy[0] = new GreenfootImage("Guy1.png");
        guy[1] = new GreenfootImage("Guy2.png");
        guy[2] = new GreenfootImage("Guy3.png");
        guy[3] = new GreenfootImage("Guy1.png");
        guy[4] = new GreenfootImage("Guy2.png");
        guy[5] = new GreenfootImage("Guy3.png");
        guy[3].mirrorHorizontally();
        guy[4].mirrorHorizontally();
        guy[5].mirrorHorizontally();

        guyducking = new GreenfootImage[2];
        guyducking[0] = new GreenfootImage("GuyDucking.png");
        guyducking[1] = new GreenfootImage("GuyDucking.png");
        guyducking[1].mirrorHorizontally();

        fryguy = new GreenfootImage("FallGuy1.png");

        burningman = new GreenfootImage[4];
        burningman[0] = new GreenfootImage("FryGuy1.png");
        burningman[1] = new GreenfootImage("FryGuy2.png");
        burningman[2] = new GreenfootImage("FryGuy1.png");
        burningman[3] = new GreenfootImage("FryGuy2.png");
        burningman[2].mirrorHorizontally();
        burningman[3].mirrorHorizontally();
    }

    int dieY = 0;

    public static enum State {STANDING,
        RUNNING,
        JUMPING,FALLING,DYING,ONFIRE,DEAD,NAN}

    State playerState = State.NAN;

    public boolean onBridge = true;
    boolean faceLeft = true;
    boolean notMoving = true;
    public boolean ducking = false;

    int horizontal = 0;
    int vertical = 0;

    int runangle;

    int speedy = 0;
    int chy = 1;

    int speedx = 4;
    
    public int extrarun = 1;
    
    int runcounter = 1;

    public PlayerActor()
    {
        onBridge = true;
        playerState = State.NAN;
    }

    public void setPlayerBackToStart()
    {
        if(onBridge)
            setLocation(600, 328);
        else
            setLocation(530, 356);
    }

    public boolean alive()
    {
        if(playerState == State.DEAD || playerState == State.DYING)
        {
            return false;
        }
        return (true);
    }

    public void fryGuy()
    {
        if(!alive())
        {
            return;
        }
        
        if(!onBridge)
        {
            if(isOnFire())
            {
                //we are already on fire
                return;
            }
            playerState = State.ONFIRE;
            runcounter = 200;
            if(faceLeft)
            {
                setImage(burningman[0]);
            }
            else
            {
                setImage(burningman[2]);
            }
            return;
        }
        
        setImage(fryguy);
        speedy = -10;
        faceLeft = false;
        playerState = State.DYING;
        dieY = getY();
        
    }

    public boolean isOnFire()
    {
        if(playerState == State.DYING || playerState == State.ONFIRE)
        {
            return true;
        }

        return false;
    }

    public void comicFireLaunch()
    {
        setImage(fryguy);
        speedy = -16;
        faceLeft = true;
        playerState = State.DYING;
        dieY = getY(); 
        setLocation(getX()-10,getY());
    }

    public boolean isNAN()
    {
        if(playerState == State.NAN)
        {
            return true;
        }
        return false;
    }

    public void act() 
    {
        
        if(playerState == State.NAN)
        {
            setRotation(0);
            setPlayerBackToStart();
            faceLeft = true;
            setImage(guy[0]);
            playerState = State.STANDING;
            return;
        }

        if(playerState == State.ONFIRE)
        {
            
            
            if(runcounter-- < 1)
            {
                //TODO Drop smoldering ashy corpse
                playerState = State.DEAD;
                return;
            }
            processOnFireKeys();
            
            int idx = 0;
            //todo check if ducking
  
            if(!faceLeft)
                idx = 2;

            setImage(burningman[idx + (runcounter % 2)]);

            setRotation(runangle);
            move((speedx + 1));
            setRotation(0);
            int y = getY();
            if(y > 397)
            {
                y = 397;
                setLocation(getX(),y);
            }
            else if(y < 105)
            {
                y = 105;
                setLocation(getX(),y);
            }
            
            for(int a = 0 ; a < 20 ; a++)
            {
                BulletFireActor bfa = new BulletFireActor();
                getWorld().addObject(bfa,getX() + Greenfoot.getRandomNumber(21)-10,
                    getY()+ Greenfoot.getRandomNumber(21) - 10);

                    bfa.setRotation(-90 + (Greenfoot.getRandomNumber(91) - 45) * 2);
                
                
                bfa.move(4);
                bfa.speed = 6;
            }
            
            BumperBlock bumper = (BumperBlock)getOneIntersectingObject(BumperBlock.class);
            if(null != bumper)
            {
            
                int x = getX();

                if(faceLeft)
                {
                    x = x + speedx + extrarun;
                }
                else
                {
                    x = x - speedx - extrarun;
                }
                setLocation(x,getY());
                //notMoving = true;
            }
            
            return;
        }
        
        if(playerState == State.DEAD)
        {
            if(!onBridge)
            {
                //playerState = State.NAN;
                getWorld().removeObject(this);
                Manager.RequestPlacement();
                return;
            }
            
            if(runcounter < 100)
            {
                runcounter++;
                int x = getX() + 1;
                int y = getY();

                if(Greenfoot.getRandomNumber(20) == 1)
                {
                    y++;
                }

                setLocation(x,y);
                if(runcounter == 100)
                {

                 
                        //PlayerActor pa = new PlayerActor();
                        //getWorld().addObject(pa,600, 328);
                        //leave the old corpse in water
                        Manager.RequestPlacement();   
                        return;
                
                }
                /*
                if(runcounter > 100)
                {
                playerState = State.NAN;
                runcounter = 0;
                return;
                }
                 */
            }
        }

        if(playerState == State.DYING)
        {
            int x = getX() + 2;
            if(faceLeft)
            {
                x = x - 2 - 20;
            }

            int y = getY() + speedy;
            speedy += 1;
            setLocation(x,y);
            if(!faceLeft)
            {
                setRotation(getRotation() + 3);
            }
            else
            {
                setRotation(getRotation() - 3);
            }

            BumperBlock bumper = (BumperBlock)getOneIntersectingObject(BumperBlock.class);
            if(null != bumper)
            {
                x = getX();
                if(faceLeft)
                {
                    x = x + 20;
                    faceLeft = false;
                    speedy = -8;
                }
                else
                {
                    x = x - 2;
                }
                setLocation(x,getY());
            }

            for(int a = 0 ; a < 20 ; a++)
            {
                BulletFireActor bfa = new BulletFireActor();
                getWorld().addObject(bfa,getX() + Greenfoot.getRandomNumber(21)-10,
                    getY()+ Greenfoot.getRandomNumber(21) - 10);

                bfa.setRotation(getRotation() + (Greenfoot.getRandomNumber(91) - 45) * 2);
                bfa.move(-1);
                bfa.speed = 1;
            }

            if (y > 450)
            {
                playerState = State.DEAD;
                runcounter = 0;
            }
            
            return;
        }

        if(playerState == State.STANDING || playerState == State.RUNNING || playerState == State.JUMPING)
        {
            //do a separate one if ducking
            processArrowKeys();
        }

        if(playerState == State.STANDING)
        {
            //todo check if ducking
            if(ducking)
            {
                if(faceLeft)
                    setImage(guyducking[0]);
                else
                    setImage(guyducking[1]);
            }
            else
            {
                if(faceLeft)
                    setImage(guy[0]);
                else
                    setImage(guy[3]);
            }
        }
        //Ma nipulate Both X and Y
        if(!onBridge && (playerState == State.RUNNING))
        {
            
            
            int idx = 0;
            //todo check if ducking
            runcounter = (runcounter + 1) % 9;
            if(!faceLeft)
                idx = 3;

            setImage(guy[idx + runcounter/3]);

            setRotation(runangle);
            move((speedx + extrarun));
            setRotation(0);
            int y = getY();
            if(y > 397)
            {
                y = 397;
                setLocation(getX(),y);
            }
            else if(y < 105)
            {
                y = 105;
                setLocation(getX(),y);
            }
             
        }

        //Manipulate X Coordinate set image
        if(onBridge && (playerState == State.RUNNING || playerState == State.JUMPING))
        {
            int idx = 0;
            //todo checkk if ducking
            runcounter = (runcounter + 1) % 9;
            if(!faceLeft)
                idx = 3;

            setImage(guy[idx + runcounter/3]);

            if(ducking)
            {
                if(faceLeft)
                {
                    setImage(guyducking[0]);
                }
                else
                {
                    setImage(guyducking[1]);
                }
            }

            if(!notMoving && !ducking)
            {
                int x = getX();

                if(faceLeft)
                    x = x - speedx;
                else
                    x = x + speedx;

                setLocation(x,getY());

            }
        }

        if(playerState == State.RUNNING)
        {
            
            BumperBlock bumper = (BumperBlock)getOneIntersectingObject(BumperBlock.class);
            if(null != bumper)
            {
                int x = getX();

                if(faceLeft)
                {
                    x = x + speedx + extrarun;
                }
                else
                {
                    x = x - speedx - extrarun;
                }
                setLocation(x,getY());
                //notMoving = true;
            }
        }

        //Manipulate Y Coordinate if Needed
        if(onBridge && (playerState == State.JUMPING))
        {
            int y = getY();
            y = y + speedy;
            speedy += chy;

            if(y > 328)
            {
                y = 328;
                speedy = 0;
                if(notMoving)
                {
                    playerState = State.STANDING;
                }
                else
                {
                    playerState = State.RUNNING;
                }
            }
            setLocation(getX(),y);

            if(!ducking)
            {
                BumperBlock bumper = (BumperBlock)getOneIntersectingObject(BumperBlock.class);
                if(null != bumper)
                {
                    if(speedy < 0)
                    {
                        speedy = 2;
                    }  
                }
            }
        }
    }    

    private void processOnFireKeys()
    {
        boolean UpKey = false;
        boolean DownKey = false;
        boolean LeftKey = false;
        boolean RightKey = false;
        int vertical = 0;
        
        UpKey = Greenfoot.isKeyDown("w") || Greenfoot.isKeyDown("up");
        DownKey = Greenfoot.isKeyDown("s") || Greenfoot.isKeyDown("down");
        LeftKey = Greenfoot.isKeyDown("a") || Greenfoot.isKeyDown("left");
        RightKey = Greenfoot.isKeyDown("d") || Greenfoot.isKeyDown("right");
        
        notMoving = false;
        
        if(LeftKey)
        {
            faceLeft = true;
        } else if(RightKey)
        {
            faceLeft = false;
        }
        
        if(UpKey)
            { 
                vertical = -1;
            }
            else if(DownKey)
            {
                vertical = 1;
            }
        
        if(faceLeft)
        {
            switch(vertical)
                {
                    case -1:
                    runangle = 225;
                    break;
                    case 0:
                    runangle = 180;
                    break;
                    case 1:
                    runangle = 135;
                    break;
                }
        }
        else
        {
            switch(vertical)
                {
                    case -1:
                    runangle = 315;
                    break;
                    case 0:
                    runangle = 0;
                    break;
                    case 1:
                    runangle = 45;
                    break;
                }
        }
    }
    
    private void processArrowKeys()
    {
        boolean UpKey = false;
        boolean DownKey = false;
        boolean LeftKey = false;
        boolean RightKey = false;
        boolean JumpKey = false;

        JumpKey = Greenfoot.isKeyDown("shift") || Greenfoot.isKeyDown("control")|| Greenfoot.isKeyDown("space");
        UpKey = Greenfoot.isKeyDown("w") || Greenfoot.isKeyDown("up");
        DownKey = Greenfoot.isKeyDown("s") || Greenfoot.isKeyDown("down");
        LeftKey = Greenfoot.isKeyDown("a") || Greenfoot.isKeyDown("left");
        RightKey = Greenfoot.isKeyDown("d") || Greenfoot.isKeyDown("right");
        //todo there are 2 different sets of logic to go by here
        if(onBridge)
        {
            if(playerState != State.JUMPING)
            {
                notMoving = true;
                playerState = State.STANDING;

                if(LeftKey)
                {
                    faceLeft = true;
                    playerState = State.RUNNING;
                    notMoving = false;
                }
                else if(RightKey)
                {
                    faceLeft = false;
                    playerState = State.RUNNING;
                    notMoving = false;
                }

                ducking = false;

                if(DownKey)
                {
                    ducking = true;
                }

                if((JumpKey || UpKey) && playerState != State.JUMPING)
                {
                    playerState = State.JUMPING;
                    speedy = -8;
                }
            }
            else
            {
                notMoving = true;
                if(LeftKey)
                {
                    faceLeft = true;
                    notMoving = false;
                }
                else if(RightKey)
                {
                    faceLeft = false;
                    notMoving = false;
                }

                ducking = false;

                if(DownKey)
                {
                    ducking = true;
                }

            }
        }
        else
        {
            notMoving = true;
            playerState = State.STANDING;
            int horizontal = 0;
            int vertical = 0;
            if(LeftKey)
            {
                faceLeft = true;
                playerState = State.RUNNING;
                notMoving = false;
                horizontal = -1;
            }
            else if(RightKey)
            {
                faceLeft = false;
                playerState = State.RUNNING;
                notMoving = false;
                horizontal = 1;
            }

            if(UpKey)
            {
                playerState = State.RUNNING;
                notMoving = false;
                vertical = -1;
            }
            else if(DownKey)
            {
                playerState = State.RUNNING;
                notMoving = false;
                vertical = 1;
            }

            switch(horizontal)
            {
                case -1:
                switch(vertical)
                {
                    case -1:
                    runangle = 225;
                    break;
                    case 0:
                    runangle = 180;
                    break;
                    case 1:
                    runangle = 135;
                    break;
                }
                break;
                case 0:
                switch(vertical)
                {
                    case -1:
                    runangle = 270;
                    break;
                    case 1:
                    runangle = 90;
                    break;
                }
                break;
                case 1:
                switch(vertical)
                {
                    case -1:
                    runangle = 315;
                    break;
                    case 0:
                    runangle = 0;
                    break;
                    case 1:
                    runangle = 45;
                    break;
                }
                break;
            }
        }
    }
}
