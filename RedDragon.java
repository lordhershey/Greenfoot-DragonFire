import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class RedDragon here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class RedDragon extends Dragon
{
     static GreenfootImage image[];
    static GreenfootImage imagefire[];
    
       static
    {
        image = new GreenfootImage[6];
        image [0] = new GreenfootImage("DragonRed1a.png");
        image [1] = new GreenfootImage("DragonRed2a.png");
        image [2] = new GreenfootImage("DragonRed3a.png");
        image [3] = new GreenfootImage("DragonRed1a.png");
        image [4] = new GreenfootImage("DragonRed2a.png");
        image [5] = new GreenfootImage("DragonRed3a.png");
        image[3].mirrorHorizontally();
        image[4].mirrorHorizontally();
        image[5].mirrorHorizontally();

        imagefire = new GreenfootImage[6];
        imagefire [0] = new GreenfootImage("DragonRed1b.png");
        imagefire [1] = new GreenfootImage("DragonRed2b.png");
        imagefire [2] = new GreenfootImage("DragonRed3b.png");
        imagefire [3] = new GreenfootImage("DragonRed1b.png");
        imagefire [4] = new GreenfootImage("DragonRed2b.png");
        imagefire [5] = new GreenfootImage("DragonRed3b.png");
        imagefire[3].mirrorHorizontally();
        imagefire[4].mirrorHorizontally();
        imagefire[5].mirrorHorizontally();
    } 
    
    public RedDragon()
    {
        bulletspeed = 9;
        firecounterreset = 30;
        speed = 5;
        use_image = image;
        use_imagefire = imagefire;
    } 
}
