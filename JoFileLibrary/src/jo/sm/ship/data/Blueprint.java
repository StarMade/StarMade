package jo.sm.ship.data;

import java.util.HashMap;
import java.util.Map;

import jo.vecmath.Point3i;

public class Blueprint
{
    private String  mName;
    private Header  mHeader;
    private Logic   mLogic;
    private Meta    mMeta;
    private Map<Point3i, Data> mData;
    
    public Blueprint()
    {
        mData = new HashMap<Point3i, Data>();
    }
    
    public String getName()
    {
        return mName;
    }
    public void setName(String name)
    {
        mName = name;
    }
    public Header getHeader()
    {
        return mHeader;
    }
    public void setHeader(Header header)
    {
        mHeader = header;
    }
    public Logic getLogic()
    {
        return mLogic;
    }
    public void setLogic(Logic logic)
    {
        mLogic = logic;
    }
    public Meta getMeta()
    {
        return mMeta;
    }
    public void setMeta(Meta meta)
    {
        mMeta = meta;
    }
    public Map<Point3i, Data> getData()
    {
        return mData;
    }
    public void setData(Map<Point3i, Data> data)
    {
        mData = data;
    }
}
