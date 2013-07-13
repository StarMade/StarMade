package jo.sm.ship.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import jo.sm.logic.DebugLogic;
import jo.sm.ship.data.Data;
import jo.sm.ship.data.Header;
import jo.sm.ship.data.Logic;
import jo.sm.ship.data.Meta;
import jo.sm.ship.logic.DataLogic;
import jo.sm.ship.logic.HeaderLogic;
import jo.sm.ship.logic.LogicLogic;
import jo.sm.ship.logic.MetaLogic;
import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ReadServerShips
{
    private File mBaseDir;

    @Before
    public void setUp() throws Exception
    {
        String baseDir = System.getProperty("sm.basedir");
        if (baseDir == null)
            baseDir = "/Users/jojaquinta/Downloads/StarMade";
        mBaseDir = new File(baseDir);
    }
    
    protected List<File> findShips()
    {
        List<File> files = new ArrayList<File>();
        File serverDatabase = new File(mBaseDir, "server-database");
        File data = new File(serverDatabase, "DATA");
        for (File f : data.listFiles())
            if (f.getName().endsWith(".smd2"))
                files.add(f);
        return files;
    }
    
    protected void testFiles(List<File> files)
    {
        for (File f : files)
            testFile(f);
    }
    
    protected void testFile(File f)
    {
        DebugLogic.debug(f.getName());
        try
        {
            testData(f);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            Assert.fail("Cannot read "+f.toString());
        }
    }
    
    protected Header testHeader(File f)
    {
        try
        {
            InputStream fis = new FileInputStream(f);
            return HeaderLogic.readFile(fis, true);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            Assert.fail("Cannot read "+f.toString());
            return null;
        }
    }
    
    protected Logic testLogic(File f)
    {
        try
        {
            InputStream fis = new FileInputStream(f);
            return LogicLogic.readFile(fis, true);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            Assert.fail("Cannot read "+f.toString());
            return null;
        }
    }
    
    protected Meta testMeta(File f)
    {
        try
        {
            InputStream fis = new FileInputStream(f);
            return MetaLogic.readFile(fis, true);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            Assert.fail("Cannot read "+f.toString());
            return null;
        }
    }
    
    protected Data testData(File f)
    {
        try
        {
            InputStream fis = new FileInputStream(f);
            return DataLogic.readFile(fis, true);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            Assert.fail("Cannot read "+f.toString());
            return null;
        }
    }

    @After
    public void tearDown() throws Exception
    {
    }
    
    @Test
    public void testShips()
    {
        List<File> files = findShips();
        testFiles(files);
    }
}
