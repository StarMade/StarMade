package jo.sm.ship.data;

import jo.vecmath.Point3s;


public class ControllerEntry
{
    private Point3s     mPosition;
    private GroupEntry[] mGroups;
    public Point3s getPosition()
    {
        return mPosition;
    }
    public void setPosition(Point3s position)
    {
        mPosition = position;
    }
    public GroupEntry[] getGroups()
    {
        return mGroups;
    }
    public void setGroups(GroupEntry[] groups)
    {
        mGroups = groups;
    }
}
