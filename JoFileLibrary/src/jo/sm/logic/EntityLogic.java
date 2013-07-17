package jo.sm.logic;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jo.sm.data.Entity;
import jo.sm.data.StarMade;
import jo.sm.ent.data.Tag;
import jo.sm.ent.logic.TagLogic;
import jo.sm.ship.logic.DataLogic;
import jo.vecmath.Point3i;

public class EntityLogic
{
    public static List<Entity> getEntities() throws IOException
    {
        StarMade sm = StarMadeLogic.getInstance();
        if (sm.getEntities() == null)
        {
            sm.setEntities(new ArrayList<Entity>());
            File serverDatabase = new File(sm.getBaseDir(), "server-database");
            for (File entFile : serverDatabase.listFiles())
                if (entFile.getName().startsWith("ENTITY_"))
                {
                    Entity entity = readEntity(entFile);
                    sm.getEntities().add(entity);
                }
        }
        return sm.getEntities();
    }
    
    public static Entity readEntity(File entFile) throws IOException
    {
        Entity entity = new Entity();
        entity.setFile(entFile);
        parseName(entFile, entity);
        Tag t = TagLogic.readFile(new FileInputStream(entFile), true);
        entity.setTag(t);
        return entity;
    }
    
    public static void readEntityData(Entity entity) throws IOException
    {
        File dataDir = new File(entity.getFile().getParent(), "DATA");
        String name = entity.getFile().getName();
        name = name.substring(0, name.length() - 4); // strip .ent
        entity.setData(DataLogic.readFiles(dataDir, name));
    }

    private static void parseName(File entFile, Entity entity)
    {
        String name = entFile.getName();
        name = name.substring(7); // strip ENTITY_
        name = name.substring(0, name.length() - 4); // strip .ent
        int o = name.indexOf("_");
        if (o < 0)
            throw new IllegalArgumentException("Bad entity file name "+entFile.getName());
        entity.setType(name.substring(0, o));
        name = name.substring(o + 1);
        if ("FLOATINGROCK".equals(entity.getType()))
        {
            o = name.lastIndexOf("_");
            entity.setUNID(name.substring(o + 1)); // valid if result is -1
        }
        else if ("PLANET".equals(entity.getType()))
        {
            o = name.lastIndexOf("_");
            entity.setUNID(name.substring(o + 1)); // valid if result is -1
            name = name.substring(0, o);
            String[] coords = name.split("_");
            Point3i pos = new Point3i(Integer.parseInt(coords[0]),
                    Integer.parseInt(coords[1]),
                    Integer.parseInt(coords[2]));
            entity.setLocation(pos);
        }
        else if ("PLAYERCHARACTER".equals(entity.getType()))
        {
            o = name.lastIndexOf("_");
            entity.setName(name.substring(o + 1)); // valid if result is -1
        }
        else if ("PLAYERSTATE".equals(entity.getType()))
        {
            o = name.lastIndexOf("_");
            entity.setName(name.substring(o + 1)); // valid if result is -1
        }
        else if ("SHIP".equals(entity.getType()))
        {
            if (name.startsWith("AITURRET_"))
            {
                entity.setName("AITURRET");
                entity.setUNID(name.substring(9));
            }
            else if (name.startsWith("MOB_SIM_"))
            {
                o = name.indexOf("_", 8);
                entity.setUNID(name.substring(o + 1)); // valid if result is -1
                entity.setName(name.substring(0, o));
            }
            else if (name.startsWith("MOB_"))
            {
                o = name.indexOf("_", 4);
                entity.setUNID(name.substring(o + 1)); // valid if result is -1
                entity.setName(name.substring(0, o));
            }
            else
            {
                entity.setName(name);
            }
        }
        else if ("SHOP".equals(entity.getType()))
        {
            o = name.lastIndexOf("_");
            entity.setUNID(name.substring(o + 1)); // valid if result is -1
        }
        else if ("SPACESTATION".equals(entity.getType()))
        {
            o = name.lastIndexOf("_");
            entity.setUNID(name.substring(o + 1)); // valid if result is -1
        }
        else
            throw new IllegalArgumentException("Unknown entity type '"+entity.getType()+"'");
    }
}
