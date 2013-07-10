package jo.sm.ship.data;

import jo.sm.data.Vector3s;

public class ControllerEntry
{
    private Vector3s     mPosition;
    private GroupEntry[] mGroups;
    public Vector3s getPosition()
    {
        return mPosition;
    }
    public void setPosition(Vector3s position)
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
