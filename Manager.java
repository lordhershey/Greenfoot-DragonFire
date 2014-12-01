import greenfoot.*;

/**
 * Write a description of class Manager here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public enum Manager  
{
    INSTANCE;

    static boolean placePlayerFlag = false;

    static int placeX = 0;
    static int placeY = 0;

    public static boolean CanPlaceNewPlayer()
    {
        placePlayerFlag = false;

        if(Score.getLives() > 0)
        {
            return true;
        }

        //Game Over so Try to Save the Score

        if(UserInfo.isStorageAvailable())
        {
            UserInfo me = UserInfo.getMyInfo();
            if(null == me)
            {
                System.out.println("Not Signed in");
            }
            else
            {
                if(me.getScore() < Score.getScore())
                {
                    me.setScore(Score.getScore());
                    me.store();
                }
            }
        }

        return false;
    }

    public static boolean PlacePlayerFlag()
    {
        return placePlayerFlag;
    }

    public static int getPlaceX()
    {
        return placeX;
    }

    public static int getPlaceY()
    {
        return placeY;
    }

    public static void RequestPlacement()
    {
        //placeX = x;
        //placeY = y;
        Score.removeLife();
        placePlayerFlag = true;
    }
}
