package jo.sm.data;

import java.io.File;
import java.util.List;

public class StarMade
{
    private File  mBaseDir;
    private List<String>    mBlueprints;
    private List<String>    mDefaultBlueprints;
    private List<Entity>    mEntities;
    
    public File getBaseDir()
    {
        return mBaseDir;
    }
    public void setBaseDir(File baseDir)
    {
        mBaseDir = baseDir;
    }
    public List<String> getBlueprints()
    {
        return mBlueprints;
    }
    public void setBlueprints(List<String> blueprints)
    {
        mBlueprints = blueprints;
    }
    public List<String> getDefaultBlueprints()
    {
        return mDefaultBlueprints;
    }
    public void setDefaultBlueprints(List<String> defaultBlueprints)
    {
        mDefaultBlueprints = defaultBlueprints;
    }
    public List<Entity> getEntities()
    {
        return mEntities;
    }
    public void setEntities(List<Entity> entities)
    {
        mEntities = entities;
    }
}
