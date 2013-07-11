package jo.sm.data;

import java.io.File;
import java.util.Map;

import jo.sm.ent.data.Tag;
import jo.sm.ship.data.Data;

public class Entity
{
    private File                mFile;
    private String              mName;
    private String              mUNID;
    private String              mType;
    private Vector3i            mLocation;
    private Tag                 mTag;
    private Map<Vector3i, Data> mData;
    
    public String getName()
    {
        return mName;
    }
    public void setName(String name)
    {
        mName = name;
    }
    public String getUNID()
    {
        return mUNID;
    }
    public void setUNID(String uNID)
    {
        mUNID = uNID;
    }
    public String getType()
    {
        return mType;
    }
    public void setType(String type)
    {
        mType = type;
    }
    public Vector3i getLocation()
    {
        return mLocation;
    }
    public void setLocation(Vector3i location)
    {
        mLocation = location;
    }
    public Tag getTag()
    {
        return mTag;
    }
    public void setTag(Tag tag)
    {
        mTag = tag;
    }
    public Map<Vector3i, Data> getData()
    {
        return mData;
    }
    public void setData(Map<Vector3i, Data> data)
    {
        mData = data;
    }
    public File getFile()
    {
        return mFile;
    }
    public void setFile(File file)
    {
        mFile = file;
    }
}
