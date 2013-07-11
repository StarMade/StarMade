package jo.sm.ship.test;

import java.io.IOException;

import jo.sm.logic.BlueprintLogic;
import jo.sm.logic.StarMadeLogic;
import jo.sm.ship.data.Blueprint;

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

    @Test
    public void testBlueprints() throws IOException
    {
        for (String name : BlueprintLogic.getBlueprintNames())
        {
            Blueprint bp = BlueprintLogic.readBlueprint(name);
            System.out.println("Read " + bp.getName());
        }
    }

    @Test
    public void testBlueprintsDefault() throws IOException
    {
        for (String name : BlueprintLogic.getDefaultBlueprintNames())
        {
            Blueprint bp = BlueprintLogic.readBlueprint(name);
            System.out.println("Read default " + bp.getName());
        }
    }
}
