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

public class ReadBlueprints
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
        doFindShips(new File(mBaseDir, "blueprints"), files);
        doFindShips(new File(mBaseDir, "blueprints-default"), files);
        return files;
    }

    private void doFindShips(File baseDir, List<File> files)
    {
        for (File f : baseDir.listFiles())
            if (f.isDirectory())
                files.add(f);
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
            File header = new File(f, "header.smbph");
            testHeader(header);
            File logic = new File(f, "logic.smbpl");
            testLogic(logic);
            File meta = new File(f, "meta.smbpm");
            testMeta(meta);
            File dataDir = new File(f, "DATA");
            for (File data : dataDir.listFiles())
                if (data.getName().endsWith(".smd2"))
                    testData(data);
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
