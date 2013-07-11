package jo.sm.ship.test;

import java.io.IOException;

import jo.sm.data.Vector3i;
import jo.sm.logic.BlueprintLogic;
import jo.sm.logic.StarMadeLogic;
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
        Vector3i lower = new Vector3i();
        Vector3i upper = new Vector3i();
        ShipLogic.getBounds(bp.getData(), lower, upper);
        System.out.println("  Bounds: "+lower+" -> "+upper);
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
