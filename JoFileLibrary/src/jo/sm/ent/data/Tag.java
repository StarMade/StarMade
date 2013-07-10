package jo.sm.ent.data;

import jo.sm.ent.logic.EntityLogic;


public class Tag
{
    private TagType mType;
    private TagType mSubType;
    private String mName;
    private Object mValue;
    
    public Tag()
    {
        mType = TagType.FINISH;
        mName = null;
        mValue = null;
    }
    
    public Tag(TagType type, String name, Object value)
    {
        mType = type;
        mName = name;
        EntityLogic.setValue(this, value); // validation is here
    }
    
    public String toString()
    {
        return mName+" ("+mValue+")";
    }
    
    public TagType getSubType()
    {
        return mSubType;
    }

    public void setSubType(TagType subType)
    {
        mSubType = subType;
    }
    public TagType getType()
    {
        return mType;
    }
    public void setType(TagType type)
    {
        mType = type;
    }
    public String getName()
    {
        return mName;
    }
    public void setName(String name)
    {
        mName = name;
    }
    public Object getValue()
    {
        return mValue;
    }
    public void setValue(Object value)
    {
        mValue = value;
    }
}
