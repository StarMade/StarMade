package jo.sm.ship.test;

import java.io.IOException;

import jo.vecmath.Point3i;

import jo.sm.data.BlockTypes;
import jo.sm.data.SparseMatrix;
import jo.sm.logic.BlueprintLogic;
import jo.sm.logic.StarMadeLogic;
import jo.sm.ship.data.Block;
import jo.sm.ship.data.Blueprint;
import jo.sm.ship.logic.ShipLogic;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ReadBlueprints
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

    private void testBlueprint(Blueprint bp)
    {
        Point3i lower = new Point3i();
        Point3i upper = new Point3i();
        ShipLogic.getBounds(bp.getData(), lower, upper);
        System.out.println("  Bounds: "+lower+" -> "+upper);
        SparseMatrix<Block> grid = ShipLogic.getBlocks(bp.getData());
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
    
    @Test
    public void testBlueprints() throws IOException
    {
        for (String name : BlueprintLogic.getBlueprintNames())
        {
            Blueprint bp = BlueprintLogic.readBlueprint(name);
            System.out.println("Read " + bp.getName());
            testBlueprint(bp);
        }
    }

    @Test
    public void testBlueprintsDefault() throws IOException
    {
        for (String name : BlueprintLogic.getDefaultBlueprintNames())
        {
            Blueprint bp = BlueprintLogic.readBlueprint(name);
            System.out.println("Read default " + bp.getName());
            testBlueprint(bp);
        }
    }
}
