package jo.sm.ship.data;

/*
 +        start   type
 +        0       int                         unknown int
 +        4       int                         numControllers (N)
 +        8       controllerEntry[N]
 +        
 +        controllerEntry is a variable length struct
 +            0   short[3]    Position of the controller block, for example the core is defined at (8, 8, 8)
 +            12  int         Number of groups of controlled blocks.  (M)
 +            16  groupEntry[M]
 +        
 +        groupEntry is a variable length struct
 +            0   short       Block ID for all blocks in this group
 +            2   int         Number of blocks in the group (I)
 +            6   short[3][I] Array of blocks positions for each of the I blocks 
 */
public class Logic
{
    private int               mUnknown1;
    private ControllerEntry[] mControllers;
    
    public int getUnknown1()
    {
        return mUnknown1;
    }
    public void setUnknown1(int unknown1)
    {
        mUnknown1 = unknown1;
    }
    public ControllerEntry[] getControllers()
    {
        return mControllers;
    }
    public void setControllers(ControllerEntry[] controllers)
    {
        mControllers = controllers;
    }
}
