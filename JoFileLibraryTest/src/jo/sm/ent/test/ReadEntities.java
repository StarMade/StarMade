package jo.sm.ent.test;

import java.io.IOException;

import javax.vecmath.Point3i;

import jo.sm.data.BlockTypes;
import jo.sm.data.Entity;
import jo.sm.data.SparseMatrix;
import jo.sm.logic.EntityLogic;
import jo.sm.logic.StarMadeLogic;
import jo.sm.ship.data.Block;
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
                for (Point3i o : ent.getData().keySet())
                {
                    Data data = ent.getData().get(o);
                    Point3i lower = new Point3i();
                    Point3i upper = new Point3i();
                    ShipLogic.getBounds(data, lower, upper);
                    System.out.println("    "+o+": "+data.getChunks().length+" little chunks, Bounds: "+lower+" -> "+upper);
                }
                Point3i lower = new Point3i();
                Point3i upper = new Point3i();
                ShipLogic.getBounds(ent.getData(), lower, upper);
                System.out.println("  Overall Bounds: "+lower+" -> "+upper);
                int dx = upper.x - lower.x;
                int dy = upper.y - lower.y;
                int dz = upper.z - lower.z;
                if ((dx < 80) && (dy < 20) && (dz < 20))
                {
                    SparseMatrix<Block> grid = ShipLogic.getBlocks(ent.getData());
                    for (int z = lower.z; z <= upper.z; z++)
                    {
                        System.out.println("--"+z+"---------------------------------");
                        for (int y = lower.y; y <= upper.y; y++)
                        {
                            for (int x = lower.x; x <= upper.x; x++)
                            {
                                Block b = grid.get(x, y, z);
                                if (b == null)
                                    System.out.print(" ");
                                else if (b.getBlockID() <= 0)
                                    System.out.print("0");
                                else
                                    System.out.print(BlockTypes.BLOCK_ABBR.get(b.getBlockID()));
                            }
                            System.out.println();
                        }
                    }

                }
            }
            ent.setData(null); // free memory
        }
    }
}
