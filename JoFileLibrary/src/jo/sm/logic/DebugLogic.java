package jo.sm.logic;

public class DebugLogic
{
    
    private static String mIndent = "";

    public static void indent()
    {
        mIndent += "  ";
    }
    
    public static void outdent()
    {
        mIndent = mIndent.substring(2);
    }
    
    public static void debug(String msg)
    {
        System.out.println(mIndent+msg);
    }

}
