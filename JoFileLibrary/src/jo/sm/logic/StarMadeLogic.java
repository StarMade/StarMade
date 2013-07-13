package jo.sm.logic;

import java.io.File;

import jo.sm.data.StarMade;

public class StarMadeLogic
{
    private static StarMade mStarMade;
    
    public static synchronized StarMade getInstance()
    {
        if (mStarMade == null)
        {
            mStarMade = new StarMade();
        }
        return mStarMade;
    }
    
    public static void setBaseDir(String baseDir)
    {
        File bd = new File(baseDir);
        if (!bd.exists())
            throw new IllegalArgumentException("Base directory '"+baseDir+"' does not exist");
        getInstance().setBaseDir(bd);
    }
    
}
