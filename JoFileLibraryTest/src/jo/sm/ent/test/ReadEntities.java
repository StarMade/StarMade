package jo.sm.ent.test;

import java.io.IOException;

import jo.sm.data.Entity;
import jo.sm.logic.EntityLogic;
import jo.sm.logic.StarMadeLogic;
import jo.sm.ship.data.Data;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ReadEntities
{
    @Before
    public void setUp() throws Exception
    {
        String baseDir = System.getProperty("sm.basedir");
        if (baseDir == null)
            baseDir = "/Users/jojaquinta/Downloads/StarMade";
        StarMadeLogic.setBaseDir(baseDir);
    }

    @After
    public void tearDown() throws Exception
    {
    }

    @Test
    public void testEntities() throws IOException
    {
        for (Entity ent : EntityLogic.getEntities())
        {
            System.out.print("Read " + ent.getType());
            if (ent.getName() != null)
                System.out.print(" \""+ent.getName()+"\"");
            if (ent.getUNID() != null)
                System.out.print(" ("+ent.getUNID()+")");
            System.out.println();
            EntityLogic.readEntityData(ent);
            System.out.println("  "+ent.getData().size()+" big chunks");
            for (Data data : ent.getData().values())
                System.out.println("    "+data.getChunks().length+" little chunks");
            ent.setData(null); // free memory
        }
    }
}
