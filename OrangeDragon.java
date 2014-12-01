import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class OrangeDragon here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class OrangeDragon extends Dragon
{
   static GreenfootImage image[];
    static GreenfootImage imagefire[];
    static
    {
        image = new GreenfootImage[6];
        image [0] = new GreenfootImage("DragonOrange1a.png");
        image [1] = new GreenfootImage("DragonOrange2a.png");
        image [2] = new GreenfootImage("DragonOrange3a.png");
        image [3] = new GreenfootImage("DragonOrange1a.png");
        image [4] = new GreenfootImage("DragonOrange2a.png");
        image [5] = new GreenfootImage("DragonOrange3a.png");
        image[3].mirrorHorizontally();
        image[4].mirrorHorizontally();
        image[5].mirrorHorizontally();

        imagefire = new GreenfootImage[6];
        imagefire [0] = new GreenfootImage("DragonOrange1b.png");
        imagefire [1] = new GreenfootImage("DragonOrange2b.png");
        imagefire [2] = new GreenfootImage("DragonOrange3b.png");
        imagefire [3] = new GreenfootImage("DragonOrange1b.png");
        imagefire [4] = new GreenfootImage("DragonOrange2b.png");
        imagefire [5] = new GreenfootImage("DragonOrange3b.png");
        imagefire[3].mirrorHorizontally();
        imagefire[4].mirrorHorizontally();
        imagefire[5].mirrorHorizontally();
    } 
    
    public OrangeDragon()
    {
        bulletspeed = 9;
        firecounterreset = 30;
        speed = 5;
        use_image = image;
        use_imagefire = imagefire;
    }
}
