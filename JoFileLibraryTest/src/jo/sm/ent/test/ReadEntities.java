package jo.sm.ent.test;

import java.io.IOException;

import jo.sm.data.Entity;
import jo.sm.data.Vector3i;
import jo.sm.logic.EntityLogic;
import jo.sm.logic.StarMadeLogic;
import jo.sm.ship.data.Data;
import jo.sm.ship.logic.ShipLogic;

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
            if (ent.getData().size() > 0)
            {
                for (Vector3i o : ent.getData().keySet())
                {
                    Data data = ent.getData().get(o);
                    Vector3i lower = new Vector3i();
                    Vector3i upper = new Vector3i();
                    ShipLogic.getBounds(data, lower, upper);
                    System.out.println("    "+o+": "+data.getChunks().length+" little chunks, Bounds: "+lower+" -> "+upper);
                }
                Vector3i lower = new Vector3i();
                Vector3i upper = new Vector3i();
                ShipLogic.getBounds(ent.getData(), lower, upper);
                System.out.println("  Overall Bounds: "+lower+" -> "+upper);
            }
            ent.setData(null); // free memory
        }
    }
}
