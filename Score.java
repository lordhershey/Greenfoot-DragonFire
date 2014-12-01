/**
 * Write a description of class Score here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public enum Score  
{
    
    INSTANCE;
    
    static int score = 0;
    static int lives = 7;
    
    static void resetScore()
    {
        score = 0;
        lives = 7;
    }
    
    public static  void addScore(int Amount)
    {
        score += Amount;
        //System.out.println("Score : " + score);
    }
    
    public static int getScore()
    {
        return score;
    }
    
    public static int getLives()
    {
        return lives;
    }
    
    public static void removeLife()
    {
        lives--;
    }
}
