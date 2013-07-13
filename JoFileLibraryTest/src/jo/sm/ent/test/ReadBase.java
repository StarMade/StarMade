package jo.sm.ent.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import jo.sm.ent.data.Tag;
import jo.sm.ent.logic.TagLogic;
import jo.sm.logic.DebugLogic;
import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;

public class ReadBase
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
    
    protected List<File> findFiles(String infix)
    {
        List<File> files = new ArrayList<File>();
        doFindFiles(mBaseDir, infix, files);
        return files;
    }

    private void doFindFiles(File baseDir, String infix, List<File> files)
    {
        for (File f : baseDir.listFiles())
            if (f.isDirectory())
                doFindFiles(f, infix, files);
            else if (f.getName().contains(infix) && f.getName().endsWith(".ent"))
                files.add(f);
    }
    
    protected void testFiles(List<File> files)
    {
        for (File f : files)
            testFile(f);
    }
    
    protected Tag testFile(File f)
    {
        DebugLogic.debug(f.getName());
        try
        {
            InputStream fis = new FileInputStream(f);
            return TagLogic.readFile(fis, true);
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
}
